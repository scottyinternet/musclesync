package com.scottgriffin.musclesync.utility;

import com.scottgriffin.musclesync.model.Assessment;
import com.scottgriffin.musclesync.model.FunctionalSystem;
import com.scottgriffin.musclesync.model.SystemTest;
import com.scottgriffin.musclesync.model.Test;
import com.scottgriffin.musclesync.model.enums.ISA_RESULT;
import com.scottgriffin.musclesync.model.enums.PO_RESULT;
import com.scottgriffin.musclesync.model.enums.RESULT_ENUM;
import com.scottgriffin.musclesync.model.enums.ROTATION_RESULT;
import com.scottgriffin.musclesync.model.resulttypes.RangeSelect;
import com.scottgriffin.musclesync.model.resulttypes.ResultOptions;
import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DemoDataProvider {
	Map<String, FunctionalSystem> functionalSystemMap = new HashMap<>();
	Map<String, ResultOptions> resultOptionsMap = new HashMap<>();
	Map<String, Test> testMap = new HashMap<>();
	Map<String, SystemTest> systemTestMap = new HashMap<>();
	Assessment assessment;

	public DemoDataProvider() {
		createFunctionalSystemsTable();
		createResultOptionsTable();
		createFunctionalTestsTable();
		createSystemTestTable();
		createAssessment();
	}

	private void createFunctionalSystemsTable() {
		functionalSystemMap = new HashMap<>();
		functionalSystemMap.put("ISA", new FunctionalSystem("Infrasternal Angle", "ISA" , false));
		functionalSystemMap.put("SROM", new FunctionalSystem("Shoulder Range of Motion", "SROM", true));
		functionalSystemMap.put("HROM", new FunctionalSystem("Hip Range of Motion", "HROM" , true));
		functionalSystemMap.put("PO", new FunctionalSystem("Pelvic Orientation", "PO", false));
		functionalSystemMap.put("STK", new FunctionalSystem("Stack", "STK" , false));
	}

	private void createResultOptionsTable() {
		TreeMap<Integer, RESULT_ENUM> wideNarrow = new TreeMap<>();
		wideNarrow.put(-100, ISA_RESULT.WIDE);
		wideNarrow.put(-25, ISA_RESULT.NEUTRAL);
		wideNarrow.put(26, ISA_RESULT.NARROW);

		TreeMap<Integer, RESULT_ENUM> limited = new TreeMap<>();
		limited.put(-100, ROTATION_RESULT.LIMITED);
		limited.put(-25, ROTATION_RESULT.SUFFICIENT);
		limited.put(26, ROTATION_RESULT.EXCESSIVE);

		TreeMap<Integer, RESULT_ENUM> aptPpt = new TreeMap<>();
		aptPpt.put(-100, PO_RESULT.APT);
		aptPpt.put(-25, PO_RESULT.NEUTRAL);
		aptPpt.put(26, PO_RESULT.PPT);

		resultOptionsMap.put("angle" , new RangeSelect(wideNarrow));
		resultOptionsMap.put("rotation" , new RangeSelect(limited));
		resultOptionsMap.put("tilt" , new RangeSelect(aptPpt));

	}

	private void createFunctionalTestsTable() {
		testMap.put("ISA01" , new Test(
				"ISA01",
				"ISA Check",
				"Laying",
				"Laying, observe posture from various angles",
				"https://youtube.com/isa01posturalassessment",
				resultOptionsMap.get("angle")
		));
		testMap.put("ISA02" , new Test(
				"ISA02",
				"ISA Check",
				"Standing",
				"Standing upright, observe posture from various angles",
				"https://youtube.com/isa02posturalassessment",
				resultOptionsMap.get("angle")
		));
		testMap.put("HROM01" , new Test(
				"HROM01",
				"External Hip Rotation",
				"Back",
				"Passively check external rotation (primary for wide, likely limited)",
				"https://youtube.com/externalrotation",
				resultOptionsMap.get("rotation")
		));
		testMap.put("HROM02" , new Test(
				"HROM02",
				"Internal Hip Rotation",
				"Back",
				"Passively check internal hip rotation (primary for narrow, likely limited",
				"https://youtube.com/internalrotation",
				resultOptionsMap.get("rotation")
		));
	}

	private void createSystemTestTable() {
		systemTestMap.put("ISA::ST", new SystemTest(functionalSystemMap.get("ISA")));
		systemTestMap.put("HROM::ST" , new SystemTest(functionalSystemMap.get("HROM")));
		systemTestMap.put("SROM::ST" , new SystemTest(functionalSystemMap.get("SROM")));
		systemTestMap.put("PO::ST" , new SystemTest(functionalSystemMap.get("PO")));
		systemTestMap.put("STK::ST" , new SystemTest(functionalSystemMap.get("STK")));

		systemTestMap.get("ISA::ST").addTestToMap(testMap.get("ISA01"));
		systemTestMap.get("ISA::ST").addTestToMap(testMap.get("ISA02"));
		systemTestMap.get("HROM::ST").addTestToMap(testMap.get("HROM01"));
		systemTestMap.get("HROM::ST").addTestToMap(testMap.get("HROM02"));
	}

	private void createAssessment() {
		assessment = new Assessment("TestAssessment");
		assessment.addSystemTestToMap(systemTestMap.get("ISA::ST"));
		assessment.addSystemTestToMap(systemTestMap.get("HROM::ST"));
	}

	public Map<String, FunctionalSystem> getFunctionalSystemMap() {
		return functionalSystemMap;
	}

	public Map<String, ResultOptions> getResultOptionsMap() {
		return resultOptionsMap;
	}

	public Map<String, Test> getTestMap() {
		return testMap;
	}

	public Map<String, SystemTest> getSystemTestMap() {
		return systemTestMap;
	}

	public Assessment getAssessment() {
		return assessment;
	}
}
