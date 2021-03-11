package com.intel.stack.IntelStack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intel.stack.IntelStack.entity.IntelStack;
import com.intel.stack.IntelStack.service.StackService;

@RestController
public class IntelStackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntelStackController.class);

	@Autowired
	private StackService service;


	@GetMapping("/peekStackById/{id}")
	public String peekByStackId(@PathVariable long stackId) {
		try {
			return service.peek(stackId);
		} catch (Exception e) {
			LOGGER.error("Error in peekByStackId - " + e.getStackTrace());
			return "Error in peekByStackId - " + e.getMessage();
		}
	}

	/**
	 * return the ID of the new Stack -1 indicates a failure in creating the Stack
	 * 
	 * @return
	 */
	@PostMapping("/createStack")
	public long createStack() {
		try {
			return service.createNewStack();
		} catch (Exception e) {
			LOGGER.error("Error in createStack - " + e.getStackTrace());
			return -1;
		}

	}

	/**
	 * need to add exception handling at client side or to create new Exception type
	 * 
	 * @param data
	 * @param stackId
	 * @return
	 */
	@PostMapping("/pushToStackById/{id}")
	public IntelStack pushToStackById(@RequestBody String data, @PathVariable long stackId) {
		try {
			return service.pushToIntelStack(data, stackId);
		} catch (Exception e) {
			LOGGER.error("Error in pushToStackById - " + e.getStackTrace());
			return null;
		}
	}

	@PostMapping("/popByStackId/{id}")
	public String popByStackId(@PathVariable long stackId) {
		try {
			return service.popFromIntelStack(stackId);
		} catch (Exception e) {
			LOGGER.error("Error in popByStackId - " + e.getStackTrace());
			return "Error in popByStackId - " + e.getMessage();
		}
	}

	@PostMapping("/revertByStackId/{id}")
	public String revertByStackId(@PathVariable long stackId) {
		try {
			return service.revert(stackId);
		} catch (Exception e) {
			LOGGER.error("Error in revertByStackId - " + e.getStackTrace());
			return "Error in revertByStackId - " + e.getMessage();
		}
	}
}
