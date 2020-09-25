package io.ys.services;

public enum AlgorithmType {
	RoundRobin("round-robin"),
	Random("random");
	
	private String type;
	
	AlgorithmType(String type) {
		this.type = type;
	}
	
	public String val() {
		return this.type;
	}
}
