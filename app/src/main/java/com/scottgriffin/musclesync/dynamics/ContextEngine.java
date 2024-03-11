package com.scottgriffin.musclesync.dynamics;

import com.scottgriffin.musclesync.model.SystemTest;
import com.scottgriffin.musclesync.model.Test;
import com.scottgriffin.musclesync.model.TestWithContext;
import com.scottgriffin.musclesync.model.enums.ISA_RESULT;
import com.scottgriffin.musclesync.model.enums.ROTATION_RESULT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextEngine {

	public List<TestWithContext> applyContextToTests(SystemTest systemTest, ContextData contextData) {
		System.out.println(" ~~ CONTEXT ENGINE ~~");
		System.out.println(" applyContextToTests() ");
		String id = systemTest.getFunctionalSystem().getFunctionalSystemId();
		List<TestWithContext> resultTests = new ArrayList<>();
		switch (id) {
			case "ISA":
				break;
			case "HROM":
				Test primaryTest;
				Test secondaryTest;
				if(contextData.isaResult == ISA_RESULT.WIDE) {
					primaryTest = systemTest.getTestMap().get("HROM01");
					secondaryTest = systemTest.getTestMap().get("HROM02");
				} else if (contextData.isaResult== ISA_RESULT.NARROW) {
					primaryTest = systemTest.getTestMap().get("HROM02");
					secondaryTest = systemTest.getTestMap().get("HROM01");
				} else {
					return resultTests;
				}
				TestWithContext primaryWithContext = new TestWithContext(primaryTest);
				TestWithContext secondaryWithContext = new TestWithContext(secondaryTest);
				primaryWithContext.setPrimary(true);
				primaryWithContext.setExpectedResult(ROTATION_RESULT.LIMITED);
				primaryWithContext.setFlaggedResult(ROTATION_RESULT.EXCESSIVE);
				secondaryWithContext.setPrimary(false);
				secondaryWithContext.setExpectedResult(ROTATION_RESULT.SUFFICIENT);
				secondaryWithContext.setFlaggedResult(ROTATION_RESULT.EXCESSIVE);
				resultTests.add(primaryWithContext);
				resultTests.add(secondaryWithContext);
				break;
			case "SROM":
				break;
			case "PO":
				break;
			case "STK":
				break;
			default:
				System.out.println("DEFAULT");
		}

		return resultTests;
	}
}
