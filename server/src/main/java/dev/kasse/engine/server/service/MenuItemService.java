package dev.kasse.engine.server.service;

import java.util.List;

import dev.kasse.engine.server.entities.MenuItem;
import dev.kasse.engine.server.repository.query.MenuItemRepository;

public interface MenuItemService {

  //POST service
  public void saveMenuItem(MenuItem item);

  public void deleteMenuItem(MenuItem item);

  public void deleteMenuItemById(String itemId);

  public void deleteAllMenuItems();
 
  // GET service
  public List<MenuItem> getAll();

  public MenuItem getById(String id);

  public List<MenuItem> getByName(String name);

  public List<MenuItem> getByItemId(String itemId);

  public List<MenuItem> getByGroupId(String groupId);

  public void setMenuItemRepository(MenuItemRepository menuItemRepository);
}
