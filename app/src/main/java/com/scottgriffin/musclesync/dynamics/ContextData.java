package com.scottgriffin.musclesync.dynamics;

import com.scottgriffin.musclesync.model.SystemTest;
import com.scottgriffin.musclesync.model.Test;
import com.scottgriffin.musclesync.model.enums.PregnancyStatus;
import com.scottgriffin.musclesync.model.enums.RESULT_ENUM;
import com.scottgriffin.musclesync.model.enums.ROTATION_RESULT;

import java.util.Map;

public class ContextData {
	PregnancyStatus pregnancyStatus;
	RESULT_ENUM isaResult;
	int excessiveFlags = 0;

	public ContextData updateContextData(SystemTest systemTestResult) {
		System.out.println("UPDATE CONTEXT DATA () ");
		String systemTestId = systemTestResult.getSystemTestId();
		if (systemTestId.equals("ISA::ST")) {
			Test isaTest = systemTestResult.getTestMap().get("ISA01");
			isaResult = isaTest.getResultOptions().getResult(isaTest.getResultValue());
			System.out.println("UPDATE ISA RESULT TO " + isaResult);
		} else if (systemTestId.equals("HROM::ST")){
			// check excessive
			for(Map.Entry<String, Test> entry : systemTestResult.getTestMap().entrySet()) {
				Test test = entry.getValue();
				RESULT_ENUM result = test.getResultOptions().getResult(test.getResultValue());
				if (result == ROTATION_RESULT.EXCESSIVE) {
					excessiveFlags++;
				}
			}
		}
		return this;
	}

	public void checkFlags() {
		if (excessiveFlags > 1) {
			System.out.println("!! ALERT !! - Check HyperMobility");
		}
	}

	public ContextData updateContextData(PregnancyStatus pregnancyStatus) {
		return this;
	}



	public PregnancyStatus getPregnancyStatus() {
		return pregnancyStatus;
	}

	public void setPregnancyStatus(PregnancyStatus pregnancyStatus) {
		this.pregnancyStatus = pregnancyStatus;
	}

	public RESULT_ENUM getIsaResult() {
		return isaResult;
	}

	public void setIsaResult(RESULT_ENUM isaResult) {
		this.isaResult = isaResult;
	}

	@Override
	public String toString() {
		return "ContextData{" +
				"pregnancyStatus=" + pregnancyStatus +
				", isaResult=" + isaResult +
				", excessiveFlags=" + excessiveFlags +
				'}';
	}
}
