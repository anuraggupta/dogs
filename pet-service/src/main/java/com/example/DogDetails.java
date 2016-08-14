package com.example;

public class DogDetails {

	String id;
	String name;
	Address address;
	String phoneNumber;
	String hair;
	String breed;
	String size;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHair() {
		return hair;
	}

	public void setHair(String hair) {
		this.hair = hair;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	@Override
	public String toString() {
		return id + "," + name + "," + phoneNumber + "," + hair + "," + breed + "," + size + "," + address;
	}

	public static DogDetails fromCSV(String line) {

		String[] fields = line.split(",");
		DogDetails details = new DogDetails();

		details.setId(fields[0]);
		details.setName(fields[1]);
		details.setPhoneNumber(fields[2]);
		details.setHair(fields[3]);
		details.setBreed(fields[4]);
		details.setSize(fields[5]);
		details.setAddress(Address.fromCSV(fields[6], fields[7], fields[8], fields[9], fields[10]));

		return details;
	}

}
