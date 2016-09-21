package dev.kasse.engine.server.test.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.ShopTable;
import dev.kasse.engine.server.repository.query.ShopTableRepository;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
public class ShopTableQueryTest extends AbstractTest {

  @Autowired
  protected ShopTableRepository shopTableRepository;

  @Before
  public void init() {
    shopTableRepository.deleteAll();
  }

  @Test
  public void queryByTableNumber() {
    shopTableRepository.save(createShopTable(1,1));
    shopTableRepository.save(createShopTable(2,1));
    
    List<ShopTable> shopTables = shopTableRepository.findAll();
    assertEquals(2, shopTables.size());

    shopTables = shopTableRepository.findByNumber(1);
    assertEquals(1, shopTables.size());

    shopTables = shopTableRepository.findByNumber(2);
    assertEquals(1, shopTables.size());

    shopTables = shopTableRepository.findByNumber(3);
    assertEquals(0, shopTables.size());
  }

  @Test
  public void queryById() {
    ShopTable table = shopTableRepository.save(createShopTable(1,1));
    assertNotNull(table);
 
    table = shopTableRepository.findOne(table.getId());
    assertNotNull(table);
  }
  
  @Test
  public void queryByFloor() {
    shopTableRepository.save(createShopTable(1,1));
    shopTableRepository.save(createShopTable(2,2));
    
    List<ShopTable> shopTables = shopTableRepository.findAll();
    assertEquals(2, shopTables.size());

    shopTables = shopTableRepository.findByFloor(1);
    assertEquals(1, shopTables.size());

    shopTables = shopTableRepository.findByFloor(2);
    assertEquals(1, shopTables.size());

    shopTables = shopTableRepository.findByFloor(3);
    assertEquals(0, shopTables.size());
  }

  @Test
  public void queryByNumberAndFloor() {
    shopTableRepository.save(createShopTable(1,1));
    shopTableRepository.save(createShopTable(2,2));
    shopTableRepository.save(createShopTable(3,1));
    
    List<ShopTable> shopTables = shopTableRepository.findAll();
    assertEquals(3, shopTables.size());

    shopTables = shopTableRepository.findByNumberAndFloor(1,1);
    assertEquals(1, shopTables.size());

    shopTables = shopTableRepository.findByNumberAndFloor(2,2);
    assertEquals(1, shopTables.size());

    shopTables = shopTableRepository.findByNumberAndFloor(3,1);
    assertEquals(1, shopTables.size());

    shopTables = shopTableRepository.findByNumberAndFloor(2,1);
    assertEquals(0, shopTables.size());
  }

}
