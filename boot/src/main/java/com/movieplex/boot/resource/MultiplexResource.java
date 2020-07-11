package com.movieplex.boot.resource;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.movieplex.boot.dto.MultiplexDTO;

@Resource
@Component
public class MultiplexResource {

	public MultiplexDTO addMultiplex() {
		return null;
	}

	public MultiplexDTO getMultiplexDetails() {
		return null;
	}

	public List<MultiplexDTO> getMultiplexList() {
		return null;
	}

	public MultiplexDTO updateMultiplexDetails() {
		return null;
	}

	public boolean deleteMultiplex() {
		return true;
	}

}
