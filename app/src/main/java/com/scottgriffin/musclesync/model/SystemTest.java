package com.scottgriffin.musclesync.model;

import com.scottgriffin.musclesync.dynamics.ContextData;
import com.scottgriffin.musclesync.dynamics.ContextEngine;
import com.scottgriffin.musclesync.dynamics.TestFlowEngine;
import com.scottgriffin.musclesync.utility.Input;
import com.scottgriffin.musclesync.utility.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemTest {
	private String systemTestId;
	private FunctionalSystem functionalSystem;
	private Map<String, Test> testMap;
	private List<String> testFlow;
	private ContextData contextData;

	public SystemTest(FunctionalSystem functionalSystem) {
		this.functionalSystem = functionalSystem;
		systemTestId = functionalSystem.getFunctionalSystemId() + "::ST";
		testMap = new HashMap<>();
		testFlow = new ArrayList<>();
	}

	public void addTestToMap(Test test) {
		testMap.put(test.getTestId(), test);
		testFlow.add(test.getTestId());
	}

	public FunctionalSystem getFunctionalSystem() {
		return functionalSystem;
	}

	public void setFunctionalSystem(FunctionalSystem functionalSystem) {
		this.functionalSystem = functionalSystem;
	}

	public Map<String, Test> getTestMap() {
		return testMap;
	}

	public List<String> getTestFlow() {
		return testFlow;
	}

	public void setTestFlow(List<String> testFlow) {
		List<String> filteredTestFlow = new ArrayList<>();
		for(String testId : testFlow) {
			if(testMap.containsKey(testId)) {
				filteredTestFlow.add(testId);
			}
		}
		this.testFlow = filteredTestFlow;
	}

	public void applyContextToTestFlow() {
		if (contextData != null) {
			TestFlowEngine testFlowEngine = new TestFlowEngine();
			List<String> result = testFlowEngine.applyTestFlow(this, contextData);
			if (result != null) {
				testFlow = result;
			}
		}
	}

	public void applyContextToTests() {
		if (contextData != null) {
			ContextEngine contextEngine = new ContextEngine();
			List<TestWithContext> result = contextEngine.applyContextToTests(this, contextData);
			for(Test test : result) {
				testMap.put(test.getTestId(), test);
			}
		}
	}

	public void runCLI() {
		// PRINT TITLE
		System.out.println("     __________________________________________");
		System.out.print("     |  ");
		System.out.print(Printer.toUpperSpaced(functionalSystem.getName()));
		System.out.println("  |  ");
		System.out.println("     ------------------------------------------\n");


		// USE CONTEXT TO SET TEST FLOW
		applyContextToTestFlow();

		// USE CONTEXT TO SET TEST CONTEXT
		applyContextToTests();

		// RUN TESTS
		runTestLoop();

		// SUBMIT RESULT
		System.out.println("{PLACE HOLDER] - SUBMIT RESULTS?");

		// UPDATE CONTEXT DATA
		if (contextData != null) {
			contextData.updateContextData(this);
		} else {
			contextData = new ContextData();
			contextData.updateContextData(this);
		}
		System.out.println(contextData);
		contextData.checkFlags();
	}

	private void runTestLoop() {
		for (String testId : testFlow) {
			if(!testMap.containsKey(testId)) {
				continue;
			}
			Test test = testMap.get(testId);
			System.out.println(test.getClass());
			test.printTest();
			int result;
			result = Input.nextInt();
			test.setResultValue(result);
			test.printTestResult();
			System.out.println("");
		}
	}

	public void setContextData(ContextData contextData) {
		this.contextData = contextData;
	}

	public String getSystemTestId() {
		return systemTestId;
	}
}
