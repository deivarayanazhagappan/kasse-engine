package dev.kasse.engine.server.test.query;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.MenuItem;
import dev.kasse.engine.server.repository.query.MenuItemRepository;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
public class MenuItemQueryTest {

  @Autowired
  protected MenuItemRepository menuItemRepository;

  @Before
  public void init() {
    menuItemRepository.deleteAll();
  }

  @Test
  public void queryAll() {

    assertEquals(0, menuItemRepository.findAll().size());
    
    menuItemRepository.save(createMenuItems());

    List<MenuItem> items = menuItemRepository.findAll();
    assertEquals(2, items.size());
  }

  @Test
  public void queryById() {

    assertEquals(0, menuItemRepository.findAll().size());
    
    menuItemRepository.save(createMenuItems());

    List<MenuItem> items = menuItemRepository.findAll();
    assertEquals(2, items.size());
    MenuItem item = items.get(0);
    assertEquals(item.getName(), menuItemRepository
          .findById(item.getId())
          .getName()); 
  }

  @Test
  public void queryByName() {

    menuItemRepository.save(createMenuItems());

    assertEquals(1, menuItemRepository.findByName("Pizza").size());
    assertEquals(1, menuItemRepository.findByName("Pasta").size());
    assertEquals(0, menuItemRepository.findByName("Vegan").size());
  }

  @Test
  public void queryByGroupId() {

    menuItemRepository.save(createMenuItems());

    assertEquals(2, menuItemRepository.findByGroupId("groupId").size());
    assertEquals(0, menuItemRepository.findByGroupId("100").size());
  }

  @Test
  public void queryByItemId() {

    menuItemRepository.save(createMenuItems());

    assertEquals(2, menuItemRepository.findByItemId("111").size());
    assertEquals(0, menuItemRepository.findByItemId("100").size());
  }

  private List<MenuItem> createMenuItems() {
    List<MenuItem> items = new ArrayList<MenuItem>();
    items.add(createMenuItem("Pizza"));
    items.add(createMenuItem("Pasta"));
    return items;
  }

  private MenuItem createMenuItem(String name) {
    MenuItem item = new MenuItem();

    item.setBarcode("12345");
    item.setGroupId("groupId");
    item.setItemId("111");
    item.setName(name);
    item.setPrice(2.50);
    item.setVariablePrice(true);
    return item;
  }
}
