package com.intel.stack.IntelStack.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intel.stack.IntelStack.controller.IntelStackController;

/**
 * since the data length (data element being saved on the Stack) is less than
 * 1kb we can save it to the DB note that usually we need to annotate it as
 * a @Lob for the disk in DB perspective , however Hibernate JPA takes care of
 * that auto for ArrayList<String> The collection - ArrayList is being saved to
 * the DB as TinyBlob
 * 
 * we can also implement the entity model with 2 tables - 1 for the Stack itself
 * with ID as key and 1 table for all the data elements - to be connected with a
 * foreign key of the Stack ID and the data element entity should have ID , data
 * , foreign key of the stack , ID of it's own and index in the current model
 * it's more simplified
 * 
 * @author hacham.sharon
 *
 */
@Entity
@Table(name = "INTEL_STACK")
public class IntelStack {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntelStack.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * either use @Lob with the StackElements class which is serializable or - to
	 * let Hibernate do the optimization with @ElementCollection or no annotation at
	 * all which saved the data as TinyBlob
	 * https://thecliftonian.wordpress.com/2012/04/09/problem-with-lob-annotation-and-string-type/
	 * https://stackoverflow.com/questions/39308457/what-is-the-best-way-of-store-arrays-with-hibernate
	 */
	// @ElementCollection
	@Lob
	private StackElements stackElements = new StackElements();
	private int top = -1;

	/**
	 * need to maintain a fixed size as specified by the Stack specification
	 */
	private final int CAPACITY = 50;

	public long getId() {
		return id;
	}

	public List<String> getStackElements() {
		return stackElements.getStackElements();
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getCapacity() {
		return CAPACITY;
	}
}
