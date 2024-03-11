package com.scottgriffin.musclesync.dynamics;

import com.scottgriffin.musclesync.model.SystemTest;
import com.scottgriffin.musclesync.model.Test;
import com.scottgriffin.musclesync.model.enums.ISA_RESULT;
import com.scottgriffin.musclesync.model.enums.PregnancyStatus;
import com.scottgriffin.musclesync.model.enums.RESULT_ENUM;
import com.scottgriffin.musclesync.model.enums.ROTATION_RESULT;

public class ContextData {
	PregnancyStatus pregnancyStatus;
	RESULT_ENUM isaResult;
	int excessiveFlags = 0;
	RESULT_ENUM hromInferedISAResult;

	// FLAG CLASS
	// TRACKS IF IT HAS BEEN ALERTED
	// HOLDS A MESSAGE
	// CAN TAKE IN A RESULT.. LIKE check hypermobility? confirm, deny, ask again later, ignore
	// sub types - flag counter (as in hyper mobility)
	// inferred type difference... ISA

	public ContextData updateContextData(SystemTest systemTestResult) {
		System.out.println("UPDATE CONTEXT DATA () ");
		String systemTestId = systemTestResult.getSystemTestId();

		switch (systemTestId) {
			case "ISA::ST":
				isaHandler(systemTestResult);
				break;
			case "HROM::ST":
				hromHandler(systemTestResult);
				break;
			case "SROM::ST":
				break;
			case "PO::ST":
				break;
			case "STK::ST":
				break;
			default:
				System.out.println("DEFAULT");
		}
		return this;
	}

	private void isaHandler(SystemTest systemTestResult) {
		Test isaTest = systemTestResult.getTestMap().get("ISA01");
		isaResult = isaTest.getResultOptions().getResult(isaTest.getResultValue());
		System.out.println("UPDATE ISA RESULT TO " + isaResult);
	}

	private void hromHandler(SystemTest systemTestResult) {
		checkExcessive(systemTestResult);
		inferISA(systemTestResult);
	}

	private void inferISA(SystemTest systemTest) {
		Test externalTest = systemTest.getTestMap().get("HROM01");
		Test internalTest = systemTest.getTestMap().get("HROM02");
		RESULT_ENUM externalResult = externalTest.getResultOptions().getResult(externalTest.getResultValue());
		RESULT_ENUM internalResult = internalTest.getResultOptions().getResult(internalTest.getResultValue());
		if (externalResult == ROTATION_RESULT.LIMITED && internalResult != ROTATION_RESULT.LIMITED) {
			hromInferedISAResult = ISA_RESULT.WIDE;
		} else if (internalResult == ROTATION_RESULT.LIMITED && (externalResult != ROTATION_RESULT.LIMITED)) {
			hromInferedISAResult = ISA_RESULT.NARROW;
		} else {
			hromInferedISAResult = ISA_RESULT.NEUTRAL;
		}
	}

	private void checkExcessive(SystemTest systemTest) {
		for(Test test : systemTest.getTestMap().values()) {
			RESULT_ENUM result = test.getResultOptions().getResult(test.getResultValue());
			if (result == ROTATION_RESULT.EXCESSIVE) {
				excessiveFlags++;
			}
		}
	}

	public void checkFlags() {
		if (excessiveFlags > 1) {
			System.out.println("!! FLAG !! - Check HyperMobility");
		}

		if (hromInferedISAResult != null && isaResult != null && hromInferedISAResult != isaResult) {
			System.out.println("!! FLAG !! - Check RibFlair: Isa Result: " + isaResult + " != HROM inferred result: " + hromInferedISAResult);
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
