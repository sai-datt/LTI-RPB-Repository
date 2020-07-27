package com.multiplex.boot.application.document;

import java.util.List;

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
@Document(collection = "multiplex")
public class MultiplexDocument {

	private String id;
	private String name;
	private String address;
	private int numberOfScreens;
	private List<String> movieNames;
}
