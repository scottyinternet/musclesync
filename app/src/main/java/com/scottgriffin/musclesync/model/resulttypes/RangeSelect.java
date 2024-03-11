package com.scottgriffin.musclesync.model.resulttypes;

import com.scottgriffin.musclesync.model.enums.RESULT_ENUM;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class RangeSelect implements ResultOptions {
	public static final String RESULT_TYPE_NAME = "RangeSelect";

	/**
	 * bottum up approach. Key is the low value. that option takes
	 */
	int lowerLimit;
	int upperLimit;
	int defaultValue;
	TreeMap<Integer, RESULT_ENUM> optionMap;

	public RangeSelect(int lowerLimit, int upperLimit, int defaultValue, TreeMap<Integer, RESULT_ENUM> optionMap) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.defaultValue = defaultValue;
		this.optionMap = optionMap;
	}

	public RangeSelect(TreeMap<Integer, RESULT_ENUM> optionMap) {
		this(-100, 100, 0, optionMap);
	}

	@Override
	public String getTypeName() {
		return RESULT_TYPE_NAME;
	}

	@Override
	public RESULT_ENUM getResult(int value) {
		Integer key = optionMap.floorKey(value);
		return optionMap.get(key);

	}

	@Override
	public String getOptionsString() {
		StringBuilder sb = new StringBuilder();
		sb.append("|  ");
		// Get an iterator over the entries in the TreeMap
		Iterator<Map.Entry<Integer, RESULT_ENUM>> iterator = optionMap.entrySet().iterator();

		// Iterate through the entries using the iterator
		if(iterator.hasNext()) {
			Map.Entry<Integer, RESULT_ENUM> entry = iterator.next();

			while (iterator.hasNext()) {
				String value = entry.getValue().toString();
				int lowRange = entry.getKey();
				entry = iterator.next();
				int highRange = entry.getKey()-1;
				sb.append(lowRange + " " + value + " " + highRange + "  |  ");
			}

			sb.append(entry.getKey() + " " + entry.getValue() + " " + upperLimit);
			sb.append("  |");

		}

		return sb.toString();
	}

}
