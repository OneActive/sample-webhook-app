package ai.active.morfeus.utils;

import ai.active.morfeus.model.ButtonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookingDetailUtils {
  private static final String TITLE = "title";
  private static final String BUTTON = "buttons";
  private static final String POSTBACK = "postback";
  private static final String CLASSPATH = "CLASSPATH:";
  private static final String VI = "vi";
  private static final Map<String, String> numToWords = new HashMap<>();
  private ResourceLoader resourceLoader;
  private ObjectMapper objectMapper;

  @Autowired BookingDetailUtils(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
    this.resourceLoader = resourceLoader;
    this.objectMapper = objectMapper;
  }

  public ButtonModel createButton(String buttonTitle, String intent, String entityName, String entityValue) {
    ButtonModel buttonModel = new ButtonModel();
    buttonModel.setIntent(intent);
    buttonModel.setText(buttonTitle);
    String payload = "{\"data\":{\""+ entityName +"\":\"" + entityValue + "\"}, \"intent\": \""+intent +"\"}";
    buttonModel.setPayload(payload);
    buttonModel.setType(POSTBACK);
    return buttonModel;
  }
}
