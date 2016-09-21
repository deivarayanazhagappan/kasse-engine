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

import dev.kasse.engine.server.entities.MenuGroup;
import dev.kasse.engine.server.repository.query.MenuGroupRepository;
import dev.kasse.engine.server.test.MockProvider.MenuGroupMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@EnableMongoRepositories("com.kasse.engine.Repository")
public class MenuGroupEntityTest {

  @Autowired
  private MenuGroupRepository menuGroupRepository;

  @Before
  public void init() {
    menuGroupRepository.deleteAll();
    menuGroupRepository.save(MenuGroupMockProvider.createMenuGroup("Margherita"));
  }

  @Test
  public void insertMenuGroup() {
    assertEquals(1, menuGroupRepository.findAll().size());

    MenuGroup group = menuGroupRepository.findAll().get(0);
    assertEquals("999", group.getCategoryId());
    assertEquals(1, group.getGaenge());
    assertEquals(111, group.getGroupId());
    assertEquals("Margherita", group.getName());
    assertNotNull(group.getId());
  }

  @Test
  public void updateMenuGroup() {
    
    assertEquals(1, menuGroupRepository.findAll().size());

    MenuGroup group = menuGroupRepository.findAll().get(0);
    assertEquals("Margherita", group.getName());
    assertEquals("999", group.getCategoryId());

    // update the menu category
    group.setName("Vegan");
    group.setCategoryId("555");

    menuGroupRepository.save(group);
    assertEquals(1, menuGroupRepository.findAll().size());

    group = menuGroupRepository.findAll().get(0);
    assertEquals("Vegan", group.getName());
    assertEquals("555", group.getCategoryId());
  }

  @Test
  public void deleteMenuGroup() {
    assertEquals(1, menuGroupRepository.findAll().size());

    MenuGroup group = menuGroupRepository.findAll().get(0);
    assertNotNull(group);

    menuGroupRepository.delete(group);

    assertEquals(0, menuGroupRepository.findAll().size());
  }
}
