package edu.remad.tutoring2.models;

public enum TokenType {
	BEARER(0);
	
	public final int ordinal;
	
	TokenType(final int ordinal) {
		this.ordinal = ordinal;
	}
}
