package com.scottgriffin.musclesync.model.resulttypes;

import com.scottgriffin.musclesync.model.enums.RESULT_ENUM;

public interface ResultOptions {
	public String getTypeName();
	public String getOptionsString();
	public RESULT_ENUM getResult(int value);
}
