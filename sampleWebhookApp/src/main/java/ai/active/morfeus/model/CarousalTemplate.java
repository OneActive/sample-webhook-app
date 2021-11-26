package ai.active.morfeus.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * The Class CarousalTemplate.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarousalTemplate {

  /**
   * The button.
   */
  @JsonProperty("button")
  private Map<String, String> button = null;

  /**
   * The image.
   */
  @JsonProperty("image")
  private String image;

  public String getTitle1l() {
    return title1;
  }

  public void setTitle1(String title1) {
    this.title1 = title1;
  }

  public String getTitle2() {
    return title2;
  }

  public void setTitle2(String title2) {
    this.title2 = title2;
  }

  /**
   * The sub title.
   */
  @JsonProperty("subTitle")
  private String subTitle;

  @JsonProperty("title1")
  private String title1;

  @JsonProperty("title2")
  private String title2;
  /**
   * The title.
   */
  @JsonProperty("title")
  private String title;

  /**
   * No args constructor for use in serialization.
   */
  public CarousalTemplate() {
  }

  /**
   * Instantiates a new carousal templates.
   *
   * @param button   the button
   * @param image    the image
   * @param subTitle the sub title
   * @param title    the title
   */
  public CarousalTemplate(final Map<String, String> button, String image, String subTitle, String title) {
    super();
    this.button = button;
    this.image = image;
    this.subTitle = subTitle;
    this.title = title;
  }

  /**
   * Gets the button.
   *
   * @return the button
   */
  public Map<String, String> getButton() {
    return button;
  }
  /**
   * Sets the button.
   *
   * @param button the new button
   */
  public void setButton(Map<String, String> button) {
    this.button = button;
  }

  /**
   * Gets the image.
   *
   * @return the image
   */
  @JsonProperty("image")
  public String getImage() {
    return image;
  }

  /**
   * Sets the image.
   *
   * @param image the new image
   */
  @JsonProperty("image")
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * Gets the sub title.
   *
   * @return the sub title
   */
  @JsonProperty("subTitle")
  public String getSubTitle() {
    return subTitle;
  }

  /**
   * Sets the sub title.
   *
   * @param subTitle the new sub title
   */
  @JsonProperty("subTitle")
  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  /**
   * Gets the title.
   *
   * @return the title
   */
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title.
   *
   * @param title the new title
   */
  @JsonProperty("title")
  public void setTitle(String title) {
    this.title = title;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }


}



