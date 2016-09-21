package dev.kasse.engine.server.test.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.MenuCategory;
import dev.kasse.engine.server.repository.query.MenuCategoryRepository;
import dev.kasse.engine.server.state.TicketType;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
public class MenuCategoryQueryTest {

  @Autowired
  protected MenuCategoryRepository menuCategoryRepository;

  @Before
  public void init() {
    menuCategoryRepository.deleteAll();
  }

  @Test
  public void queryAll() {

    assertEquals(0, menuCategoryRepository.findAll().size());
    
    menuCategoryRepository.save(createMenuCategories());

    List<MenuCategory> categories = menuCategoryRepository.findAll();
    assertEquals(2, categories.size());
    MenuCategory category = categories.get(0);
    assertEquals(1, category.getBon());
    assertEquals(101,category.getCategoryId());
    assertEquals("Pizza", category.getName());
    assertEquals("Kasse",category.getShopName());
    assertEquals(TicketType.DINE_IN,category.getType());
    assertEquals(true, category.getZutaten());
    assertNotNull(category.getId());
  }

  @Test
  public void queryById() {

    assertEquals(0, menuCategoryRepository.findAll().size());
    
    menuCategoryRepository.save(createMenuCategories());

    List<MenuCategory> categories = menuCategoryRepository.findAll();
    assertEquals(2, categories.size());
    MenuCategory category = categories.get(0);
    assertEquals(category.getName(), menuCategoryRepository
          .findById(category.getId())
          .getName()); 
  }

  @Test
  public void queryByName() {

    menuCategoryRepository.save(createMenuCategories());

    assertEquals(1, menuCategoryRepository.findByName("Pizza").size());
    assertEquals(1, menuCategoryRepository.findByName("Pasta").size());
    assertEquals(0, menuCategoryRepository.findByName("Spaghetti").size());
  }

  @Test
  public void queryByCategoryId() {

    menuCategoryRepository.save(createMenuCategories());

    assertEquals(2, menuCategoryRepository.findByCategoryId(101).size());
    assertEquals(0, menuCategoryRepository.findByCategoryId(100).size());
  }

  @Test
  public void queryByType() {

    menuCategoryRepository.save(createMenuCategories());

    assertEquals(2, menuCategoryRepository.findByType(TicketType.DINE_IN).size());
    assertEquals(0, menuCategoryRepository.findByType(TicketType.HOME_DELIVERY).size());
  }

  @Test
  public void queryByVisiblity() {

    menuCategoryRepository.save(createMenuCategories());

    assertEquals(2, menuCategoryRepository.findByVisible(true).size());
    assertEquals(0, menuCategoryRepository.findByVisible(false).size());
  }

  private List<MenuCategory> createMenuCategories() {
    List<MenuCategory> categories = new ArrayList<MenuCategory>();
    categories.add(createMenuCategory("Pizza"));
    categories.add(createMenuCategory("Pasta"));
    return categories;
  }

  private MenuCategory createMenuCategory(String name) {
    MenuCategory category = new MenuCategory();

    category.setBeverage(false);
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
