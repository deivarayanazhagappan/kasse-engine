package dev.kasse.engine.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  private String id;

  private String password;

  private String firstName;

  private String lastName;

  private String telephoneNumber;

  private boolean isDriver;

  private boolean availableForDelivery;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public boolean isDriver() {
    return isDriver;
  }

  public void setDriver(boolean isDriver) {
    this.isDriver = isDriver;
  }

  public boolean isAvailableForDelivery() {
    return availableForDelivery;
  }

  public void setAvailableForDelivery(boolean availableForDelivery) {
    this.availableForDelivery = availableForDelivery;
  }
}
