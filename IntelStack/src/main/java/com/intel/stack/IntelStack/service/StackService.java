package com.intel.stack.IntelStack.service;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intel.stack.IntelStack.entity.IntelStack;
import com.intel.stack.IntelStack.repository.IntelStackRepository;

@Service
public class StackService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StackService.class);

	private static final String SUCCESS = "SUCCESS";

	@Autowired
	private IntelStackRepository repository;

	public IntelStack pushToIntelStack(String data, long stackId) throws Exception {
		IntelStack stack = repository.findById(stackId).orElse(null);

		if (null != stack) {

			if (isFull(stack)) {
				System.out.println("unable to push to stack , as it's full");
				throw new Exception("unable to push to stack , as it's full");
			}

			System.out.println("Inserting " + data);
			stack.getStackElements().add(data);
			stack.setTop(stack.getTop() + 1);

			LOGGER.info("Successfully pushed value {0} to Stack {}", data, stackId);
			return repository.save(stack);

		}
		System.out.println("unable to push to stack , as unable to find the stack by ID");
		throw new Exception("unable to push to stack , as unable to find the stack by ID");

	}

	public long createNewStack() throws Exception {

		IntelStack stack = new IntelStack();
		stack.setTop(-1);
		IntelStack createdStack = repository.save(stack);
		if (null == createdStack) {
			System.out.println("unable to create the new Stack");
			throw new Exception("unable to create the new Stack");
		}
		LOGGER.info("Successfully created new Stack with ID - {0}", createdStack.getId());
		return createdStack.getId();
	}

	private Boolean isFull(IntelStack stack) {
		return stack.getTop() == stack.getCapacity() - 1;
	}

	public String popFromIntelStack(long stackId) throws Exception {
		IntelStack stack = repository.findById(stackId).orElse(null);

		if (null != stack && isEmpty(stack)) {
			System.out.println("unable to pop from stack , no items");
			throw new Exception("unable to pop from stack , no items");
		}

		System.out.println("Removing " + peek(stackId));
		stack.setTop(stack.getTop() - 1);
		String element = stack.getStackElements().get(stack.getStackElements().size());
		stack.getStackElements().remove(stack.getStackElements().size());
		LOGGER.info("Successfully pop element from Stack {0}", stackId);
		return element;
	}

	private Boolean isEmpty(IntelStack stack) {
		return stack.getTop() == -1;
	}

	public String peek(long stackId) throws Exception {
		IntelStack stack = repository.findById(stackId).orElse(null);
		String element = null;
		if (null != stack && !isEmpty(stack)) {
			element = stack.getStackElements().get(stack.getStackElements().size());
		} else {
			System.out.println("unable to peek stack , no items");
			throw new Exception("unable to peek stack , no items");
		}
		LOGGER.info("Successfully peek element from Stack {0}", stackId);
		return element;
	}

	public String revert(long stackId) throws Exception {
		IntelStack stack = repository.findById(stackId).orElse(null);

		if (null != stack && !isEmpty(stack)) {
			Collections.reverse((stack.getStackElements()));
		} else {
			System.out.println("unable to revert stack , as it's empty");
			throw new Exception("unable to revert stack , as it's empty");
		}
		LOGGER.info("Successfully reverted Stack {0}", stackId);
		repository.save(stack);
		return SUCCESS;
	}

}
