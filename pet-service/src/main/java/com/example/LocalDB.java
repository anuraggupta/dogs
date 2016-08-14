package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LocalDB {

	private static Map<String, DogDetails> local = new TreeMap<String, DogDetails>(new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {

			return o1.compareToIgnoreCase(o2);
		}
	});

	static {

		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/local.csv"))) {

			String line;

			while ((line = br.readLine()) != null) {

				DogDetails details = DogDetails.fromCSV(line);

				local.put(details.getId(), details);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DogDetails getById(String id) {

		return local.get(id);

	}

	public List<DogDetails> searchBy(int sizeMin, int sizeMax, String hair, String breed) {

		List<DogDetails> result = new ArrayList();

		if (sizeMin == 0 && sizeMax == 0 && hair == null && breed == null) {

			result.addAll(local.values());
		} else {

			local.values().stream().filter(details -> checkSize(sizeMin, sizeMax, details))
					.filter(details -> checkHair(hair, details)).filter(details -> checkBreed(breed, details))
					.forEach(details -> result.add(details));

		}
		return result;

	}

	private boolean checkBreed(String breed, DogDetails details) {

		if (null == breed) {
			return true;
		}
		return details.getBreed().equals(breed);

	}

	private boolean checkHair(String hair, DogDetails details) {

		if (null == hair) {
			return true;
		}
		return details.getHair().equals(hair);
	}

	private boolean checkSize(int sizeMin, int sizeMax, DogDetails details) {

		if (sizeMin == 0 && sizeMax != 0) {
			return Integer.parseInt(details.getSize()) <= sizeMax;
		} else if (sizeMin != 0 && sizeMax == 0) {

			return Integer.parseInt(details.getSize()) >= sizeMin;
		} else if (sizeMin == 0 && sizeMax == 0) {
			return true;
		} else {
			return Integer.parseInt(details.getSize()) >= sizeMin && Integer.parseInt(details.getSize()) <= sizeMax;

		}
	}

}
