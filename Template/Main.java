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
			//String sNumCases = m.GetNextInputLine();
			//m.numCases = Integer.parseInt(sNumCases);

			while (m.ParseNextTestCase()) ;
			try {
				m.stdout.write(m.sb.toString());
				m.stdout.flush();
				m.stdout.close();
			} catch (IOException ioe) {}
	}

	int numCases = 0;
	int caseNum = 0;
	Boolean debug = false;

	BufferedReader stdin;
	BufferedWriter stdout;
	StringBuilder sb;

	private Boolean ParseNextTestCase() {
		if (!Ready()) return false;
		//if (caseNum >= numCases) return false;
		String line = GetNextInputLine();
		String[] tokens = line.split("\\s+");

		Write(tokens[0]);
		Write("|");
		Write(tokens[1]);
		Write("\n");

		caseNum++;
		return true;
	}

	private Boolean Ready() {
		try {
			return (stdin.ready());
		} catch (IOException ioe) { System.out.println("I/O exception"); }

		return false;
	}

	private String GetNextInputLine() {
		try {
			return stdin.readLine();
		} catch (IOException ioe) { System.out.println("I/O exception"); }

		return "";
	}

	private void Write(String s) {
		try {
			sb.append(s);
			if (sb.length() >= 20000) {
				stdout.write(sb.toString());
				sb = new StringBuilder(25000);
			}
		} catch (IOException ioe) { System.out.println("I/O exception"); }
	}
}
