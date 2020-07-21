package com.movie.boot.application.ro;

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
public class MultiplexRO {
	private String id;
	private String name;
	private String address;
	private int numberOfScreens;
	private List<MovieRO> moviesList;

}
