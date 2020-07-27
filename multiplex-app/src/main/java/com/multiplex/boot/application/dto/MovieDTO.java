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
public class MovieDTO {
	private String id;
	private String name;
	private String category;
	private String producer;
	private String director;
	private String releaseDt;
	private String language;
	private String multiplexName;

}
