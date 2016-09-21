package dev.kasse.engine.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kasse.engine.server.entities.MenuCategory;
import dev.kasse.engine.server.repository.query.MenuCategoryRepository;
import dev.kasse.engine.server.service.MenuCategoryService;
import dev.kasse.engine.server.state.TicketType;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

  @Autowired
  MenuCategoryRepository menuCategoryRepository;

  public List<MenuCategory> getAll() {
    return menuCategoryRepository.findAll();
  }

  public MenuCategory getById(String id) {
    return menuCategoryRepository.findById(id);
  }

  public List<MenuCategory> getByName(String name) {
    return menuCategoryRepository.findByName(name);
  }

  public List<MenuCategory> getByCategoryId(int categoryId) {
    return menuCategoryRepository.findByCategoryId(categoryId);
  }

  public List<MenuCategory> getByType(TicketType type) {
    return menuCategoryRepository.findByType(type);
  }

  public List<MenuCategory> getByVisibility(boolean visible) {
    return menuCategoryRepository.findByVisible(visible);
  }

  public void setMenuCategoryRepository(
      MenuCategoryRepository menuCategoryRepository) {
    this.menuCategoryRepository = menuCategoryRepository;  
  }

  public void saveMenuCategory(MenuCategory category) {
    menuCategoryRepository.save(category);
  }

  public void deleteMenuCategory(MenuCategory category) {
    menuCategoryRepository.delete(category);
  }

  public void deleteMenuCategoryById(String categoryId) {
    menuCategoryRepository.delete(categoryId);
  }

  public void deleteAllMenuCategories() {
    menuCategoryRepository.deleteAll();  
  }
 }
