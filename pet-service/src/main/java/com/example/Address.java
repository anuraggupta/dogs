package com.example;

public class Address {

	String firstLine;
	String secondLine;
	String city;
	String country;
	String pincode;

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return firstLine + "," + secondLine + "," + city + "," + country + "," + pincode;
	}

	public static Address fromCSV(String firstLine, String secondLine, String city, String country, String pincode) {
		Address address = new Address();
		address.setFirstLine(firstLine);
		address.setSecondLine(secondLine);
		address.setCity(city);
		address.setCountry(country);
		address.setPincode(pincode);

		return address;
	}

}
