package com.multiplex.boot.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multiplex.boot.application.dto.MultiplexDTO;
import com.multiplex.boot.application.exception.MultiplexNotAddedException;
import com.multiplex.boot.application.exception.MultiplexException;
import com.multiplex.boot.application.exception.MultiplexNotFoundException;
import com.multiplex.boot.application.exception.MultiplexNotModifiedException;
import com.multiplex.boot.application.resource.MultiplexResource;
import com.multiplex.boot.application.ro.MultiplexRO;

@RestController
@RequestMapping("/multiplex-api")
public class MultiplexController {

	@Autowired
	MultiplexResource multiplexResource;

	public static Logger logger = LoggerFactory.getLogger(MultiplexController.class);

	@RequestMapping("")
	public ResponseEntity<String> healthCheck() {
		String status = "Multiplex is open!";
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@PostMapping("/addMultiplex")
	public ResponseEntity<MultiplexRO> addMultiplex(@RequestBody MultiplexDTO multiplexDto)
			throws MultiplexNotAddedException {

		ResponseEntity<MultiplexRO> response = null;
		MultiplexRO multiplexRo = multiplexResource.addMultiplex(multiplexDto);
		if (multiplexRo != null) {
			response = new ResponseEntity<MultiplexRO>(multiplexRo, HttpStatus.OK);
		} else {
			logger.error("Error while adding multiplex details with name:" + multiplexDto.getName());
			throw new MultiplexNotAddedException("Error while adding multiplex with name:" + multiplexDto.getName());
		}
		return response;
	}

	@GetMapping("/getMultiplex")
	public ResponseEntity<MultiplexRO> getMultiplex(@RequestParam(name = "id") String id)
			throws MultiplexNotFoundException {

		ResponseEntity<MultiplexRO> response = null;

		MultiplexRO multiplexRo = multiplexResource.getMultiplex(id);
		if (multiplexRo != null) {
			response = new ResponseEntity<MultiplexRO>(multiplexRo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<MultiplexRO>(multiplexRo, HttpStatus.OK);
			/*
			 * throw new MultiplexNotFoundException(
			 * "Please verify the multiplex id and try again!\n Given multiplex id is:" +
			 * id);
			 */
		}

		return response;
	}

	@GetMapping("/getMultiplexList")
	public ResponseEntity<List<MultiplexRO>> getMultiplexList() throws MultiplexException {

		ResponseEntity<List<MultiplexRO>> response = null;
		List<MultiplexRO> multiplexList = multiplexResource.getMultiplexList();

		if (multiplexList != null) {
			response = new ResponseEntity<List<MultiplexRO>>(multiplexList, HttpStatus.OK);
		} else {
			logger.error("Error while retrieving multiplexes list");
			throw new MultiplexException("There are no multiplexes at present or cannot retrieve multiplexes list. "
					+ "\n Please try after sometime!");
		}

		return response;
	}

	@PutMapping("/updateMultiplex")
	public ResponseEntity<MultiplexRO> updateMultiplex(@RequestBody MultiplexDTO multiplexDto)
			throws MultiplexNotModifiedException {

		ResponseEntity<MultiplexRO> response = null;

		MultiplexRO multiplexRo = multiplexResource.updateMultiplex(multiplexDto);

		if (multiplexRo != null) {
			response = new ResponseEntity<MultiplexRO>(multiplexRo, HttpStatus.OK);
		} else {
			logger.error("Error while updating multiplex details with id:" + multiplexDto.getId());
			throw new MultiplexNotModifiedException("Error while updating details of the multiplex "
					+ multiplexDto.getName() + "having id " + multiplexDto.getId());
		}

		return response;
	}

	@DeleteMapping("/deleteMultiplex")
	public ResponseEntity<List<Object>> deleteMultiplex(@RequestParam(name = "id") String id)
			throws MultiplexException {

		ResponseEntity<List<Object>> response = null;
		List<Object> responseObject = new ArrayList<Object>();

		boolean status = multiplexResource.deleteMultiplex(id);

		if (status) {

			responseObject.add("Multiplex with id:" + id + "deleted successfully!");
			responseObject.add(HttpStatus.OK);
			responseObject.add("Deletion status: SUCCESS");

			response = new ResponseEntity<List<Object>>(responseObject, HttpStatus.OK);
		} else {

			responseObject.add("Multiplex with id:" + id + " not found! Please verify the multiplex ID once");
			responseObject.add(HttpStatus.NOT_IMPLEMENTED);
			responseObject.add("Deletion status: FAILURE");

			throw new MultiplexException(responseObject.toString());

			// response = new ResponseEntity<List<Object>>(responseObject, HttpStatus.OK);

		}
		return response;
	}

}
