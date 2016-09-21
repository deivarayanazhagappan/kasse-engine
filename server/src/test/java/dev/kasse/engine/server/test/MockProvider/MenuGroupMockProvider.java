package dev.kasse.engine.server.test.MockProvider;

import java.util.ArrayList;
import java.util.List;

import dev.kasse.engine.server.entities.MenuGroup;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public class MenuGroupMockProvider {

  public static List<MenuGroup> createMenuGroups() {
    List<MenuGroup> groups = new ArrayList<MenuGroup>();
    groups.add(createMenuGroup("Margherita"));
    groups.add(createMenuGroup("Pepporoni"));

    return groups;
  }

  public static MenuGroup createMenuGroup(String name) {
    MenuGroup group = new MenuGroup();
    group.setCategoryId("999");
    group.setId("groupId");
    group.setGroupId(111);
    group.setName(name);
    group.setGaenge(1);
    return group;
  }
}
