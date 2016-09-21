package dev.kasse.engine.server.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.kasse.engine.server.state.TicketType;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@Document
public class MenuCategory {

  @Id
  private String id;

  private String name;
 
  private boolean visible;

  private int categoryId;

  private boolean beverage;

  private TicketType type;

  private String shopName;

  private int bon;

  private boolean zutaten;

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

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public boolean isBeverage() {
    return beverage;
  }

  public void setBeverage(boolean beverage) {
    this.beverage = beverage;
  }

  public TicketType getType() {
    return type;
  }

  public void setType(TicketType type) {
    this.type = type;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public int getBon() {
    return bon;
  }

  public void setBon(int bon) {
    this.bon = bon;
  }

  public boolean getZutaten() {
    return zutaten;
  }

  public void setZutaten(boolean zutaten) {
    this.zutaten = zutaten;
  }
 }
