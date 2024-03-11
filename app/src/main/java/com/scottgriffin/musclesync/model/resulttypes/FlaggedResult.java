package com.scottgriffin.musclesync.model.resulttypes;

public class FlaggedResult {
	ResultOptions resultOptions;
	String flaggedMessage;

	public FlaggedResult(ResultOptions resultOptions) {
		this.resultOptions = resultOptions;
	}
}
