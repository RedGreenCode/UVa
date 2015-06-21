import java.util.*;
import java.io.*;

public class ReferenceUtil
{
	public ReferenceUtil() {
			stdin = new BufferedReader(new InputStreamReader(System.in));
			stdout = new BufferedWriter(new OutputStreamWriter(System.out));
			sb = new StringBuilder(25000);

			// Find first test case name
			String line = "#";
			while (line != null && line.length() > 0 && line.charAt(0) != '$') line = getNextInputLine();

			if (line == null || line.length() == 0) currentTestCaseName = "";
			else currentTestCaseName = line.substring(1).trim();

			// Read remaining test case input blocks
			while (true) {
				String name = currentTestCaseName;
				ArrayList<String> testInput = getNextTestInput();
				if (testInput == null) break;
				testCases.put(name, testInput);
			}
	}

	int numCases = 0;
	int caseNum = 0;
	Boolean debug = false;

	BufferedReader stdin;
	BufferedWriter stdout;
	StringBuilder sb;
	
	String currentTestCaseName;

	HashMap<String, ArrayList<String>> testCases = new HashMap<String, ArrayList<String>>();

	public ArrayList<String> getTestInput() {
		String testCaseName = getMethodName(2);
		ArrayList<String> cases = testCases.get(testCaseName);
		if (cases == null) return new ArrayList<String>();
		return cases;
	}

	// Return all of the non-comment lines between the current test case and the next
	// one, then set the next test case name.
	private ArrayList<String> getNextTestInput() {
		if (!ready()) return null;

		ArrayList<String> lines = new ArrayList<String>();

		String line = getNextInputLine();

		while (line != null && line.length() > 0 && line.charAt(0) != '$') {
			if (line.charAt(0) != '#') lines.add(line);
			line = getNextInputLine();
		}
		if (line == null || line.length() == 0)
			currentTestCaseName = "";
		else
			currentTestCaseName = line.substring(1).trim();

		return lines;
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

	/**
	 * Get the method name given a depth in the call stack.
	 * http://stackoverflow.com/a/442789/4803
	 * @param depth depth in the call stack (0 means current method, 1 means call method, ...)
	 * @return method name
	 */
	private String getMethodName(final int depth)
	{
	  final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	  return ste[ste.length - 1 - depth].getMethodName();
	}
}
