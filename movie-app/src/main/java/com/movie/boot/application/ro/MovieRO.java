package com.movie.boot.application.ro;

import java.util.Map;

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
public class MovieRO {
	private String id;
	private String name;
	private String category;
	private String producer;
	private String director;
	private String releaseDt;
	private String language;
	//Map with name and address of multiplex
	private Map<String, String> multiplexMap;

}

