package com.movieplex.boot.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movieplex.boot.ro.MultiplexRO;

public interface IMultiplexRepository extends MongoRepository<MultiplexRO, String> {
	public MultiplexRO addMultiplex();

	public MultiplexRO updateMultiplexDetails();

	public MultiplexRO getMultiplexDetails();

	public List<MultiplexRO> getMultiplexList();

	public boolean deleteMultiplex();

}
