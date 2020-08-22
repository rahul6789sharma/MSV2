package org.stocksrin.common.model;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String args[]) {
		System.out.println("start");
		String[] dish = { "FATOil", "FATCheese", "FATEgg", "FIBERSpinach", "CARBRice", "FIBERBeans" };

		List<String> d1 = new ArrayList<>(); // FAT
		List<String> d2 = new ArrayList<>(); // FIBER
		List<String> d3 = new ArrayList<>(); // CARB

		for (int i = 0; i < 5; i++) {

			if (dish[i].startsWith("FAT")) {
				d1.add(dish[i]);
			} else if (dish[i].startsWith("FIBER")) {
				d2.add(dish[i]);
			} else if (dish[i].startsWith("CARB")) {
				d3.add(dish[i]);
			}

			if (d1.size() >= 2) {

				System.out.println(i + "-> " + "1");
				d1.remove(dish[i]);
				System.out.println(dish[i]);
			} else {
				System.out.println(i + "-> " + "0");
			}

			if (d2.size() >= 2) {

				System.out.println(i + "-> " + "1");
				d2.remove(dish[i]);
				System.out.println(dish[i]);
			} else {
				System.out.println(i + "-> " + "0");
			}
			if (d3.size() >= 2) {

				System.out.println(i + "-> " + "1");
				d3.remove(dish[i]);
				System.out.println(dish[i]);
			} else {
				System.out.println(i + "-> " + "0");
			}

		}
		for (String string : dish) {

			// System.out.println(string);

			/*
			 * if (d1.size() >= 2) { System.out.println("two dish" + d1 + "1"); } if
			 * (d2.size() >= 2) { System.out.println("two dish" + d2 + "1"); } if (d3.size()
			 * >= 2) { System.out.println("two dish" + d3 + "1"); }
			 */
		}
		// System.out.println(d1);
		// System.out.println(d2);
		// System.out.println(d3);

	}
}
