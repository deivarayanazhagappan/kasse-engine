package dev.kasse.engine.server.test.MockProvider;

import java.util.ArrayList;
import java.util.List;

import dev.kasse.engine.server.entities.MenuItem;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public class MenuItemMockProvider {

  public static List<MenuItem> createMenuItems() {
    List<MenuItem> items = new ArrayList<MenuItem>();
    items.add(createMenuItem("Pizza"));
    items.add(createMenuItem("Pasta"));
    return items;
  }

  public static MenuItem createMenuItem(String name) {
    MenuItem item = new MenuItem();

    item.setBarcode("12345");
    item.setGroupId("groupId");
    item.setId("itemId");
    item.setTax(19.00);
    item.setItemId("111");
    item.setName(name);
    item.setPrice(2.50);
    item.setVariablePrice(true);
    item.setShouldPrintToKitchen(true);
    return item;
  }
}
