package com.scottgriffin.musclesync.model;

public class FunctionalSystem {
	private final String name;
	private final String functionalSystemId;
	private final boolean isBilateral;

	public FunctionalSystem(String name, String functionalSystemId, boolean isBilateral) {
		this.name = name;
		this.functionalSystemId = functionalSystemId;
		this.isBilateral = isBilateral;
	}

	public String getName() {
		return name;
	}

	public String getFunctionalSystemId() {
		return functionalSystemId;
	}

	public boolean isBilateral() {
		return isBilateral;
	}
}
