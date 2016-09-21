package dev.kasse.engine.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kasse.engine.server.entities.MenuCategory;
import dev.kasse.engine.server.service.MenuCategoryService;
import dev.kasse.engine.server.state.TicketType;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RestController
@RequestMapping("/menuCategory")
public class MenuCategoryRestController {

  @Autowired
  public MenuCategoryService menuCategoryService;

  @RequestMapping(method = { RequestMethod.POST })
  public void saveMenuCategory(@RequestParam(required=true) MenuCategory category) {
    menuCategoryService.saveMenuCategory(category);
  }

  @RequestMapping(method = { RequestMethod.DELETE })
  public void deleteMenuCategory(@RequestParam(required=true) MenuCategory category) {
    menuCategoryService.deleteMenuCategory(category);
  }

  @RequestMapping(path = "/id", method = { RequestMethod.DELETE })
  public void deleteMenuCategoryById(@RequestParam(required=true) String categoryId) {
    menuCategoryService.deleteMenuCategoryById(categoryId);
  }

  @RequestMapping(path = "/all", method = { RequestMethod.DELETE })
  public void deleteAllMenuCategories() {
    menuCategoryService.deleteAllMenuCategories();
  }

  @RequestMapping(path = "/all", method = { RequestMethod.GET })
  public List<MenuCategory> getAllCategories() {
    return menuCategoryService.getAll();
  }

  @RequestMapping(path = "/id", method = { RequestMethod.GET })
  public MenuCategory getById(@RequestParam(required=true) String id) {
    return menuCategoryService.getById(id);
  }

  @RequestMapping(path = "/name", method = { RequestMethod.GET })
  public List<MenuCategory> getByName(@RequestParam(required=true) String name) {
    return menuCategoryService.getByName(name);
  }

  @RequestMapping(path = "/categoryId", method = { RequestMethod.GET })
  public List<MenuCategory> getByCategoryId(@RequestParam(required=true) int categoryId) {
    return menuCategoryService.getByCategoryId(categoryId);
  }

  @RequestMapping(path = "/visible", method = { RequestMethod.GET })
  public List<MenuCategory> getByVisible(@RequestParam(required=true) boolean visible) {
    return menuCategoryService.getByVisibility(visible);
  }

  @RequestMapping(path = "/type", method = { RequestMethod.GET })
  public List<MenuCategory> getByType(@RequestParam(required=true) TicketType type) {
    return menuCategoryService.getByType(type);
  }
}
