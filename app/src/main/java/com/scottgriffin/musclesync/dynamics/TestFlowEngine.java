package com.scottgriffin.musclesync.dynamics;

import com.scottgriffin.musclesync.model.Assessment;
import com.scottgriffin.musclesync.model.SystemTest;
import com.scottgriffin.musclesync.model.enums.ISA_RESULT;
import com.scottgriffin.musclesync.model.enums.PregnancyStatus;

import java.util.List;

public class TestFlowEngine {

	public List<String> applySystemTestFlow(Assessment assessment, ContextData contextData) {
		System.out.println(" ~~ TEST FLOW ENGINE ~~");
		System.out.println(" applySystemTestFlow() ");
		if(contextData.pregnancyStatus == PregnancyStatus.PREGNANT) {
			return List.of("HROM::ST", "ISA::ST");
		} else if (contextData.pregnancyStatus == PregnancyStatus.POSTPARTUM) {
			return List.of("ISA::ST", "HROM::ST");
		}
		return null;
	}

	public List<String> applyTestFlow(SystemTest systemTest, ContextData contextData) {
		System.out.println(" ~~ TEST FLOW ENGINE ~~");
		System.out.println(" applyTestFlow() ");
		String id = systemTest.getFunctionalSystem().getFunctionalSystemId();
		switch (id) {
			case "ISA":
				System.out.println("SET TEST FLOW -- CASE: ISA");
				return applyFlowToISA(contextData);
			case "HROM":
				System.out.println("SET TEST FLOW -- CASE: HROM");
				return applyFlowToHROM(contextData);
			case "SROM":
				break;
			case "PO":
				break;
			case "STK":
				break;
			default:
				System.out.println("DEFAULT");
		}
		return null;
	}

	private List<String> applyFlowToISA(ContextData contextData) {
		if (contextData.getPregnancyStatus() == PregnancyStatus.PREGNANT) {
			return List.of("ISA02");
		} else {
			return List.of("ISA01", "ISA02");
		}
	}

	private static List<String> applyFlowToHROM(ContextData contextData) {
		if(contextData.isaResult == ISA_RESULT.WIDE) {
			return List.of("HROM01", "HROM02");
		} else if (contextData.isaResult== ISA_RESULT.NARROW) {
			return List.of("HROM02", "HROM01");
		}
		return null;
	}
}
