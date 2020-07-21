package com.boxoffice.application.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class JsonResponse {
	
	private String message;
	private String errorCode;
}
