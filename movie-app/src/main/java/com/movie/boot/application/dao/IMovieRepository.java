package com.movie.boot.application.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.movie.boot.application.document.MovieDocument;

@Repository
public interface IMovieRepository extends MongoRepository<MovieDocument, String>{

}
