package ai.active.morfeus.service;

import ai.active.fulfillment.webhook.data.request.MorfeusWebhookRequest;
import ai.active.fulfillment.webhook.data.response.*;
import ai.active.morfeus.constants.Constants;
import ai.active.morfeus.model.ButtonModel;
import ai.active.morfeus.utils.ApplicationLogger;
import ai.active.morfeus.utils.BookingDetailUtils;
import ai.active.morfeus.utils.TemplateConversionUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class WebhookService {

  @Autowired
  private final ObjectMapper objectMapper;

  @Autowired
  private TemplateConversionUtil templateConsversionUtil;

  @Autowired
  private BookingDetailUtils bookingDetailUtils;

  @Autowired
  private ResourceLoader resourceLoader;

  private static final String CLASSPATH = "classpath:";
  private static final String STATUS = "Your booking detail has been downloaded successfully. Thank you!";
  private static final String ENTER_MOBILE_NUMBER = "Please enter your registered mobile number to check your bookings";

  public WebhookService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String getDownloadStatus() {
    return STATUS;
  }

  public String getMobileNumber(){
    return ENTER_MOBILE_NUMBER;
  }

  public List<Map<String, Object>> getBookings() {
    try {
      InputStream resourceAsStream =
          resourceLoader.getResource(CLASSPATH + Constants.FOLDER_PATH + Constants.BOOKINGS + Constants.JSON).getInputStream();
      JsonNode bookingDetails = objectMapper.readValue(resourceAsStream, JsonNode.class);
      return formPayloadFromJson(bookingDetails);
    } catch (IOException e) {
      ApplicationLogger.logError("Error while gettings bookings : ", e);
      return Collections.emptyList();
    }
  }

  // will return list of payloads.
  public List<Map<String, Object>> formPayloadFromJson(JsonNode js) {
    List<Map<String, Object>> listOfFlights = new ArrayList<>();
    for (JsonNode flightDetail : js) {
      Map<String, Object> payloadMap = new HashMap<>();
      payloadMap.put("title", flightDetail.get("flightName").asText());
      payloadMap.put("subtitle",
          "OrderNumber : " + flightDetail.get("orderNumber").asText() + " \n" + "Date : " + flightDetail.get("date").asText());
      List<ButtonModel> button = new ArrayList<>();
      button.add(bookingDetailUtils.createButton("Select", "txn-ticketbook", "sys_number",
          flightDetail.get("displayOrderNumber").asText()));
      payloadMap.put("buttons", button);
      listOfFlights.add(payloadMap);
    }
    return listOfFlights;
  }

  public MorfeusWebhookResponse getDownloadStatusAsTemplate(MorfeusWebhookRequest request) {

    WorkflowValidationResponse workflowValidationResponse = new WorkflowValidationResponse.Builder(Status.SUCCESS).build();
    Content content = new Content();
    content.setImage("https://i.ibb.co/GRzCZpp/success.gif");
    content.setTitle(STATUS);
    content.setSubtitle("Please save the reference id 5zMbyvHX for future reference.");

    CarouselMessage carouselMessage = new CarouselMessage();
    carouselMessage.setType("carousel");
    List<Content> ListOfcontents = new ArrayList<>();
    ListOfcontents.add(content);
    carouselMessage.setContent(ListOfcontents);
    workflowValidationResponse.setMessages(Arrays.asList(carouselMessage));
    return workflowValidationResponse;

  }

  public MorfeusWebhookResponse getMobileNumberValidation(MorfeusWebhookRequest morfeusWebhookRequest) {
    WorkflowValidationResponse workflowValidationResponse = new WorkflowValidationResponse.Builder(Status.SUCCESS).build();
    String userInput = morfeusWebhookRequest.getWorkflowParams().getRequestVariables().get("sys.person-phone-number");

    if (StringUtils.isNotEmpty(userInput) && userInput.length() == 10 && userInput.startsWith("98")) {
      workflowValidationResponse.setStatus(Status.SUCCESS);
    } else {
      TextMessage textMessage = new TextMessage();
      textMessage.setType("text");
      textMessage.setContent("Entered phone number is not registered with us. Please enter valid number");
      workflowValidationResponse.setStatus(Status.FAILED);
      workflowValidationResponse.setMessages(Arrays.asList(textMessage));
    }
    return workflowValidationResponse;
  }

  public MorfeusWebhookResponse otpValidation(MorfeusWebhookRequest request) {
    MorfeusWebhookResponse morfeusWebhookResponse = new MorfeusWebhookResponse();
    if ("999999".equalsIgnoreCase(request.getWorkflowParams().getRequestVariables().get("sys.amount"))) { //999999
      morfeusWebhookResponse.setStatus(Status.SUCCESS);
      return morfeusWebhookResponse;
    } else {
      WorkflowValidationResponse workflowValidationResponse = new WorkflowValidationResponse.Builder(Status.SUCCESS).build();
      if ("three".equalsIgnoreCase(request.getWorkflowParams().getWorkflowVariables().get("wrong_otp_attempts"))) {
        Map<String, String> workflowVariables = request.getWorkflowParams().getWorkflowVariables();
        workflowVariables.put("node", "otpErrorMessage");
        workflowValidationResponse.setWorkflowVariables(workflowVariables);
        return workflowValidationResponse;
      } else if ("two".equalsIgnoreCase(request.getWorkflowParams().getWorkflowVariables().get("wrong_otp_attempts"))) {
        Map<String, String> workflowVariables = request.getWorkflowParams().getWorkflowVariables();
        workflowVariables.put("wrong_otp_attempts", "three");
        workflowValidationResponse.setWorkflowVariables(workflowVariables);
        workflowValidationResponse.setStatus(Status.FAILED);
        workflowValidationResponse.setMessages(
            Arrays.asList(templateConsversionUtil.showTextMessage("Sorry, the entered OTP is incorrect. \n One attempt left.")));
        return workflowValidationResponse;
      } else {
        Map<String, String> workflowVariables = request.getWorkflowParams().getWorkflowVariables();
        workflowVariables.put("wrong_otp_attempts", "two");
        workflowValidationResponse.setWorkflowVariables(workflowVariables);
        workflowValidationResponse.setStatus(Status.FAILED);
        workflowValidationResponse.setMessages(
            Arrays.asList(templateConsversionUtil.showTextMessage("Sorry, the entered OTP is incorrect. \n Two attempts left.")));
        return workflowValidationResponse;
      }
    }

  }
}
