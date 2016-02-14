// Template for UVa solutions
//
// Part of Project 462, a trip through the 462 starred problems
// at http://uhunt.felix-halim.net/id/570854
//
// To use this template, just remove the indicated code from 
// parseNextTestCase(), and add yours. That function will run
// once for each test case.
//
// For more information, see http://www.redgreencode.com/about-project-462/

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Main
{
	public static void main(String args[]) {
		Main m = new Main();
		m.setupIO(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
		m.run();
	}

	public void setupIO(Reader reader, Writer writer) {
		stdin = new BufferedReader(reader);
		stdout = new BufferedWriter(writer);
		sb = new StringBuilder(25000);

		// Uncomment if first input line is number of test cases
		//String sNumCases = getNextInputLine();
		//numCases = Integer.parseInt(sNumCases);
	}

	public void run() {
		//long startTime = System.nanoTime();
		while (parseNextTestCase()) ;
		flushOutput();
		//long stopTime = System.nanoTime();
		//System.err.println((stopTime - startTime)/1000000);
	}

	// Module-level variables
	int numCases = 0;
	int caseNum = 0;
	Boolean debug = false;

	BufferedReader stdin;
	BufferedWriter stdout;
	StringBuilder sb;

	private Boolean parseNextTestCase() {
		if (!ready()) return false;
		//if (caseNum >= numCases) return false;

		// ********** Delete this code and add yours (start) **********
		Integer[] tokens = getIntegers();
		if (tokens.length == 0) return false;

		write(tokens[0]);
		write("|");
		write(tokens[1]);
		writeln();

		if (debug) writeln("Debug mode is on");
		// ********** Delete this code and add yours (end) **********

		caseNum++;
		return true;
	}

	// Helper classes
	// (add here if necessary)

	// Helper functions
	public Integer[] getIntegers() {
		String line = getNextInputLine();
		String[] tokens = line.split("\\s+");
		ArrayList<Integer> lst = new ArrayList<>();
		for (String s : tokens) if (!s.trim().equals("")) lst.add(Integer.parseInt(s));

		return lst.toArray(new Integer[lst.size()]);
	}

	public void flushOutput() {
		try {
			stdout.write(sb.toString());
			stdout.flush();
			stdout.close();
		} catch (IOException ioe) {}
	}

	private Boolean ready() {
		try {
			return (stdin.ready());
		} catch (IOException ioe) { System.out.println("I/O exception"); }

		return false;
	}

	private String getNextInputLine() {
		try {
			return stdin.readLine();
		} catch (IOException ioe) { System.out.println("I/O exception"); }

		return "";
	}

	private void checkSb() {
		try {
			if (sb.length() >= 20000) {
				stdout.write(sb.toString());
				sb = new StringBuilder(25000);
			}
		} catch (IOException ioe) { System.out.println("I/O exception"); }
	}

	public void write(Object w) {
		sb.append(w);
		checkSb();
	}

	public void writeln() {
		sb.append(System.getProperty("line.separator"));
		checkSb();
	}

	public void writeln(Object w) {
		sb.append(w);
		sb.append(System.getProperty("line.separator"));
		checkSb();
	}
}
