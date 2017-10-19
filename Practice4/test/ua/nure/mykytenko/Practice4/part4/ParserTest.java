package ua.nure.mykytenko.Practice4.part4;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import ua.nure.mykytenko.Practice4.Parser;;

public class ParserTest {
	Parser parser;
	private Matcher match;

	@Before
	public void testConstructor() throws IOException {
		parser = new Parser("part4.txt", "Cp1251");
	}

	@Test
	public void iteratorTest() {
		Iterator<String> it = parser.iterator();
		Matcher m = match;
		while (it.hasNext()) {
			m.find();
			assertEquals(match.group(), it.next());
		}
	}

	@Test(expected = UnsupportedOperationException.class)
	public void iteratorExceptionTest() {
		Iterator<String> it = parser.iterator();
		it.remove();
	}

}
