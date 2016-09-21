package dev.kasse.engine.server.test.MongoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@EnableMongoRepositories
public class SpringMongoConfiguration {

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(new MongoClient("localhost"), "repository");
  }

}