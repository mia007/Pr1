package ua.nure.mykytenko.Practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

	private static final String BASE_NAME = "resources";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in, "cp1251");
		while (sc.hasNext()) {
			try {
				String[] arr = sc.nextLine().split(" ");
				if (arr[0].equals("stop")) {
					break;
				}
				ResourceBundle rb = ResourceBundle.getBundle(BASE_NAME, new Locale(arr[1]));
				System.out.println(rb.getString(arr[0]));
			} catch (MissingResourceException e) {
				System.out.println(e.toString());
				continue;
			}
		}
		sc.close();
	}

}
