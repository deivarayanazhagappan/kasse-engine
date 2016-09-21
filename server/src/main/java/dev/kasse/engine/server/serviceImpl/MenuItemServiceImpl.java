package dev.kasse.engine.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kasse.engine.server.entities.MenuItem;
import dev.kasse.engine.server.repository.query.MenuItemRepository;
import dev.kasse.engine.server.service.MenuItemService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

  @Autowired
  MenuItemRepository menuItemRepository;

  public List<MenuItem> getAll() {
    return menuItemRepository.findAll();
  }

  public MenuItem getById(String id) {
    return menuItemRepository.findById(id);
  }

  public List<MenuItem> getByName(String name) {
    return menuItemRepository.findByName(name);
  }

  public List<MenuItem> getByItemId(String itemId) {
    return menuItemRepository.findByItemId(itemId);
  }

  public List<MenuItem> getByGroupId(String groupId) {
    return menuItemRepository.findByGroupId(groupId);
  }
  
  public void setMenuItemRepository(
      MenuItemRepository menuItemRepository) {
    this.menuItemRepository = menuItemRepository;  
  }

  public void saveMenuItem(MenuItem item) {
    menuItemRepository.save(item);
  }

  public void deleteMenuItem(MenuItem item) {
    menuItemRepository.delete(item);  
  }

  public void deleteMenuItemById(String itemId) {
    menuItemRepository.delete(itemId); 
  }

  public void deleteAllMenuItems() {
    menuItemRepository.deleteAll(); 
  }
}
