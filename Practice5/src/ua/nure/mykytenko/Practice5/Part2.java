package ua.nure.mykytenko.Practice5;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Part2 {

	// terminates an execution per one second
	public static void main(String[] args) {
		// save standard input
		InputStream stdIn = System.in;

		// create input stream with line terminator (=ENTER)
		ByteArrayInputStream bais;
		try {
			bais = new ByteArrayInputStream(System.lineSeparator().getBytes("cp1251"));
		} catch (UnsupportedEncodingException e1) {
			System.out.println(e1.toString());
			return;
		}

		// move internal pointer of input stream to the end of input
		bais.skip(System.lineSeparator().length());

		// assign new value of standard input
		System.setIn(bais);

		// main functionality
		Thread thread = Spam.main2();
		// wait for 1 sec
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			System.out.println(exp.toString());
		}
		System.out.println("Try to send enter to standard input");

		// move internal pointer to begin of input
		bais.reset();
		// wait for a child termination

		try {
			thread.join();
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}

		// restore standard input
		System.setIn(stdIn);
	}

}
