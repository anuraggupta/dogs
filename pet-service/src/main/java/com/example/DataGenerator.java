package com.example;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DataGenerator {

	static int LIMIT = 20;
	static Random rand = new Random();

	public static void main(String... a) {
		List<String> localDB = new ArrayList<>();

		List<String[]> remoteDB = new ArrayList<>();

		for (int i = 0; i < LIMIT; i++) {

			String id = UUID.randomUUID().toString();
			String hair = getRandomHair();
			String breed = getRandomBreed();

			Address address = getRandomAddress();
			String name = getRandomName();
			String phone = getRandomPhoneNumber();
			String size = getRandomSize();

			DogDetails details = new DogDetails();

			details.setAddress(address);
			details.setId(id);
			details.setBreed(breed);
			details.setHair(hair);
			details.setName(name);
			details.setPhoneNumber(phone);
			details.setSize(size);

			String[] local = { id, hair, breed, name, size + "", phone, address.toString() };
			String[] remote = { id, name, size + "", phone, address.toString() };
			localDB.add(details.toString());
			remoteDB.add(remote);

		}

		try (FileWriter writer = new FileWriter("src/main/resources/local.csv")) {

			for (String local : localDB) {
				writer.write(local);
				writer.write("\n");
			}
		} catch (Exception w) {
			w.printStackTrace();
		}

		try (FileWriter writer = new FileWriter("src/main/resources/remote.csv")) {

			for (String[] remote : remoteDB) {
				writer.write(arrayToLine(remote));
				writer.write("\n");
			}
		} catch (Exception w) {
			w.printStackTrace();
		}

	}

	private static char[] arrayToLine(String[] local) {

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < local.length; i++) {
			buffer.append(local[i]);

			if (i + 1 != local.length) {

				buffer.append(",");
			}
		}

		return buffer.toString().toCharArray();
	}

	private static String getRandomSize() {

		int randomNum = rand.nextInt((70 - 2) + 1) + 2;

		return randomNum + "";
	}

	private static String getRandomPhoneNumber() {
		int randomNum = rand.nextInt((999999999 - 99999999) + 1) + 99999999;

		return randomNum + "";
	}

	private static String getRandomName() {

		char randomNum1 = (char) (rand.nextInt(('z' - 'a') + 1) + 'a');
		char randomNum2 = (char) (rand.nextInt(('z' - 'a') + 1) + 'a');
		char randomNum3 = (char) (rand.nextInt(('z' - 'a') + 1) + 'a');
		char randomNum4 = (char) (rand.nextInt(('z' - 'a') + 1) + 'a');

		return new String(new char[] { randomNum1, randomNum2, randomNum3, randomNum4 });
	}

	private static Address getRandomAddress() {

		Address address = new Address();
		address.setCity(getRandomName());
		address.setCountry(getRandomName());
		address.setFirstLine(getRandomName());
		address.setPincode(getRandomName());
		address.setSecondLine(getRandomName());

		return address;
	}

	private static String getRandomBreed() {
		String[] breeds = { "Lab", "German Shepherd", "Bulldog", "Beagle", "Husky", "Poodle", "Retriever" };

		int randomNum = rand.nextInt((6 - 0) + 1) + 0;

		return breeds[randomNum];
	}

	private static String getRandomHair() {
		String[] hairs = { "Short Hair", "Long Hair" };

		int randomNum = rand.nextInt((1 - 0) + 1) + 0;

		return hairs[randomNum];
	}
}
