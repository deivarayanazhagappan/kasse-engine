package dev.kasse.engine.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kasse.engine.server.entities.ShopTable;
import dev.kasse.engine.server.repository.query.ShopTableRepository;
import dev.kasse.engine.server.service.ShopTableService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@Service
public class ShopTableServiceImpl implements ShopTableService {

  @Autowired
  ShopTableRepository shopTableRepository;

  public void saveTable(ShopTable table) {
    shopTableRepository.save(table);
  }

  public void deleteTable(ShopTable table) {
    shopTableRepository.delete(table);
  }

  public void deleteTableById(String tableId) {
    shopTableRepository.delete(tableId);
  }

  public void deleteAllTables() {
    shopTableRepository.deleteAll();
  }

  public List<ShopTable> getAll() {
    return shopTableRepository.findAll();
  }

  public ShopTable getById(String id) {
    return shopTableRepository.findOne(id);
  }

  public List<ShopTable> getByTableNumber(int tableNumber) {
    return shopTableRepository.findByNumber(tableNumber); 
  }

  public List<ShopTable> getByFloor(int floor) {
    return shopTableRepository.findByFloor(floor);
  }

  public List<ShopTable> getByNumberAndFloor(int number, int floor) {
    return shopTableRepository.findByNumberAndFloor(number,floor); 
  }

  public void setShopTableRepository(ShopTableRepository shopTableRepository) {
    this.shopTableRepository = shopTableRepository;
  }
 }
