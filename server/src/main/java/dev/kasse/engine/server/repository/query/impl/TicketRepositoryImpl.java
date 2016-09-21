package dev.kasse.engine.server.repository.query.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import dev.kasse.engine.server.repository.query.custom.TicketRepositoryCustom;

public class TicketRepositoryImpl implements TicketRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;
}
