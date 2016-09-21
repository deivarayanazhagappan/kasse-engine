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

import dev.kasse.engine.server.entities.MenuItem;
import dev.kasse.engine.server.repository.query.MenuItemRepository;
import dev.kasse.engine.server.test.MockProvider.MenuItemMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@EnableMongoRepositories("com.kasse.engine.Repository")
public class MenuItemEntityTest {

  @Autowired
  private MenuItemRepository menuItemRepository;

  @Before
  public void init() {
    menuItemRepository.deleteAll();
    menuItemRepository.save(MenuItemMockProvider.createMenuItem("Margherita"));
  }

  @Test
  public void insertMenuItem() {
    assertEquals(1, menuItemRepository.findAll().size());

    MenuItem item = menuItemRepository.findAll().get(0);
    assertEquals("12345", item.getBarcode());
    assertEquals("groupId", item.getGroupId());
    assertEquals("111", item.getItemId());
    assertEquals(new Double(19.00), item.getTax());
    assertEquals("Margherita", item.getName());
    assertEquals(new Double(2.50), item.getPrice());
    assertEquals(true, item.isShouldPrintToKitchen());
    assertNotNull(item.getId());
  }

  @Test
  public void updateMenuItem() {
    
    assertEquals(1, menuItemRepository.findAll().size());

    MenuItem item = menuItemRepository.findAll().get(0);
    assertEquals("Margherita", item.getName());
    assertEquals("groupId", item.getGroupId());

    // update the menu item
    item.setName("Pepporoni");
    item.setGroupId("Pizza");

    menuItemRepository.save(item);
    assertEquals(1, menuItemRepository.findAll().size());

    item = menuItemRepository.findAll().get(0);
    assertEquals("Pepporoni", item.getName());
    assertEquals("Pizza", item.getGroupId());
  }

  @Test
  public void deleteMenuItem() {
    assertEquals(1, menuItemRepository.findAll().size());

    MenuItem item = menuItemRepository.findAll().get(0);
    assertNotNull(item);

    menuItemRepository.delete(item);

    assertEquals(0, menuItemRepository.findAll().size());
  }
}
