package com.multiplex.boot.application.service;

import java.util.List;

import com.multiplex.boot.application.dto.MultiplexDTO;
import com.multiplex.boot.application.exception.MultiplexException;
import com.multiplex.boot.application.exception.MultiplexNotAddedException;
import com.multiplex.boot.application.exception.MultiplexNotFoundException;
import com.multiplex.boot.application.exception.MultiplexNotModifiedException;
import com.multiplex.boot.application.ro.MultiplexRO;

public interface IMultiplexService {

	MultiplexRO addMultiplex(MultiplexDTO multiplexDto) throws MultiplexNotAddedException;

	MultiplexRO getMultiplex(String id) throws MultiplexNotFoundException;

	List<MultiplexRO> geMultiplexList() throws MultiplexException;

	MultiplexRO updateMultiplex(MultiplexDTO multiplexDto) throws MultiplexNotModifiedException;

	boolean deleteMultiplex(String id) throws MultiplexException;

}
