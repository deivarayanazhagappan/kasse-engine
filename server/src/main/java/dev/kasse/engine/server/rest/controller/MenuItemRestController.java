package dev.kasse.engine.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kasse.engine.server.entities.MenuItem;
import dev.kasse.engine.server.service.MenuItemService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RestController
@RequestMapping("/menuItem")
public class MenuItemRestController {

  @Autowired
  public MenuItemService menuItemService;

  @RequestMapping(method = { RequestMethod.POST })
  public void saveMenuItem(@RequestParam(required=true) MenuItem item) {
    menuItemService.saveMenuItem(item);
  }

  @RequestMapping(method = { RequestMethod.DELETE })
  public void deleteMenuItem(@RequestParam(required=true) MenuItem item) {
    menuItemService.deleteMenuItem(item);
  }

  @RequestMapping(path = "/id", method = { RequestMethod.DELETE })
  public void deleteMenuItemById(@RequestParam(required=true) String itemId) {
    menuItemService.deleteMenuItemById(itemId);
  }

  @RequestMapping(path = "/all", method = { RequestMethod.DELETE })
  public void deleteAllMenuItems() {
    menuItemService.deleteAllMenuItems();
  }

  @RequestMapping(path = "/all", method = { RequestMethod.GET })
  public List<MenuItem> getAllItems() {
    return menuItemService.getAll();
  }

  @RequestMapping(path = "/id", method = { RequestMethod.GET })
  public MenuItem getById(@RequestParam(required=true) String id) {
    return menuItemService.getById(id);
  }

  @RequestMapping(path = "/name", method = { RequestMethod.GET })
  public List<MenuItem> getByName(@RequestParam(required=true) String name) {
    return menuItemService.getByName(name);
  }

  @RequestMapping(path = "/groupId", method = { RequestMethod.GET })
  public List<MenuItem> getByGroupId(@RequestParam(required=true) String groupId) {
    return menuItemService.getByGroupId(groupId);
  }

  @RequestMapping(path = "/itemId", method = { RequestMethod.GET })
  public List<MenuItem> getByItemId(@RequestParam(required=true) String itemId) {
    return menuItemService.getByItemId(itemId);
  }
}
