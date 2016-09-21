package dev.kasse.engine.server.repository.query;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.kasse.engine.server.entities.MenuCategory;
import dev.kasse.engine.server.state.TicketType;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface MenuCategoryRepository extends MongoRepository<MenuCategory, String> {

  public List<MenuCategory> findAll();

  public MenuCategory findById(String id);

  public List<MenuCategory> findByName(String name);

  public List<MenuCategory> findByCategoryId(int categoryId);

  public List<MenuCategory> findByType(TicketType ticketType);

  public List<MenuCategory> findByVisible(boolean visible);
}
