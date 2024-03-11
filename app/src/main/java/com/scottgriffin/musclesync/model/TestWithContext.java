package com.scottgriffin.musclesync.model;

import com.scottgriffin.musclesync.model.enums.RESULT_ENUM;
import com.scottgriffin.musclesync.utility.Printer;

public class TestWithContext extends Test {
	private Boolean isPrimary;
	private RESULT_ENUM expectedResult;
	private RESULT_ENUM flaggedResult;

	public TestWithContext(Test test) {
		super(test.getTestId(), test.getTestName(), test.getPosition(), test.getDescription(), test.getMediaURL(), test.getResultOptions());
	}

	public Boolean getPrimary() {
		return isPrimary;
	}

	public void setPrimary(Boolean primary) {
		isPrimary = primary;
	}

	public RESULT_ENUM getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(RESULT_ENUM expectedResult) {
		this.expectedResult = expectedResult;
	}

	public RESULT_ENUM getFlaggedResult() {
		return flaggedResult;
	}

	public void setFlaggedResult(RESULT_ENUM flaggedResult) {
		this.flaggedResult = flaggedResult;
	}

	@Override
	public void printTest() {
		System.out.println(" - - - " + Printer.toUpperSpaced(super.getTestName()) + " - - - " );
		System.out.println("Position: " + super.getPosition());
		if (isPrimary) {
			System.out.print("PRIMARY | ");
		} else {
			System.out.println("SECONDARY | ");
		}
		System.out.println(super.getDescription());
		System.out.println("Expected: " + expectedResult);
		System.out.println("Flaggable: " + flaggedResult);
		System.out.println(super.getResultOptions().getOptionsString());
	}

	@Override
	public void printTestResult() {
		int resultValue = super.getResultValue();
		RESULT_ENUM result = super.getResultOptions().getResult(resultValue);
		String contextStr = "";
		if (result == expectedResult) {
			contextStr = " (EXPECTED) ";
		} else if (result == flaggedResult) {
			contextStr = " (FLAGGED) ";
		}

		System.out.println("RESULT: " +
				super.getResultValue() + " " +
				super.getResultOptions().getResult(super.getResultValue()) + contextStr +"\n");
	}

}
