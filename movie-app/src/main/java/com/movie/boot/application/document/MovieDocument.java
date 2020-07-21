package com.movie.boot.application.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "movie")
public class MovieDocument {

	@Id
	private String id;
	private String name;
	private String category;
	private String producer;
	private String director;
	private String releaseDt;
	private String language;
	private List<String> multiplexNames;
}
