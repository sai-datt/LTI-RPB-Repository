package com.movieplex.boot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "movie")
public class MovieDocument {

	@Id
	private String id;
	private String name;
	private String category;
	private String producer;
	private String director;
	private String releaseDt;
	private String genre;
}
