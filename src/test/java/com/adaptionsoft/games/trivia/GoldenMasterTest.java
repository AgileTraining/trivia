package com.adaptionsoft.games.trivia;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.adaptionsoft.games.trivia.runner.GameRunner;

public class GoldenMasterTest {

	private PrintStream old_sysout;

	@Test
	public void overallBehaviourHasntChanged() throws Exception {
		ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
		System.setOut(new PrintStream(actualOutput));
//		System.setOut(new PrintStream(new File("real-output.txt"))); // Use this line to write the output to a file

		for (int seed = 0; seed < 500; seed++) {
			GameRunner.run(seed);
		}

		assertEquals(contentOf("golden-master.txt"), actualOutput.toString());
	}

	@SuppressWarnings("resource")
	private String contentOf(String filename) throws Exception {
		return new Scanner(new File(filename)).useDelimiter("\\Z").next();
	}

	@Before
	public void rememberSysout() {
		old_sysout = System.out;
	}

	@After
	public void resetSysout() {
		System.setOut(old_sysout);
	}
}
