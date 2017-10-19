package ua.nure.mykytenko.Practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements Iterable<String> {
	private Matcher match;

	public Parser(String fileName, String encoding) throws IOException {
		File file = new File(fileName);
		StringBuilder sb = new StringBuilder();
		Scanner s;
		try {
			s = new Scanner(file, encoding);
			while (s.hasNextLine()) {
				sb.append(s.nextLine()).append(" ");
				sb.delete(sb.length() - 1, sb.length());
				Pattern p = Pattern.compile("\\p{javaUpperCase}.*?\\.");
				match = p.matcher(sb);
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.toString());
		}
	}

	public Iterator<String> iterator() {
		return new Iterator<String>() {

			public boolean hasNext() {
				return match.find();
			}

			public String next() {
				return match.group();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};

	}
}