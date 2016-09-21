package dev.kasse.engine.server.repository.query;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.kasse.engine.server.entities.MenuItem;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {

  public List<MenuItem> findAll();

  public MenuItem findById(String id);

  public List<MenuItem> findByName(String name);

  public List<MenuItem> findByGroupId(String groupId);

  public List<MenuItem> findByItemId(String itemId);
}
