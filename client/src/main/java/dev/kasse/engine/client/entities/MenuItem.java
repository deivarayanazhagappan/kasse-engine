package dev.kasse.engine.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuItem {

  private String id;

  private String name;
 
  private String itemId;

  private String barcode;

  private String groupId;

  private Double price;

  private Double tax;

  private boolean variablePrice;

  private boolean shouldPrintToKitchen;

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

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getTax() {
    return tax;
  }

  public void setTax(Double tax) {
    this.tax = tax;
  }

  public boolean isVariablePrice() {
    return variablePrice;
  }

  public void setVariablePrice(boolean variablePrice) {
    this.variablePrice = variablePrice;
  }

  public boolean isShouldPrintToKitchen() {
    return shouldPrintToKitchen;
  }

  public void setShouldPrintToKitchen(boolean shouldPrintToKitchen) {
    this.shouldPrintToKitchen = shouldPrintToKitchen;
  }

}
