package com.movieplex.boot.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class MultiplexDTO {
	private String name;
	private String address;
	private String numberOfScreens;
	private String director;
	private List<String> moviesList;
	private MovieDTO movieDto;
}
