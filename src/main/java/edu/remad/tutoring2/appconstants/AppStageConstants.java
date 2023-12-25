package edu.remad.tutoring2.appconstants;

public enum AppStageConstants {

	DEVELOPMENT(0, "dev"), TEST(1, "test"), FEATURE(2, "feature"), PROD(3, "prod");

	public final int ordinalIndex;
	
	public final String statusName;

	AppStageConstants(int ordinalIndex, String statusName) {
		this.ordinalIndex = ordinalIndex;
		this.statusName = statusName;
	}

	public int getOrdinalIndex() {
		return ordinalIndex;
	}

	public String getStatusName() {
		return statusName;
	}
}
