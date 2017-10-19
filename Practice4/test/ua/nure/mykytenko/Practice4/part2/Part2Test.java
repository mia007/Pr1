package ua.nure.mykytenko.Practice4.part2;

import java.io.File;

import static org.junit.Assert.*;
import org.junit.Test;

import ua.nure.mykytenko.Practice4.Part2;

public class Part2Test {

	@Test
	public void testGenerateRandom(){
		assertNotEquals(Part2.generateRandom(), Part2.generateRandom());
	}
	
	@Test
	public void testSortNumbers() {
		assertEquals("2 7 11 16 16 26 28 46 48 49", Part2.sortNumbers("7 26 49 16 2 28 48 46 16 11"));
	}
	
	@Test
	public void testWriteData() {
		Part2.writeData("part2.txt", "37 31 3 12 37 12 24 36 36 37");
		    assertEquals(Part2.readData(), "37 31 3 12 37 12 24 36 36 37");
	}
	
	@Test
	public void testMain() {
		Part2 p = new Part2();
		Part2.main(new String[] {});
	}
}