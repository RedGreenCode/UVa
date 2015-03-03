// UVa 454: Anagrams (http://uva.onlinejudge.org/external/4/454.html)
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

			String sNumCases = m.GetNextInputLine();
			m.numCases = Integer.parseInt(sNumCases);
			m.GetNextInputLine();	// blank line

			while (m.ParseNextTestCase()) ;
	}

	List<String> inputList = new ArrayList<String>();
	int inputPos = 0;
	int numCases = 0;
	int caseNum = 0;
	Boolean foundMatches = false;

	private Boolean ParseNextTestCase() {
		if (inputPos >= inputList.size()) return false;
		if (caseNum > numCases) return false;
		
		List<String> phrases = new ArrayList<String>();
		List<String> output = new ArrayList<String>();

		String line = GetNextInputLine();
		while (!line.equals("")) {
			phrases.add(line);
			Collections.sort(phrases);
			List<int[]> counts = new ArrayList<int[]>();
			for (String p : phrases) {
				int[] count = new int[256];
				for (int i=0; i<p.length(); i++) {
					char c = p.charAt(i);
					if (c != ' ') count[c]++;
				}
				counts.add(count);
			}

			for (int i=0; i<counts.size(); i++) {
				int[] counti = counts.get(i);
				Boolean foundMatch;
				for (int j=0; j<counts.size(); j++) {
					if (i == j) continue;
					foundMatch = true;
					int[] countj = counts.get(j);
					for (int k=0; k<counti.length; k++) {
						if (counti[k] != countj[k]) {
							foundMatch = false;
							break;
						}
					}
					if (foundMatch) {
						List<String> pair = new ArrayList<String>();
						pair.add(phrases.get(i));
						pair.add(phrases.get(j));
						Collections.sort(pair);
						output.add(pair.get(0) + " = " + pair.get(1));
					}
				}
			}
			if (inputPos >= inputList.size()) break;
			line = GetNextInputLine();
		}

		String prev = "";
		Collections.sort(output);
		foundMatches = false;
		for (String o : output) {
			if (!o.equals(prev)) {
				System.out.println(o);
				foundMatches = true;
			}
			prev = o;
		}

		caseNum++;
		if (caseNum < numCases) System.out.println();
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
