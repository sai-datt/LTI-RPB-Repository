package com.movieplex.boot.document;

import java.util.List;

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
@Document
public class MultiplexDocument {

	@Id
	private String id;
	private String name;
	private String address;
	private int numberOfScreens;
	private List<String> moviesList;
	private MovieDocument movie;

}
