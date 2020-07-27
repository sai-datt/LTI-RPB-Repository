package com.multiplex.boot.application.dto;

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
	private String id;
	private String name;
	private String address;
	private int numberOfScreens;
	private String movieName;
}
