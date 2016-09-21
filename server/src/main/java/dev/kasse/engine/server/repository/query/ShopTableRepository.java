package dev.kasse.engine.server.repository.query;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.kasse.engine.server.entities.ShopTable;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface ShopTableRepository extends MongoRepository<ShopTable, String> {

  public List<ShopTable> findAll();

  public List<ShopTable> findByNumber(int tableNumber);

  public List<ShopTable> findByFloor(int floor);

  public List<ShopTable> findByNumberAndFloor(int tableNumber, int floor);
}
