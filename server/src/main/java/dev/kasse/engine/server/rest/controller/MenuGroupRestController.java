package dev.kasse.engine.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kasse.engine.server.entities.MenuGroup;
import dev.kasse.engine.server.service.MenuGroupService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RestController
@RequestMapping("/menuGroup")
public class MenuGroupRestController {

  @Autowired
  public MenuGroupService menuGroupService;

  @RequestMapping(method = { RequestMethod.POST })
  public void saveMenuGroup(@RequestParam(required=true) MenuGroup group) {
    menuGroupService.saveMenuGroup(group);
  }

  @RequestMapping(method = { RequestMethod.DELETE })
  public void deleteMenuGroup(@RequestParam(required=true) MenuGroup group) {
    menuGroupService.deleteMenuGroup(group);
  }

  @RequestMapping(path = "/id", method = { RequestMethod.DELETE })
  public void deleteMenuGroupById(@RequestParam(required=true) String groupId) {
    menuGroupService.deleteMenuGroupById(groupId);
  }

  @RequestMapping(path = "/all", method = { RequestMethod.DELETE })
  public void deleteAllMenuGroups() {
    menuGroupService.deleteAllMenuGroups();
  }

  @RequestMapping(path = "/all", method = { RequestMethod.GET })
  public List<MenuGroup> getAllGroups() {
    return menuGroupService.getAll();
  }

  @RequestMapping(path = "/id", method = { RequestMethod.GET })
  public MenuGroup getById(@RequestParam(required=true) String id) {
    return menuGroupService.getById(id);
  }

  @RequestMapping(path = "/name", method = { RequestMethod.GET })
  public List<MenuGroup> getByName(@RequestParam(required=true) String name) {
    return menuGroupService.getByName(name);
  }

  @RequestMapping(path = "/categoryId", method = { RequestMethod.GET })
  public List<MenuGroup> getByCategoryId(@RequestParam(required=true) String categoryId) {
    return menuGroupService.getByCategoryId(categoryId);
  }

  @RequestMapping(path = "/groupId", method = { RequestMethod.GET })
  public List<MenuGroup> getByGroupId(@RequestParam(required=true) int groupId) {
    return menuGroupService.getByGroupId(groupId);
  }

  @RequestMapping(path = "/gaenge", method = { RequestMethod.GET })
  public List<MenuGroup> getByGaenge(@RequestParam(required=true) int gaenge) {
    return menuGroupService.getByGaenge(gaenge);
  }
}
