package ai.active.morfeus.utils;

import ai.active.fulfillment.webhook.data.response.*;
import ai.active.morfeus.model.ButtonModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TemplateConversionUtil {

  private static final String CAROUSEL = "carousel";
  private static final String TITLE = "title";
  private static final String BUTTONS = "buttons";
  private static final String SUBTITLE = "subtitle";
  private static final String TEXT = "text";

  public AbstractMessage showCarouselTemplate(List<Map<String, Object>> listOfCards) {
    CarouselMessage carouselMessage = new CarouselMessage();
    // iterate through loop of card detals
    List<Content> contents = new ArrayList<>();
    for (Map<String, Object> payloadMap : listOfCards) {
      Content content = new Content();
      StringBuilder sb = new StringBuilder();
      if(payloadMap.containsKey(TITLE)) {
        content.setTitle((String) payloadMap.get(TITLE));
        sb.append((String) payloadMap.get(TITLE));
        sb.append(" \n");
      }
      if(payloadMap.containsKey(SUBTITLE)) {
        content.setSubtitle((String) payloadMap.get(SUBTITLE));
      }
      if(payloadMap.containsKey(BUTTONS)) {
        content.setButtons(setButton((List<ButtonModel>) payloadMap.get(BUTTONS)));
        String str = ((List<ButtonModel>) payloadMap.get(BUTTONS)).stream().map(ButtonModel::getText).
            collect(Collectors.joining(" \n"));
        sb.append(str);
      }
      contents.add(content);
    }
    carouselMessage.setType(CAROUSEL);
    carouselMessage.setContent(contents);
    return carouselMessage;
  }

  private List<Object> setButton(List<ButtonModel> buttonModelList) {
    List<Object> buttons = new ArrayList<>();
    for(ButtonModel buttonModel: buttonModelList) {
      Button button = new Button();
      button.setTitle(buttonModel.getText());
      button.setIntent(buttonModel.getIntent());
      button.setType(buttonModel.getType());
      button.setPayload(buttonModel.getPayload());
      buttons.add(button);
    }
    return buttons;
  }

  public AbstractMessage showTextMessage(String text) {
    TextMessage textMessage = new TextMessage();
    textMessage.setType(TEXT);
    textMessage.setSpeechResponse(text);
    textMessage.setContent(text);
    return textMessage;
  }

}
