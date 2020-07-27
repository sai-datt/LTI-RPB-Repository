package com.multiplex.boot.application.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multiplex.boot.application.dto.MultiplexDTO;
import com.multiplex.boot.application.exception.MultiplexNotAddedException;
import com.multiplex.boot.application.exception.MultiplexException;
import com.multiplex.boot.application.exception.MultiplexNotFoundException;
import com.multiplex.boot.application.exception.MultiplexNotModifiedException;
import com.multiplex.boot.application.ro.MultiplexRO;
import com.multiplex.boot.application.service.IMultiplexService;

@Component
public class MultiplexResource {
	
	@Autowired
	IMultiplexService multiplexService;

	public MultiplexRO addMultiplex(MultiplexDTO multiplexDto) throws MultiplexNotAddedException{
		return multiplexService.addMultiplex(multiplexDto);
	}

	public MultiplexRO getMultiplex(String id) throws MultiplexNotFoundException{
		return multiplexService.getMultiplex(id);

	}

	public List<MultiplexRO> getMultiplexList() throws MultiplexException{
		return multiplexService.geMultiplexList();

	}

	public MultiplexRO updateMultiplex(MultiplexDTO multiplexDto) throws MultiplexNotModifiedException {
		return multiplexService.updateMultiplex(multiplexDto);

	}

	public boolean deleteMultiplex(String id) throws MultiplexException {
		return multiplexService.deleteMultiplex(id);

	}

}
