package com.scottgriffin.musclesync.model;

import com.scottgriffin.musclesync.model.resulttypes.ResultOptions;
import com.scottgriffin.musclesync.utility.Printer;

public class Test {
	private String testId;
	private String testName;
	private String position;
	private String description;
	private String mediaURL;
	private ResultOptions resultOptions;
	private int resultValue;

	public Test(String testId, String testName, String position, String description, String mediaURL, ResultOptions resultOptions) {
		this.testId = testId;
		this.testName = testName;
		this.position = position;
		this.description = description;
		this.mediaURL = mediaURL;
		this.resultOptions = resultOptions;
	}

	public String getTestId() {
		return testId;
	}

	public String getTestName() {
		return testName;
	}

	public String getPosition() {
		return position;
	}

	public String getDescription() {
		return description;
	}

	public String getMediaURL() {
		return mediaURL;
	}

	public ResultOptions getResultOptions() {
		return resultOptions;
	}

	public int getResultValue() {
		return resultValue;
	}

	public void setResultValue(int resultValue) {
		this.resultValue = resultValue;
	}

	@Override
	public String toString() {
		return "Test{" +
				"testId='" + testId + '\'' +
				", testName='" + testName + '\'' +
				", position='" + position + '\'' +
				", description='" + description + '\'' +
				", mediaURL='" + mediaURL + '\'' +
				", resultOptions=" + resultOptions +
				'}';
	}

	public void printTest() {
		System.out.println(" - - - " + Printer.toUpperSpaced(testName) + " - - - " );
		System.out.println("Position: " + position);
		System.out.println("Description: " + description);
		System.out.println(resultOptions.getOptionsString());
	}

	public void printTestResult() {
		System.out.println("RESULT: " +
				resultValue + " " +
				resultOptions.getResult(resultValue));
	}
}
