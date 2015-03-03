// Template for UVa solutions
//
// Part of Project 462, a trip through the 462 starred problems
// at http://uhunt.felix-halim.net/
//
// For more information, see http://www.redgreencode.com/about-project-462/

import java.util.*;

public class Main
{
	public static void main(String args[]) {
			Main m = new Main();
			m.ReadInput();

			// Uncomment if first input line is number of test cases
			//String sNumCases = m.GetNextInputLine();
			//m.numCases = Integer.parseInt(sNumCases);

			while (m.ParseNextTestCase()) ;
	}

	List<String> inputList = new ArrayList<String>();
	int inputPos = 0;
	int numCases = 0;
	int caseNum = 0;
	Boolean debug = false;

	private Boolean ParseNextTestCase() {
		if (inputPos >= inputList.size()) return false;
		//if (caseNum >= numCases) return false;
		String line = GetNextInputLine();
		// parse input line
		
		// Process input line
		System.out.println(line);

		caseNum++;
		return true;
	}

	private String GetNextInputLine() {
		return inputList.get(inputPos++);
	}

	private void ReadInput() {
		Scanner stdin = new Scanner(System.in);
		while (stdin.hasNextLine())
			inputList.add(stdin.nextLine());
	}
}
