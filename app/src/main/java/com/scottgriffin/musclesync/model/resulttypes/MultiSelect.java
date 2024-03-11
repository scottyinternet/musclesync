package com.scottgriffin.musclesync.model.resulttypes;

import com.scottgriffin.musclesync.model.enums.RESULT_ENUM;

import java.util.List;

public class MultiSelect implements ResultOptions {
	public static final String RESULT_TYPE_NAME = "MultiSelect";
	List<RESULT_ENUM> options;

	public MultiSelect(List<RESULT_ENUM> options) {
		this.options = options;
	}

	@Override
	public String getTypeName() {
		return RESULT_TYPE_NAME;
	}

	@Override
	public String getOptionsString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < options.size(); i++) {
			sb.append(i+1 + " - " + options.get(i) + "\n");
		}
		return sb.toString();
	}

	@Override
	public RESULT_ENUM getResult(int value) {
		return options.get(value);
	}
}
