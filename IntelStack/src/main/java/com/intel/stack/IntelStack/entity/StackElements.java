package com.intel.stack.IntelStack.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StackElements implements Serializable {

	/**
	 * object for allowing save of the Stack elements to the DB as Lob for better
	 * performance and utilization - it need to be Seralizable
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> stackElements = new ArrayList<String>();

	public List<String> getStackElements() {
		return stackElements;
	}

}
