package com.scottgriffin.musclesync.model;

import com.scottgriffin.musclesync.dynamics.ContextData;
import com.scottgriffin.musclesync.model.enums.ISA_RESULT;
import com.scottgriffin.musclesync.model.enums.PregnancyStatus;
import com.scottgriffin.musclesync.utility.DemoDataProvider;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentTest {
	DemoDataProvider demoDataProvider = new DemoDataProvider();

	@Test
	void runCLI() {
		String input = "-50\n-90\n10\n10\n-10"; // Simulate user input
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		System.out.println("TEST");
		Assessment assessment = demoDataProvider.getAssessment();

		ContextData contextData = new ContextData();
		contextData.setPregnancyStatus(PregnancyStatus.POSTPARTUM);
		assessment.setContextData(contextData);

		assessment.runCLI();
		System.out.println("END TEST");
	}
}