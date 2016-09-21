package dev.kasse.engine.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kasse.engine.server.entities.ShopTable;
import dev.kasse.engine.server.service.ShopTableService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RestController
@RequestMapping("/shopTable")
public class ShopTableRestController {

  @Autowired
  public ShopTableService shopTableService;

  @RequestMapping(method = { RequestMethod.POST })
  public void saveShopTable(@RequestParam(required=true) ShopTable shopTable) {
    shopTableService.saveTable(shopTable);
  }

  @RequestMapping(method = { RequestMethod.DELETE })
  public void deleteCustomer(@RequestParam(required=true) ShopTable table) {
    shopTableService.deleteTable(table);
  }

  @RequestMapping(path = "/all", method = { RequestMethod.GET })
  public List<ShopTable> getAllTables() {
    return shopTableService.getAll();
  }

  @RequestMapping(path = "/id", method = { RequestMethod.GET })
  public ShopTable getTableById(@RequestParam(required=true) String id) {
    return shopTableService.getById(id);
  }

  @RequestMapping(path = "/tableNumber", method = { RequestMethod.GET })
  public List<ShopTable> getTableByNumber(@RequestParam(required=true) int number) {
    return shopTableService.getByTableNumber(number);
  }

  @RequestMapping(path = "/floor", method = { RequestMethod.GET })
  public List<ShopTable> getTableByFloor(@RequestParam(required=true) int floor) {
    return shopTableService.getByFloor(floor);
  }

  @RequestMapping(path = "/numberandfloor", method = { RequestMethod.GET })
  public List<ShopTable> getTableByNumberAndFloor(@RequestParam(required=true) int number, @RequestParam(required=true) int floor) {
    return shopTableService.getByNumberAndFloor(number,floor);
  }
}
