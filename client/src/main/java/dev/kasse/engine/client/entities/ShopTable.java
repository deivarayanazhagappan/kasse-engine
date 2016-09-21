package dev.kasse.engine.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopTable {

  private String id;

  private int number;

  private boolean occupied;

  private int floor;

  private int xPosition;

  private int yPosition;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public boolean isOccupied() {
    return occupied;
  }

  public void setOccupied(boolean occupied) {
    this.occupied = occupied;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getxPosition() {
    return xPosition;
  }

  public void setxPosition(int xPosition) {
    this.xPosition = xPosition;
  }

  public int getyPosition() {
    return yPosition;
  }

  public void setyPosition(int yPosition) {
    this.yPosition = yPosition;
  }

}
