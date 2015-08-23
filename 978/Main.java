import java.util.*;
import java.io.*;

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

		String sNumCases = getNextInputLine();
		numCases = Integer.parseInt(sNumCases);
	}

	public void run() {
		while (parseNextTestCase()) ;
		flushOutput();
	}

	int numCases = 0;
	int caseNum = 0;

	BufferedReader stdin;
	BufferedWriter stdout;
	StringBuilder sb;

	public int b;
	public int sgreen;
	public int sblue;

	TreeMap<Integer, Integer> gArmy;
	TreeMap<Integer, Integer> bArmy;
	
	Integer[] bfg;
	Integer[] bfb;

	public void sendBackToArmies() {
		for (int i=0; i<b; i++) {
			if (bfg[i] != null && bfg[i] > 0) putPower(gArmy, bfg[i].toString());
			if (bfb[i] != null && bfb[i] > 0) putPower(bArmy, bfb[i].toString());
		}
	}

	public void fight() {
		for (int i=0; i<b; i++) {
			if (bfg[i] == null || bfb[i] == null) break;
			if (bfg[i] > bfb[i]) {
				bfg[i] -= bfb[i];
				bfb[i] = 0;
			} else {
				bfb[i] -= bfg[i];
				bfg[i] = 0;
			}
		}
	}

	public void assignBattlefields() {
		bfg = new Integer[b];
		bfb = new Integer[b];

		for (int i=0; i<b; i++) {
			bfg[i] = getNextLemming(gArmy);
			bfb[i] = getNextLemming(bArmy);
		}
	}

	public Integer getNextLemming(TreeMap<Integer, Integer> army) {
		Map.Entry<Integer,Integer> entry = army.pollLastEntry();
		if (entry == null) return null;
		int power = entry.getKey();
		int num = entry.getValue();
		if (num > 1) army.put(power, num-1);
		return power;
	}

	public void buildArmies() {
		gArmy = new TreeMap<>();
		bArmy = new TreeMap<>();
		for (int i=0; i<sgreen; i++) putPower(gArmy, getNextInputLine());
		for (int i=0; i<sblue; i++)  putPower(bArmy, getNextInputLine());
	}

	public void putPower(TreeMap<Integer, Integer> army, String power) {
		Integer p = Integer.parseInt(power);
		Integer count = army.get(p);
		if (count == null) army.put(p, 1); else army.put(p, count+1);
	}

	public void processParams() {
		Integer[] params = getInts();
		b = params[0];
		sgreen = params[1];
		sblue = params[2];
	}

	public void printArmy(TreeMap<Integer,Integer> army) {
		while (true) {
			Integer power = getNextLemming(army);
			if (power == null) break;
			writeln(power);
		}
	}

	private void printResults() {
		if (gArmy.size() == 0 && bArmy.size() == 0) {
			writeln("green and blue died");
			return;
		}

		if (gArmy.size() > 0) {
			writeln("green wins");
			printArmy(gArmy);
			return;
		}

		if (bArmy.size() > 0) {
			writeln("blue wins");
			printArmy(bArmy);
			return;
		}
	}

	private Boolean parseNextTestCase() {
		if (!ready()) return false;
		if (caseNum >= numCases) return false;

		processParams();
		buildArmies();

		while (gArmy.size() > 0 && bArmy.size() > 0) {
			assignBattlefields();
			fight();
			sendBackToArmies();
		}
		printResults();
		if (caseNum < numCases-1) writeln();

		caseNum++;
		return true;
	}

	public Integer[] getInts() {
		String line = getNextInputLine();
		String[] tokens = line.split("\\s+");
		ArrayList<Integer> lst = new ArrayList<>();
		for (String s : tokens) lst.add(Integer.parseInt(s));

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
