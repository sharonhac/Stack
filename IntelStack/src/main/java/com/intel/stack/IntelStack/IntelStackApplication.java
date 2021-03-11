package com.intel.stack.IntelStack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * we could add Synchronization on the Stack operations which update the stack
 * like push , pop , revert in order to maintain data correctness and enable
 * multi users changing the same Stack
 * 
 * we are supporting here several stacks being persisted to DB and accessed as
 * per the corresponding ID
 * 
 * since the data length (data on each Stack element) is less than 1kb we can
 * save ArrayList of those elements in DB (the amount of elements is also
 * limited by the Stack structure by definition - capacity)
 * 
 * 
 * Exception handling can be adjusted to a more detailed Exception classes -
 * more concrete instead of throwing the general Exception
 * 
 * we are using H2 DB internal , we can also change it to any other DB by
 * changing the needed in the application.properties and not need to create the
 * tables again after reset , it's simply used here for DEV purpose
 * 
 * 
 * @author hacham.sharon
 *
 */
@SpringBootApplication(scanBasePackages = { "com.intel.stack.IntelStack.repository" })
@EnableAutoConfiguration
public class IntelStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelStackApplication.class, args);
	}

}
