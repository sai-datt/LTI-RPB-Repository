package com.movieplex.boot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.movieplex.boot.document.MovieDocument;

@Repository
public interface IMovieRepository extends MongoRepository<MovieDocument, String> {
}
