package dev.kasse.engine.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CookingInstruction {

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  private String id;

}
