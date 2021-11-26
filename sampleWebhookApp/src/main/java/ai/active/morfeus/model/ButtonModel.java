package ai.active.morfeus.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class ButtonModel.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"text", "payload", "payloads", "type", "image","amount","when","category","paymentType","bookingDetails"})
public class ButtonModel {

  /**
   * The text.
   */
  @JsonProperty("text")
  private String text;

  /**
   * The payload.
   */
  @JsonProperty("payload")
  private String payload;

  /**
   * amount.
   */
  @JsonProperty("amount")
  private String amount;

  /**
   * when.
   */
  @JsonProperty("when")
  private String when;


  /**
   * category.
   */
  @JsonProperty("category")
  private String category;

  /**
   * paymentType.
   */
  @JsonProperty("paymentType")
  private String paymentType;

  /**
   * bookingDetails.
   */
  @JsonProperty("bookingDetails")
  private String bookingDetails;

  /**
   * The payloads.
   */
  @JsonProperty("payloads")
  private String payloads;

  /**
   * The type.
   */
  @JsonProperty("type")
  private String type;

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  @JsonProperty("subtitle")
  private String subtitle;

  /**
   * The type.
   */
  @JsonProperty("image")
  private String image;

  /**
   * The intent.
   */
  private String intent;

  /**
   * Gets the text.
   *
   * @return the text
   */
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  /**
   * Sets the text.
   *
   * @param text the new text
   */
  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Gets the payload.
   *
   * @return the payload
   */
  @JsonProperty("payload")
  public String getPayload() {
    return payload;
  }

  /**
   * Sets the payload.
   *
   * @param payload the new payload
   */
  @JsonProperty("payload")
  public void setPayload(String payload) {
    this.payload = payload;
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the new type
   */
  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  /**
   * Gets the intent.
   *
   * @return the intent
   */
  public String getIntent() {
    return intent;
  }

  /**
   * Sets the intent.
   *
   * @param intent the new intent
   */
  public void setIntent(String intent) {
    this.intent = intent;
  }

  @JsonProperty("image")
  public String getImage() {
    return image;
  }

  @JsonProperty("image")
  public void setImage(String image) {
    this.image = image;
  }

  @JsonProperty("payloads")
  public String getPayloads() {
    return payloads;
  }

  @JsonProperty("payloads")
  public void setPayloads(String payloads) {
    this.payloads = payloads;
  }

  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }

  @JsonProperty("amount")
  public void setAmount(String amount) {
    this.amount = amount;
  }

  @JsonProperty("when")
  public String getWhen() {
    return when;
  }

  @JsonProperty("when")
  public void setWhen(String when) {
    this.when = when;
  }

  @JsonProperty("category")
  public String getCategory() {
    return category;
  }

  @JsonProperty("category")
  public void setCategory(String category) {
    this.category = category;
  }

  @JsonProperty("paymentType")
  public String getPaymentType() {
    return paymentType;
  }

  @JsonProperty("paymentType")
  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  @JsonProperty("bookingDetails")
  public String getbookingDetails() {
    return bookingDetails;
  }

  @JsonProperty("bookingDetails")
  public void setbookingDetails(String bookingDetails) {
    this.bookingDetails = bookingDetails;
  }
}

