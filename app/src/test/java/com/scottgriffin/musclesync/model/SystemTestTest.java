package com.scottgriffin.musclesync.model;

import com.scottgriffin.musclesync.dynamics.ContextData;
import com.scottgriffin.musclesync.model.enums.ISA_RESULT;
import com.scottgriffin.musclesync.utility.DemoDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SystemTestTest {
	DemoDataProvider demoDataProvider;

	@BeforeEach
	void setup() {
		demoDataProvider = new DemoDataProvider();
	}

	// DIRECTLY CHECK TEST FLOW
	@Test
	void setTestFlow() {
		SystemTest isaSystemTest = demoDataProvider.getSystemTestMap().get("ISA");
		isaSystemTest.setTestFlow(List.of("ISA02" , "ISA01"));
		isaSystemTest.setTestFlow(List.of("ISA011" , "ISA02"));
	}

	// CHECK TEST FLOW LOGIC
	@Test
	void runHromWithContext() {
		String input = "87\n80"; // Simulate user input
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		SystemTest hromSystemTest = demoDataProvider.getSystemTestMap().get("HROM");

		ContextData contextData = new ContextData();
		contextData.setIsaResult(ISA_RESULT.NARROW );
		hromSystemTest.setContextData(contextData);

		hromSystemTest.runCLI();
	}


	@Test
	void runHromNoContext() {
		String input = "87\n80"; // Simulate user input
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		SystemTest hromSystemTest = demoDataProvider.getSystemTestMap().get("HROM");

		hromSystemTest.runCLI();
	}

	@Test
	void runHromNeutralIsa() {
		String input = "87\n80"; // Simulate user input
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		SystemTest hromSystemTest = demoDataProvider.getSystemTestMap().get("HROM");

		ContextData contextData = new ContextData();
		contextData.setIsaResult(ISA_RESULT.NEUTRAL );
		hromSystemTest.setContextData(contextData);

		hromSystemTest.runCLI();
	}
}