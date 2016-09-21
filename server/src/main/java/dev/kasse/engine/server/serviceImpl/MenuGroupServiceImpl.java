package dev.kasse.engine.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kasse.engine.server.entities.MenuGroup;
import dev.kasse.engine.server.repository.query.MenuGroupRepository;
import dev.kasse.engine.server.service.MenuGroupService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@Service
public class MenuGroupServiceImpl implements MenuGroupService {

  @Autowired
  MenuGroupRepository menuGroupRepository;

  public List<MenuGroup> getAll() {
    return menuGroupRepository.findAll();
  }

  public MenuGroup getById(String id) {
    return menuGroupRepository.findById(id);
  }

  public List<MenuGroup> getByName(String name) {
    return menuGroupRepository.findByName(name);
  }

  public List<MenuGroup> getByCategoryId(String categoryId) {
    return menuGroupRepository.findByCategoryId(categoryId);
  }

  public List<MenuGroup> getByGroupId(int groupId) {
    return menuGroupRepository.findByGroupId(groupId);
  }
  
  public List<MenuGroup> getByGaenge(int gaenge) {
    return menuGroupRepository.findByGaenge(gaenge);
  }

  public void setMenuGroupRepository(
      MenuGroupRepository menuGroupRepository) {
    this.menuGroupRepository = menuGroupRepository;  
  }

  public void saveMenuGroup(MenuGroup group) {
    menuGroupRepository.save(group);
  }

  public void deleteMenuGroup(MenuGroup group) {
    menuGroupRepository.delete(group);  
  }

  public void deleteMenuGroupById(String groupId) {
    menuGroupRepository.delete(groupId); 
  }

  public void deleteAllMenuGroups() {
    menuGroupRepository.deleteAll(); 
  }
}
