package de.mirb.pg.rest.data;

/**
 * Created by michael on 14.12.16.
 */
public class NotSupportedBean {
  private String name;
  private String content;

  public NotSupportedBean(String name, String content) {
    this.name = name;
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
