package dev.kasse.engine.server.test.MockProvider;

import java.util.Arrays;
import java.util.List;

import dev.kasse.engine.server.entities.ShopTable;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public class ShopTableMockProvider {

  public static List<ShopTable> createShopTables() {
    return Arrays.asList(createShopTable(1));  
  }
  
  public static ShopTable createShopTable(int tableNumber) {
    ShopTable table = new ShopTable();
    table.setId("kasseId");
    table.setFloor(1);
    table.setNumber(tableNumber);
    table.setOccupied(false);
    return table;
  }
}
