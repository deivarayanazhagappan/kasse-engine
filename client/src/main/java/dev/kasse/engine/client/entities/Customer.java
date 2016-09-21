package dev.kasse.engine.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

  private String id;

  private String referenceNumber;

  private String telephoneNumber;

  private String email;

  private String street;

  private String doorNumber;

  private String bellName;

  private String zipcode;

  private String landmark;

  private String companyName;

  private String area;

  private String secondaryTelephone;

  private String firstName;

  private String lastName;

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getDoorNumber() {
    return doorNumber;
  }

  public void setDoorNumber(String doorNumber) {
    this.doorNumber = doorNumber;
  }

  public String getBellName() {
    return bellName;
  }

  public void setBellName(String bellName) {
    this.bellName = bellName;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getLandmark() {
    return landmark;
  }

  public void setLandmark(String landmark) {
    this.landmark = landmark;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getSecondaryTelephone() {
    return secondaryTelephone;
  }

  public void setSecondaryTelephone(String secondaryTelephone) {
    this.secondaryTelephone = secondaryTelephone;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

}
