package com.movieplex.boot.ro;

import java.util.List;

import com.movieplex.boot.dto.MovieDTO;

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
public class MultiplexRO {
	private String name;
	private String address;
	private String numberOfScreens;
	private String director;
	private List<String> moviesList;
	private MovieDTO movieDto;

}
