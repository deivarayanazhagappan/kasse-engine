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

import dev.kasse.engine.server.entities.MenuGroup;
import dev.kasse.engine.server.repository.query.MenuGroupRepository;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
public class MenuGroupQueryTest {

  @Autowired
  protected MenuGroupRepository menuGroupRepository;

  @Before
  public void init() {
    menuGroupRepository.deleteAll();
  }

  @Test
  public void queryAll() {

    assertEquals(0, menuGroupRepository.findAll().size());
    
    menuGroupRepository.save(createMenuGroups());

    List<MenuGroup> groups = menuGroupRepository.findAll();
    assertEquals(2, groups.size());
  }

  @Test
  public void queryById() {

    assertEquals(0, menuGroupRepository.findAll().size());
    
    menuGroupRepository.save(createMenuGroups());

    List<MenuGroup> groups = menuGroupRepository.findAll();
    assertEquals(2, groups.size());
    MenuGroup group = groups.get(0);
    assertEquals(group.getName(), menuGroupRepository
          .findById(group.getId())
          .getName()); 
  }

  @Test
  public void queryByName() {

    menuGroupRepository.save(createMenuGroups());

    assertEquals(1, menuGroupRepository.findByName("Margherita").size());
    assertEquals(1, menuGroupRepository.findByName("Pepporoni").size());
    assertEquals(0, menuGroupRepository.findByName("Vegan").size());
  }

  @Test
  public void queryByCategoryId() {

    menuGroupRepository.save(createMenuGroups());

    assertEquals(2, menuGroupRepository.findByCategoryId("999").size());
    assertEquals(0, menuGroupRepository.findByCategoryId("100").size());
  }

  @Test
  public void queryByGroupId() {

    menuGroupRepository.save(createMenuGroups());

    assertEquals(2, menuGroupRepository.findByGroupId(111).size());
    assertEquals(0, menuGroupRepository.findByGroupId(100).size());
  }

  @Test
  public void queryByGaenge() {

    menuGroupRepository.save(createMenuGroups());

    assertEquals(2, menuGroupRepository.findByGaenge(1).size());
    assertEquals(0, menuGroupRepository.findByGaenge(2).size());
  }

  private List<MenuGroup> createMenuGroups() {
    List<MenuGroup> groups = new ArrayList<MenuGroup>();
    groups.add(createMenuGroup("Margherita"));
    groups.add(createMenuGroup("Pepporoni"));

    return groups;
  }

  private MenuGroup createMenuGroup(String name) {
    MenuGroup group = new MenuGroup();
    group.setCategoryId("999");
    group.setGroupId(111);
    group.setName(name);
    group.setGaenge(1);
    return group;
  }
}
