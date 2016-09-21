package dev.kasse.engine.server.entities;

import java.util.List;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public class TicketItem {

  private String itemId;

  private String itemName;

  private int itemCount;

  private String groupName;

  private String categoryName;

  private Double total;

  private Double subTotal;

  private Double tax;

  private Double taxRate;

  private boolean beverage;

  private int printOrder;

  private boolean shouldPrintToKitchen;

  private boolean printedToKitchen;

  private List<CookingInstruction> cookingInstructions;

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public int getItemCount() {
    return itemCount;
  }

  public void setItemCount(int itemCount) {
    this.itemCount = itemCount;
  }

  public String getGroupName() {
    return groupName;
  }

  public Double getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(Double taxRate) {
    this.taxRate = taxRate;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Double getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }

  public Double getTax() {
    return tax;
  }

  public void setTax(Double tax) {
    this.tax = tax;
  }

  public boolean isBeverage() {
    return beverage;
  }

  public void setBeverage(boolean beverage) {
    this.beverage = beverage;
  }

  public int getPrintOrder() {
    return printOrder;
  }

  public void setPrintOrder(int printOrder) {
    this.printOrder = printOrder;
  }

  public boolean isShouldPrintToKitchen() {
    return shouldPrintToKitchen;
  }

  public void setShouldPrintToKitchen(boolean shouldPrintToKitchen) {
    this.shouldPrintToKitchen = shouldPrintToKitchen;
  }

  public boolean isPrintedToKitchen() {
    return printedToKitchen;
  }

  public void setPrintedToKitchen(boolean printedToKitchen) {
    this.printedToKitchen = printedToKitchen;
  }

  public List<CookingInstruction> getCookingInstructions() {
    return cookingInstructions;
  }

  public void setCookingInstructions(
      List<CookingInstruction> cookingInstructions) {
    this.cookingInstructions = cookingInstructions;
  }
}
