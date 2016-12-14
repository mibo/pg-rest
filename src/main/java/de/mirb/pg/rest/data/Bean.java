package de.mirb.pg.rest.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Bean {
  private String name;
  private String content;

  public Bean() {
  }

  public Bean(String name, String content) {
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
