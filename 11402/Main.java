// Java solution to 11402 - Ahoy, Pirates!
//
// For more information, see http://www.redgreencode.com/solving-uva-11402-with-segment-trees/
//
// Some code and/or ideas come from:
// 	* http://algs4.cs.princeton.edu/99misc/SegmentTree.java.html
//	* https://github.com/boxme/cs2010_Desmond/blob/master/UVa/UVa%2011402/Main.java

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
	Boolean debug = false;
	
	public int[] pirates;

	BufferedReader stdin;
	BufferedWriter stdout;
	StringBuilder sb;
	SegmentTree st;

	// Create the binary string
	public int getPirates() {
		String line = getNextInputLine();
		int M = Integer.parseInt(line);
		int size = 0;
		for (int i=0; i<M; i++) {
			line = getNextInputLine();
			int T = Integer.parseInt(line);
			String S = getNextInputLine();
			int slen = S.length();
			int[] ints = new int[slen];
			for (int k=0; k<slen; k++) ints[k] = S.charAt(k) - '0';
			for (int j=0; j<T; j++) {
				for (int k=0; k<slen; k++) {
					pirates[size++] = ints[k];
				}
			}
		}
		
		return size;
	}

	private Boolean parseNextTestCase() {
		if (!ready()) return false;
		if (caseNum >= numCases) return false;

		writeln("Case " + (caseNum+1) + ":");

		// we don't know the total length at this point, so just reserve
		// the max size that we're going to need
		pirates = new int[1100000];
		st = new SegmentTree(getPirates());
		String line = getNextInputLine();
		int Q = Integer.parseInt(line);
		int snum = 0;
		for (int i=0; i<Q; i++) {
			line = getNextInputLine();
			String[] tokens = line.split("\\s+");
			int a = Integer.parseInt(tokens[1]);
			int b = Integer.parseInt(tokens[2]);
			switch (tokens[0]) {
				case "E":
					st.update(1, a, b, st.CLEAR);
					break;
				case "F":
					st.update(1, a, b, st.SET);
					break;
				case "I":
					st.update(1, a, b, st.FLIP);
					break;
				case "S":
					writeln("Q" + (++snum) + ": " + st.RSQ(a, b));
					break;
			}
		}

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

	// Custom segment tree implementation for this problem
	// Doesn't implement range max/min methods
	class SegmentTree {

			// These five integers together make up a node in the segment tree.
			// Arrays are indexed starting at 1 (the root). Children of node p
			// are at 2*p and 2*p+1.
			private int[] sum;
			private int[] pendingVal;	// defaults to 0 (DONOTHING)
			private int[] nfrom;
			private int[] nto;
			private int[] nsize;

			private int heapSize;

			public final int DONOTHING = 0, SET = 10, CLEAR = 20, FLIP = 30;

			public SegmentTree(int size) {
					heapSize = size << 2;	// larger than necessary
					sum = new int[heapSize];
					pendingVal = new int[heapSize];
					nfrom = new int[heapSize];
					nto = new int[heapSize];
					nsize = new int[heapSize];

					build(1, 0, size);
			}

			// Build the segment tree, starting from node v
			private void build(int v, int from, int size) {
					nfrom[v] = from;
					nto[v] = from + size - 1;
					nsize[v] = size;

					if (size == 1) {
						// Leaf node, so the sum is just the underlying data element
						sum[v] = pirates[from];
					} else {
						// Build children
						int c1 = v*2;
						int c2 = c1+1;
						int s2 = size/2;
						build(c1, from, s2);
						build(c2, from + s2, size - s2);

						// Sum of range = sum of first half + sum of second half
						sum[v] = sum[c1] + sum[c2];
					}
			}

			// Range sum query for range [from, to], starting from the root
			public int RSQ(int from, int to) {
					return RSQ(1, from, to);
			}

			// Range sum query for range [from, to], starting from node v
			private int RSQ(int v, int from, int to) {
					// If we set or cleared all of the values in this node, and the node range completely contains
					// the target range, then all of the values in the target range are the same, so we can calculate
					// the sum directly without considering any child nodes.
					if (contains(nfrom[v], nto[v], from, to)) {
						// All values are 1, so sum is the range size
						if (pendingVal[v] == SET) return (to - from + 1);

						// All values are 0, so sum is 0
						if (pendingVal[v] == CLEAR) return 0;
					}

					// If the target range completely contains the node range, then its whole sum
					// contributes to the range sum.
					if (contains(from, to, nfrom[v], nto[v])) {
							propagate(v);		// update node sum and child pending values
							return sum[v];
					}

					// If none of the previous conditions are true but the target range overlaps
					// with the start or the end of the node range, then it contributes part of its
					// sum the target sum, but we don't yet know how much. To get that information,
					// we need to travel farther down the tree to find nodes with more targeted ranges.
					if (intersects(from, to, nfrom[v], nto[v])) {
							propagate(v);		// update node sum and child pending values
							int c1 = v*2;
							int c2 = c1+1;
							int leftSum = RSQ(c1, from, to);
							int rightSum = RSQ(c2, from, to);

							return leftSum + rightSum;
					}

					// If none of the previous conditions are true, then the target range falls completely
					// before or completely after the range of node v. Therefore, node v's range does
					// not contribute to the target sum.
					return 0;
			}

			// Update range [from, to] with a value, starting at node v
			private void update(int v, int from, int to, int value) {
					propagate(v);		// update node sum and child pending values

					// If the target range completely contains the node range, then we
					// can (lazily) update the whole node range to the new value.
					if (contains(from, to, nfrom[v], nto[v])) {
							change(v, value);
							return;
					}

					// If the previous conditions is false but the target range overlaps
					// with the start or the end of the node range, then we have to update
					// part of it, but we don't yet know which part. To get that information,
					// we need to travel farther down the tree to find nodes with more targeted ranges.
					if (intersects(from, to, nfrom[v], nto[v])) {
							int c1 = 2*v;
							int c2 = c1+1;
							update(c1, from, to, value);
							update(c2, from, to, value);

							sum[v] = sum[c1] + sum[c2];
					}

					// If none of the previous conditions are true, then the target range falls completely
					// before or completely after the range of node v. Therefore, we don't need to update
					// node v other than propagating any pending values.
			}

			// If node v has a pending value, update its sum and propagate the pending value
			// to its children (if it has any).
			private void propagate(int v) {
				if (pendingVal[v] == DONOTHING) return;	// nothing to propagate
				change(v, pendingVal[v]);
				pendingVal[v] = DONOTHING; //unset the pending propagation value
			}

			// Update node v's sum using a value, and set the pending values
			// for any child nodes so they can be lazily updated when necessary.
			private void change(int v, int value) {
					if (value == DONOTHING) return;
					sum[v] = getSum(v, value);

					// If v is a leaf node, there's nothing more to update
					if (nfrom[v] == nto[v]) return;

					// Otherwise, update children
					int c1 = v*2;
					int c2 = c1+1;
					if (value == SET || value == CLEAR) {
						// For SET and CLEAR, we can directly update the children,
						// since every array position will get the same value.
						pendingVal[c1] = pendingVal[c2] = value;
					} else {
						// For FLIP, every array position could end up with a
						// different value, so the new pending value depends
						// on the previous pending value.
						pendingVal[c1] = newPendingVal(c1);
						pendingVal[c2] = newPendingVal(c2);
					}
			}

			// Calculate the sum of a node range based on a value
			private int getSum(int v, int value) {
					switch(value) {
						case CLEAR:
							// All elements are 0, so sum is 0
							return 0;
						case SET:
							// All elements are 1, so sum is the range size
							return nsize[v];
						case FLIP:
							// Each element flips to its opposite, so the
							// sum is also the "opposite" of the previous sum.
							return nsize[v] - sum[v];
					}

					// Getting here would be an error, so make sure the test case fails
					return Integer.MIN_VALUE;
			}

			// This is used for a FLIP action, so return the opposite of the
			// current pending value.
			private int newPendingVal(int v) {
				switch(pendingVal[v]) {
					case CLEAR: return SET;
					case SET: return CLEAR;
					case FLIP: return DONOTHING;
					case DONOTHING: return FLIP;
				}

				// Getting here would be an error, so make sure the test case fails
				return Integer.MIN_VALUE;
			}

			// Returns true if the range [from1, to1] completely contains the
			// range [from2, to2], so that no element of the second range is outside
			// of the first range.
			private boolean contains(int from1, int to1, int from2, int to2) {
					return from2 >= from1 && to2 <= to1;
			}

			// Returns true if the range [from1, to1] overlaps with either from2
			// or to2. Note that there are several scenarios where this can happen,
			// so in this implementation, contains() is always called first
			// to narrow down the possibilities.
			//
			// In the diagrams:
			// 		from1 ( ... ) to1
			//		from2 [ ... ] to2
			private boolean intersects(int from1, int to1, int from2, int to2) {
					return from1 <= from2 && to1 >= from2   //  (.[..)..] or (.[...]..)
									|| from1 >= from2 && from1 <= to2; // [.(..]..) or [..(..)..
			}
	}
}
