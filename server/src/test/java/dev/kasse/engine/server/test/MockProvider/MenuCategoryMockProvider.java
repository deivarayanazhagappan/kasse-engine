package dev.kasse.engine.server.test.MockProvider;

import java.util.ArrayList;
import java.util.List;

import dev.kasse.engine.server.entities.MenuCategory;
import dev.kasse.engine.server.state.TicketType;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public class MenuCategoryMockProvider {

  public static List<MenuCategory> createMenuCategories() {
    List<MenuCategory> categories = new ArrayList<MenuCategory>();
    categories.add(createMenuCategory("Pizza"));
    categories.add(createMenuCategory("Pasta"));
    return categories;
  }

  public static MenuCategory createMenuCategory(String name) {
    MenuCategory category = new MenuCategory();

    category.setBeverage(false);
    category.setId("categoryId");
    category.setBon(1);
    category.setName(name);
    category.setShopName("Kasse");
    category.setType(TicketType.DINE_IN);
    category.setVisible(true);
    category.setZutaten(true);
    category.setCategoryId(101);

    return category;
  }
}
