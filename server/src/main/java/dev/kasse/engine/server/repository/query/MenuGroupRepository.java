package dev.kasse.engine.server.repository.query;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.kasse.engine.server.entities.MenuGroup;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface MenuGroupRepository extends MongoRepository<MenuGroup, String> {

  public List<MenuGroup> findAll();

  public MenuGroup findById(String id);

  public List<MenuGroup> findByName(String name);

  public List<MenuGroup> findByCategoryId(String categoryId);

  public List<MenuGroup> findByGroupId(int groupId);
  
  public List<MenuGroup> findByGaenge(int gaenge);
}
