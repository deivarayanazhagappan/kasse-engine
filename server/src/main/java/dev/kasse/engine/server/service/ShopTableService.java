package dev.kasse.engine.server.service;

import java.util.List;

import dev.kasse.engine.server.entities.ShopTable;
import dev.kasse.engine.server.repository.query.ShopTableRepository;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface ShopTableService {

  // POST service
  public void saveTable(ShopTable table);

  public void deleteTable(ShopTable table);

  public void deleteTableById(String tableId);

  public void deleteAllTables();
  
  // GET service
  public List<ShopTable> getAll();

  public ShopTable getById(String id);

  public List<ShopTable> getByTableNumber(int tableNumber);

  public List<ShopTable> getByFloor(int floor);

  public List<ShopTable> getByNumberAndFloor(int number, int floor);
 
  public void setShopTableRepository(ShopTableRepository shopTableRepository);
}
