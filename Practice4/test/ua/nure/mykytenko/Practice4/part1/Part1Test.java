package ua.nure.mykytenko.Practice4.part1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import ua.nure.mykytenko.Practice4.Part1;

public class Part1Test {

	@Test
	public void testConvert1() {
		String testString = "When I was younger, so much younger than today.";
		String expected = "WHEN I was YOUNGER, so MUCH YOUNGER THAN TODAY.";
		String actually = Part1.convert(testString);
		assertEquals(expected, actually);
	}

	@Test
	public void testConvert2() {
		String testString = "Ïóñòü áåãóò íåóêëşæå\nÏåøåõîäû ïî ëóæàì";
		String expected = "ÏÓÑÒÜ ÁÅÃÓÒ ÍÅÓÊËŞÆÅ\nÏÅØÅÕÎÄÛ ïî ËÓÆÀÌ";
		String actually = Part1.convert(testString);
		assertEquals(expected, actually);
	}

	@Test(expected = IOException.class)
	public void testMain() throws IOException {
		Part1 p = new Part1();
		Part1.main(new String[] {});
		throw new IOException("Test IOException");
	}

	
	}

