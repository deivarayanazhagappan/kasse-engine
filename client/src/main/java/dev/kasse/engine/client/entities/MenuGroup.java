package dev.kasse.engine.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuGroup {

  private String id;

  private String name;
 
  private int groupId;

  private int gaenge;

  private String categoryId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public int getGaenge() {
    return gaenge;
  }

  public void setGaenge(int gaenge) {
    this.gaenge = gaenge;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }
}
