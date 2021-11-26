package ai.active.morfeus.controller;

import ai.active.fulfillment.webhook.data.request.MorfeusWebhookRequest;
import ai.active.fulfillment.webhook.data.response.MorfeusWebhookResponse;
import ai.active.fulfillment.webhook.data.response.Status;
import ai.active.fulfillment.webhook.data.response.TextMessage;
import ai.active.morfeus.service.RedisService;
import ai.active.morfeus.service.WebhookService;
import ai.active.morfeus.utils.TemplateConversionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class WebhookController {
    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private WebhookService webhookService;

    @Autowired
    private TemplateConversionUtil templateConsversionUtil;

    @Autowired
    private RedisService redisService;

    private TextMessage textMessage = new TextMessage();

    public WebhookController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping(path = "/view/bookings", consumes = "application/json", produces = "application/json")
    public MorfeusWebhookResponse getBooikings(@RequestBody(required = true) String body,
        @RequestHeader(name = "X-Hub-Signature", required = true) String signature, HttpServletResponse response) throws IOException {
        MorfeusWebhookRequest request = objectMapper.readValue(body, MorfeusWebhookRequest.class);
        MorfeusWebhookResponse morfeusWebhookResponse = new MorfeusWebhookResponse();
        morfeusWebhookResponse.setMessages(Arrays.asList(
            templateConsversionUtil.showCarouselTemplate(webhookService.getBookings())));

//        redisService.addToSet(webhookService.getBookings(), "success", 6000);
//        String load = redisService.getFromSet("success");

        morfeusWebhookResponse.setStatus(Status.SUCCESS);
        return morfeusWebhookResponse;
    }

    @PostMapping(path = "/download/status", consumes = "application/json", produces = "application/json")
    public MorfeusWebhookResponse getBlockStatus(@RequestBody(required = true) String body,
        @RequestHeader(name = "X-Hub-Signature", required = true) String signature, HttpServletResponse response) throws IOException {
        MorfeusWebhookRequest request = objectMapper.readValue(body, MorfeusWebhookRequest.class);
        MorfeusWebhookResponse morfeusWebhookResponse = new MorfeusWebhookResponse();
        morfeusWebhookResponse.setMessages(Arrays.asList(
            templateConsversionUtil.showTextMessage(webhookService.getDownloadStatus())));
        morfeusWebhookResponse.setStatus(Status.SUCCESS);
        return morfeusWebhookResponse;
    }

    @PostMapping(path = "/download/status/template", consumes = "application/json", produces = "application/json")
    public MorfeusWebhookResponse getBlockStatusTemplate(@RequestBody(required = true) String body,
        @RequestHeader(name = "X-Hub-Signature", required = true) String signature, HttpServletResponse response) throws IOException {
        MorfeusWebhookRequest request = objectMapper.readValue(body, MorfeusWebhookRequest.class);
        MorfeusWebhookResponse morfeusWebhookResponse = webhookService.getDownloadStatusAsTemplate(request);
        morfeusWebhookResponse.setStatus(Status.SUCCESS);
        return morfeusWebhookResponse;
    }

//    @GetMapping(path = "/get/key", consumes = "application/json", produces = "application/json")
//    public String getRedisKeys(@RequestBody(required = true) String body,
//        @RequestHeader(name = "X-Hub-Signature", required = true) String signature, HttpServletResponse response) throws IOException {
//        MorfeusWebhookRequest request = objectMapper.readValue(body, MorfeusWebhookRequest.class);
//        return redisService.getFromSet(request.getId());
//    }

    @PostMapping(path = "/get/mobile/number", consumes = "application/json", produces = "application/json")
    public MorfeusWebhookResponse getMobileNumber(@RequestHeader(name = "X-Hub-Signature", required = true) String signature,
        HttpServletResponse response) throws IOException {
        MorfeusWebhookResponse morfeusWebhookResponse = new MorfeusWebhookResponse();
        morfeusWebhookResponse.setMessages(Arrays.asList(templateConsversionUtil.showTextMessage(webhookService.getMobileNumber())));
        morfeusWebhookResponse.setStatus(Status.SUCCESS);
        return morfeusWebhookResponse;
    }

    @PostMapping(path = "/get/mobile/number/validation", consumes = "application/json", produces = "application/json")
    public MorfeusWebhookResponse getMobileNumberValidation(@RequestBody(required = true) String body,
        @RequestHeader(name = "X-Hub-Signature", required = true) String signature, HttpServletResponse response) throws IOException {
        MorfeusWebhookRequest request = objectMapper.readValue(body, MorfeusWebhookRequest.class);
        MorfeusWebhookResponse morfeusWebhookResponse = (webhookService.getMobileNumberValidation(request));
        return morfeusWebhookResponse;
    }

    @PostMapping(path = "/otp/validation", consumes = "application/json", produces = "application/json")
    public MorfeusWebhookResponse otpVerification(@RequestBody(required = true) String body,
        @RequestHeader(name = "X-Hub-Signature", required = true) String signature, HttpServletResponse response) throws Exception {
        MorfeusWebhookRequest request = objectMapper.readValue(body, MorfeusWebhookRequest.class);
        System.out.println(body);
        MorfeusWebhookResponse morfeusWebhookResponse = webhookService.otpValidation(request);
        return morfeusWebhookResponse;
    }
}
