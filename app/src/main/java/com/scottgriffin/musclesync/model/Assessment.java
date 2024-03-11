package com.scottgriffin.musclesync.model;

import com.scottgriffin.musclesync.dynamics.ContextData;
import com.scottgriffin.musclesync.dynamics.TestFlowEngine;
import com.scottgriffin.musclesync.utility.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assessment {
	private String assessmentId;
	private List<SystemTest> assessmentSections;
	private Map<String, SystemTest> systemTestMap;
	private List<String> systemTestFlow;
	private ContextData contextData;


	public Assessment(String assessmentId) {
		this.assessmentId = assessmentId;
		assessmentSections = new ArrayList<>();
		systemTestMap = new HashMap<>();
		systemTestFlow = new ArrayList<>();
	}

	public void addSystemTestToMap(SystemTest systemTest) {
		String systemTestId = systemTest.getSystemTestId();
		systemTestMap.put(systemTestId, systemTest);
		systemTestFlow.add(systemTestId);
	}

	public void applyContextToSystemTestFlow() {
		if (contextData != null) {
			TestFlowEngine testFlowEngine = new TestFlowEngine();
			List<String> result = testFlowEngine.applySystemTestFlow(this, contextData);
			if (result != null) {
				systemTestFlow = result;
			}
		}
	}

	public void runCLI() {
		injectContextData();
		System.out.println("============================================================");
		System.out.println("    ========== " + Printer.toUpperSpaced(assessmentId) + " ==========");
		System.out.println("============================================================\n");

		// USE CONTEXT TO SET FLOW
		applyContextToSystemTestFlow();

		for (String systemTestId : systemTestFlow) {
			SystemTest systemTest = systemTestMap.get(systemTestId);
			systemTest.runCLI();
		}

		System.out.println("END TEST ASSESSMENT");
	}

	private void injectContextData() {
		for(SystemTest systemTest : systemTestMap.values()) {
			systemTest.setContextData(contextData);
		}
	}

	public void setContextData(ContextData contextData) {
		this.contextData = contextData;
	}
}
