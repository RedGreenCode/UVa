// Template for UVa solutions
//
// Part of Project 462, a trip through the 462 starred problems
// at http://uhunt.felix-halim.net/
//
// For more information, see http://www.redgreencode.com/about-project-462/

import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String args[]) {
			Main m = new Main();
			m.stdin = new BufferedReader(new InputStreamReader(System.in));
			m.stdout = new BufferedWriter(new OutputStreamWriter(System.out));
			m.sb = new StringBuilder(25000);

			// Uncomment if first input line is number of test cases
			//String sNumCases = m.getNextInputLine();
			//m.numCases = Integer.parseInt(sNumCases);

			while (m.parseNextTestCase()) ;
			m.flushOutput();
}

	int numCases = 0;
	int caseNum = 0;
	Boolean debug = false;

	BufferedReader stdin;
	BufferedWriter stdout;
	StringBuilder sb;

	private Boolean parseNextTestCase() {
		if (!ready()) return false;
		//if (caseNum >= numCases) return false;
		String line = getNextInputLine();
		String[] tokens = line.split("\\s+");

		write(tokens[0]);
		write("|");
		write(tokens[1]);
		writeln();

		caseNum++;
		return true;
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
