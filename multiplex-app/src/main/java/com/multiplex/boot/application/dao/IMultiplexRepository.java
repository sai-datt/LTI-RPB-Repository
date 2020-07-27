package com.multiplex.boot.application.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.multiplex.boot.application.document.MultiplexDocument;

@Repository
public interface IMultiplexRepository extends MongoRepository<MultiplexDocument, String> {

}
