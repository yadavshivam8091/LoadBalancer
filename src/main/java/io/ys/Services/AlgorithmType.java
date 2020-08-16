package io.ys.Services;

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
