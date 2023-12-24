package edu.remad.tutoring2.appconstants;

public final class RegexAppConstants {

	public final static String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public final static String PASSWORD_REGEX = "[0-9a-zA-Z]*";
	public final static String USERNAME_REGEX = "[0-9_a-zA-Z]*";
	public final static String HOUSE_NUMBER_REGEX = "[0-9A-Z]*";
	public final static String STREET_REGEX = "^[a-zA-Z ]*$";
	public final static String ZIP_REGEX = "^[0-9]*$";
	public final static String ZIP_LOCATION_REGEX = "^[a-zA-Z -.]*$";

	private RegexAppConstants() {
		throw new UnsupportedOperationException(this.getClass().getName() + " shall not be supported.");
	}
}
