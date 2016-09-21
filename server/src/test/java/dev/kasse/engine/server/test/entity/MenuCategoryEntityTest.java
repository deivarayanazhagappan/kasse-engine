package dev.kasse.engine.server.test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.MenuCategory;
import dev.kasse.engine.server.repository.query.MenuCategoryRepository;
import dev.kasse.engine.server.state.TicketType;
import dev.kasse.engine.server.test.MockProvider.MenuCategoryMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@EnableMongoRepositories("com.kasse.engine.Repository")
public class MenuCategoryEntityTest {

  @Autowired
  private MenuCategoryRepository menuCategoryRepository;

  @Before
  public void init() {
    menuCategoryRepository.deleteAll();
  }

  @Test
  public void insertMenuCategory() {
    menuCategoryRepository.save(MenuCategoryMockProvider.createMenuCategory("Pizza"));
    assertEquals(1, menuCategoryRepository.findAll().size());

    MenuCategory menuCategory = menuCategoryRepository.findAll().get(0);
    assertEquals(1, menuCategory.getBon());
    assertEquals(101, menuCategory.getCategoryId());
    assertEquals("Pizza", menuCategory.getName());
    assertEquals("Kasse", menuCategory.getShopName());
    assertEquals(TicketType.DINE_IN, menuCategory.getType());
    assertEquals(true, menuCategory.getZutaten());
  }

  @Test
  public void updateMenuCategory() {
    menuCategoryRepository.save(MenuCategoryMockProvider.createMenuCategory("Pizza"));
    assertEquals(1, menuCategoryRepository.findAll().size());

    MenuCategory menuCategory = menuCategoryRepository.findAll().get(0);
    assertEquals("Pizza", menuCategory.getName());
    assertEquals(101, menuCategory.getCategoryId());

    // update the menu category
    menuCategory.setName("Pasta");
    menuCategory.setCategoryId(901);

    menuCategoryRepository.save(menuCategory);
    assertEquals(1, menuCategoryRepository.findAll().size());

    menuCategory = menuCategoryRepository.findAll().get(0);
    assertEquals("Pasta", menuCategory.getName());
    assertEquals(901, menuCategory.getCategoryId());
  }

  @Test
  public void deleteMenuCategory() {
    menuCategoryRepository.save(MenuCategoryMockProvider.createMenuCategory("Pizza"));
    assertEquals(1, menuCategoryRepository.findAll().size());

    MenuCategory menuCategory = menuCategoryRepository.findAll().get(0);
    assertNotNull(menuCategory);

    menuCategoryRepository.delete(menuCategory);

    assertEquals(0, menuCategoryRepository.findAll().size());
  }
}
