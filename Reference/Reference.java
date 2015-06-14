// Sample Java syntax for programming puzzles. When I need to look
// up any Java syntax, I add it to this file as a reference.
//
// For more information, see http://www.redgreencode.com/about-project-462/

import java.util.*;	// Lots of useful stuff in here
import java.text.DecimalFormat;

// Filename (Reference.java) should match the public class name (Reference)
public class Reference {
	
	// Programming puzzles usually get plain text input from stdin.
	// Scanner is a useful class to parse it.
	Scanner stdin;

	private Reference() {
		stdin = new Scanner(System.in);
	}

	// Return the next line from stdin, skipping lines
	// that start with #
	private String Next() {
		String line = "";
		if (!stdin.hasNextLine()) return line;

		line = stdin.nextLine();
		while (line.charAt(0) == '#') {
			if (!stdin.hasNextLine()) {
				line = "";
				break;
			}
			line = stdin.nextLine();
		}
		return line;
	}

	private static class SortDescending implements Comparator<Integer> {
		public int compare(Integer i1, Integer i2) {
			return i2-i1;
		}
	}

	private static class Planet implements Comparable<Planet> {
		public String Name;
		public double Density;

		public int compareTo(Planet p) {
			double d = Density - p.Density;
			if (d < 0) return -1;
			else if (d > 0.001) return 1;
			else return 0;
		}
	}

	// main method must match this signature exactly
	public static void main(String[] args) {
		String line = "";
		String[] tokens = null;
		int i = 0;
		int j = 0;
		
		Reference r = new Reference();
		
		// --> Print the input line
		// println
		System.out.println(r.Next());


		// --> Convert space delimiters to dash delimiters, and end line with a dash
		// split
		line = r.Next();
		tokens = line.split(" ");	// delimiter must be a string, not a char
		for (String s : tokens) System.out.print(s + "-");
		System.out.println();


		// --> Sum space-delimited integers and print result
		// parseInt
		line = r.Next();
		tokens = line.split(" ");
		int sum = 0;
		// !s.equals("") check is required to avoid errors when there are
		// multiple spaces between tokens in the input
		for (String s : tokens) if (!s.equals("")) sum += Integer.parseInt(s);
		System.out.println(sum);


		// --> Print every other character in the input, starting with the first character
		// charAt
		line = r.Next();
		for (i=0; i<line.length(); i+=2) {
			System.out.print(line.charAt(i));
		}
		System.out.println();


		// --> Fill a StringBuilder, an array, and an ArrayList with the characters
		// from the input string. Then print the size/length of the string,
		// StringBuilder, array, and ArrayList.
		//
		// length, length(), and size()
		line = r.Next();
		StringBuilder sb = new StringBuilder();
		char[] arrLetters = new char[line.length()];
		// List<char> doesn't work here since Java Generics can't use primitive types.
		// http://stackoverflow.com/questions/19530816
		List<Character> lettersList = new ArrayList<Character>();
		for (i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			sb.append(c);	// append for StringBuilder
			arrLetters[i] = c;
			lettersList.add(c);	// add for ArrayList
		}
		System.out.println(line.length());				// length() for String and StringBuilder
		System.out.println(sb.length());
		System.out.println(arrLetters.length);		// length for primitive arrays
		System.out.println(lettersList.size());		// size() for other collections


		// --> Fill a 5x5 array with the first 25 characters of the input string,
		// and print it in a 5x5 grid
		//
		// char[][]
		line = r.Next();
		char[][] grid = new char[5][5];
		int pos = 0;
		for (int row=0; row<5; row++) {
			for (int col=0; col<5; col++) {
				grid[row][col] = line.charAt(pos++);
			}
		}
		for (int row=0; row<5; row++) {
			for (int col=0; col<5; col++) {
				System.out.print(grid[row][col]);
			}
			System.out.println();
		}

		// --> Print the first input string in all uppercase and the second one
		// in all lowercase
		//
		// Character.toUpperCase
		// Character.toLowerCase
		line = r.Next();
		// Can't do this -- error: for-each not applicable to expression type
		//for (char c : line) System.out.print(Character.toUpperCase(c));
		for (char c : line.toCharArray()) System.out.print(Character.toUpperCase(c));
		System.out.println();
		line = r.Next();
		for (char c : line.toCharArray()) System.out.print(Character.toLowerCase(c));
		System.out.println();

		// --> Take the square root of each number in a space-delimited list, convert
		// it to an integer, and print it on one line (space-delimited)
		//
		// (int)Math.sqrt
		line = r.Next();
		tokens = line.split(" ");
		for (String s : tokens) {
			int n = Integer.parseInt(s);
			System.out.print((int)Math.sqrt(n) + " ");
		}
		System.out.println();

		// --> Sort an array and a collection of integers
		//
		// Arrays.sort()
		// Collections.sort()
		line = r.Next();
		tokens = line.split(" ");
		int[] nums = new int[tokens.length];
		i = 0;
		for (String str : tokens) nums[i++] = Integer.parseInt(str);
		Arrays.sort(nums);
		for (j=0; j<nums.length; j++) System.out.print(nums[j] + " ");
		System.out.println();

		line = r.Next();
		tokens = line.split(" ");
		// Can't do this -- error: unexpected type (have to use Integer, not int) 
		//List<int> numsList = new ArrayList<int>();
		List<Integer> numsList = new ArrayList<Integer>();
		for (String str : tokens) numsList.add(Integer.parseInt(str));
		Collections.sort(numsList);
		for (int n : numsList) System.out.print(n + " ");
		System.out.println();

		// --> Insert a list of integers into a hash table. Then for each
		// integers in a second list, indicate whether it exists in the hash table
		//
		// HashMap
		line = r.Next();
		tokens = line.split(" ");
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (String str : tokens) hm.put(Integer.parseInt(str), 0);

		line = r.Next();
		tokens = line.split(" ");
		for (String str : tokens) {
			int n = Integer.parseInt(str);
			System.out.print(hm.containsKey(n) ? "yes " : "no ");
		}
		System.out.println();

		// --> Print the substring of a string from the 3rd to the 10th character
		//
		// String.substring(start inclusive, end exclusive)
		line = r.Next();
		System.out.println(line.substring(2, 10));

		// --> Read integers and print them as 3 digits, padded with zeros if necessary
		//
		// String.format()
		line = r.Next();
		tokens = line.split("\\s+");
		for (String s : tokens) System.out.print(String.format("%03d", Integer.parseInt(s)) + " ");
		System.out.println();

		// --> Read integers and print them as 3 digits, padded with spaces if necessary
		//
		// String.format()
		line = r.Next();
		tokens = line.trim().split("\\s+");
		for (String s : tokens) System.out.print(String.format("%3s", Integer.parseInt(s)) + " ");
		System.out.println();

		// --> Sum space-delimited integers, ignoring characters mixed in
		// NumberFormatException
		line = r.Next();
		tokens = line.split(" ");
		sum = 0;
		for (String s : tokens) {
			if (!s.equals("")) {
				try {
					sum += Integer.parseInt(s);
				} catch (NumberFormatException nfe) {}
			}
		}
		System.out.println(sum);

		// --> Print the ceiling and floor (space-delimited) of each number in the input
		// Math.ceil
		// Math.floor
		line = r.Next();
		tokens = line.split(" ");
		for (String s : tokens) {
			double d = Double.parseDouble(s);
			System.out.println(Math.ceil(d) + " " + Math.floor(d));
		}

		// --> For the next three lines, print true if the first string contains the second string, false otherwise
		// contains
		for (j=0; j<3; j++) {
			line = r.Next();
			tokens = line.split(" ");
			System.out.println(tokens[0].contains(tokens[1]));
		}

		// --> Print the input with leading and trailing spaces removed
		// trim
		line = r.Next();
		System.out.println(line.trim());

		// --> Convert pipe delimiters to dash delimiters, and end line with a dash
		// split (pipe)
		line = r.Next();
		tokens = line.split("\\|");	// split accepts a regex, so | has to be escaped
		for (String s : tokens) System.out.print(s + "-");
		System.out.println();

		// --> Convert the input string to its ASCII values (space-delimited)
		line = r.Next();
		for (j=0; j<line.length(); j++) System.out.print(((int)line.charAt(j)) + " ");
		System.out.println();

		// --> Assuming the input values represent ASCII values, print the string they represent
		line = r.Next();
		tokens = line.split(" ");
		for (String s : tokens) {
			int c = Integer.parseInt(s);
			System.out.print((char)c);
		}
		System.out.println();

		// --> Convert the input string to numerical values (space-delimited), where
		// lowercase a = 0, b = 1, and so on
		line = r.Next();
		for (j=0; j<line.length(); j++) System.out.print((line.charAt(j) - 'a') + " ");
		System.out.println();

		// --> Assuming the input values represent lowercase letters, where a=0, b=1, etc.,
		// print the corresponding string
		line = r.Next();
		tokens = line.split(" ");
		for (String s : tokens) {
			int c = Integer.parseInt(s);
			System.out.print((char)(c + 'a'));
		}
		System.out.println();

		// --> Read whitespace-delimited integers into an array; print them out; create a
		// reference to the array; modify the first element; print the original array;
		// create a copy of the array; modify the first element; print the original array.
		//
		// clone
		line = r.Next();
		tokens = line.trim().split("\\s+");
		nums = new int[tokens.length];
		i = 0;
		for (String str : tokens) nums[i++] = Integer.parseInt(str);
		for (int n : nums) System.out.print(n + " ");
		System.out.println();
		int[] nums2 = nums;	// this is a reference to nums, so changes to nums2 will affect nums
		nums2[0] = 99;
		for (int n : nums) System.out.print(n + " ");
		System.out.println();
		int[] nums3 = nums.clone();	// this is a copy of nums, so changes to nums3 will NOT affect nums
		nums3[0] = 0;
		for (int n : nums) System.out.print(n + " ");
		System.out.println();

		// --> Initialize an array of integer literals, and print the result
		int[] literals = new int[] { 9, 8, 7, 6, 5 };
		for (int n : literals) System.out.print(n + " ");
		System.out.println();

		// --> Read whitespace-delimited integers into an ArrayList; print whether the array contains 23
		// contains
		line = r.Next();
		tokens = line.trim().split("\\s+");
		numsList = new ArrayList<Integer>();
		for (String str : tokens) numsList.add(Integer.parseInt(str));
		System.out.println(numsList.contains(23));

		// --> Read whitespace-delimited integers into an ArrayList; convert it to an array, and print the result
		// toArray
		line = r.Next();
		tokens = line.trim().split("\\s+");
		numsList = new ArrayList<Integer>();
		for (String str : tokens) numsList.add(Integer.parseInt(str));
		Integer[] numsArr = numsList.toArray(new Integer[numsList.size()]);	// have to use Integer, not int
		for (int n : numsArr) System.out.print(n + " ");
		System.out.println();

		// --> Divide the first input by the second input and print the
		// result to three decimal places
		//
		// DecimalFormat
		line = r.Next();
		tokens = line.trim().split("\\s+");
		double n1 = Double.parseDouble(tokens[0]);
		double n2 = Double.parseDouble(tokens[1]);
		DecimalFormat df = new DecimalFormat("0.000");
		System.out.println(df.format(n1/n2));

		// --> Read space-delimited month, day, year, and an increment in days.
		// Calculate the date produced by the input date plus the increment. Print the result in
		// mm/dd/yyyy format, with each field padded with zeros to the indicated width.
		//
		// UVa 11947
		//
		// GregorianCalendar
		line = r.Next();
		tokens = line.trim().split("\\s+");
		Calendar cal = GregorianCalendar.getInstance();
		int month = Integer.parseInt(tokens[0]);
		int day = Integer.parseInt(tokens[1]);
		int year = Integer.parseInt(tokens[2]);
		int days = Integer.parseInt(tokens[3]);
		cal.set(year, (month-1), day, 0, 0, 0); // month is 0-based, unlike human date format
		cal.add(Calendar.DATE, days);

		int newm = cal.get(Calendar.MONTH);
		int newd = cal.get(Calendar.DAY_OF_MONTH);
		int newy = cal.get(Calendar.YEAR);

		String sm = String.format("%02d", (newm+1)); // convert back to 1-based for humans
		String sd = String.format("%02d", newd);
		String sy = String.format("%04d", newy);

		System.out.println(sm + "/" + sd + "/" + sy);

		// --> Print the first element of an integer array and a Boolean
		// array, and print a Boolean variable.
		//
		// Boolean array
		int[] a = new int[1];	// gets initialized to {0} automatically
		Boolean[] b = new Boolean[1];	// this is {null}; no automatic initialization to false
		Boolean c = true;	// have to initialize before printing; otherwise, error: variable c might not have been initialized
		System.out.println(a[0]);	// 0
		System.out.println(b[0]);	// null
		System.out.println(c);		// true

		// --> Read 5 rows and 5 columns into a 5x5 grid (2D integer array)
		// Print the grid using a nested for loop, but break out of the
		// inner loop whenever the current value is divisible by 6. This
		// will result in printing only the bottom left section of the grid.
		//
		// UVa 10855
		//
		// nested loops for two-dimensional arrays
		int[][] sq = new int[5][5];
		for (i=0; i<5; i++) {
			line = r.Next();
			tokens = line.trim().split("\\s+");
			for (j=0; j<tokens.length; j++) {
				sq[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		for (i=0; i<5; i++) {
			for (j=0; j<5; j++) {
				if (sq[i][j] % 6 == 0) break;
				System.out.print(String.format("%2d ", sq[i][j]));
			}
			System.out.println();
		}

		// --> Sort the input list of integers in ascending and descending
		// order using two PriorityQueues
		//
		// UVa 10107
		// PriorityQueue
		SortDescending sortd = new SortDescending();
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>(100);
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(100, sortd);
		line = r.Next();
		tokens = line.trim().split("\\s+");
		for (j=0; j<tokens.length; j++) {
			i = Integer.parseInt(tokens[j]);
			pq1.add(i);
			pq2.add(i);
		}
		for (j=0; j<tokens.length; j++) {
			System.out.print(pq1.poll() + " ");
		}
		System.out.println();
		for (j=0; j<tokens.length; j++) {
			System.out.print(pq2.poll() + " ");
		}
		System.out.println();

		// --> Read a list of planet names and density values. Then print them in
		// descending order of density.
		//
		// UVa 10258
		// Collections.sort
		line = r.Next();
		tokens = line.split(" ");
		List<Planet> planets = new ArrayList<Planet>();
		for (i=0; i<tokens.length; i+=2) {
			Planet p = new Planet();
			p.Name = tokens[i];
			p.Density = Double.parseDouble(tokens[i+1]);
			planets.add(p);
		}
		Collections.sort(planets);
		for (i=0; i<planets.size(); i++) {
			Planet p = planets.get(i);
			System.out.println(p.Name + " has an average density of " + p.Density + " g/cm^3");
		}

		// --> Starting with 00000000, set each bit from right to left,
		// and print the results as integers. Then unset each bit from
		// left to right, and print the results again. Then toggle each
		// bit from right to left and print the results.
		//
		// UVa 10264
		// bitwise operations
		int num = 0;
		for (i=0; i<8; i++) {
			int bits = 1 << i;
			num |= bits;	// set
			System.out.print(num + " ");
		}
		System.out.println();
		for (i=7; i>=0; i--) {
			int bits = ~(1 << i);
			num &= bits;	// unset
			System.out.print(num + " ");
		}
		System.out.println();
		for (i=0; i<8; i++) {
			int bits = 1 << i;
			num ^= bits;	// toggle
			System.out.print(num + " ");
		}
		System.out.println();

		// --> Read pairs of integers, use the first number in each pair
		// as a base and the second as an exponent, and print space-delimited
		// results as integers.
		//
		// UVa 10264
		// Math.pow
		line = r.Next();
		tokens = line.trim().split("\\s+");
		for (i=0; i<tokens.length; i+=2) {
			int base = Integer.parseInt(tokens[i]);
			int exp = Integer.parseInt(tokens[i+1]);
			System.out.print((int)Math.pow(base, exp) + " ");
		}
		System.out.println();

		// --> Read two intervals (n1,n2) and (n3,n4). Print true
		// if they overlap and false if they don't. There are four
		// input lines for this test.
		//
		// UVa 11926
		// BitSet
		for (i=0; i<4; i++) {
			line = r.Next();
			tokens = line.trim().split("\\s+");
			BitSet b1 = new BitSet();
			b1.set(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			BitSet b2 = new BitSet();
			b2.set(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
			System.out.println(b1.intersects(b2));
		}

		// --> Read the base-10 integer from the first line and print its 8-bit binary
		// representation. Read the 8-bit binary representation from the second
		// line, and print its base-10 integer value. Use little endian
		// ordering (LSB on the right, as normally written for humans) in
		// both cases.
		//
		// UVa 11933
		// BitSet
		line = r.Next();
		int n = Integer.parseInt(line);
		BitSet bs = BitSet.valueOf(new long[] {n});
		for (i=7; i>=0; i--)
			if (bs.get(i)) System.out.print("1"); else System.out.print("0");
		System.out.println();
		line = r.Next();
		bs = new BitSet();
		for (i=0; i<line.length(); i++)
			if (line.charAt(i) == '1') bs.set(7-i); else bs.clear(7-i);
		System.out.println(bs.toLongArray()[0]);

		// --> Read the list of space-delimited integers from the input into two linked lists.
		// For the first list, add each value to the head. For the second list, add each value
		// to the tail. Iterate through each list, printing only the even values.
		//
		// UVa 11988
		// LinkedList
		line = r.Next();
		tokens = line.trim().split("\\s+");
		LinkedList<Integer> lst = new LinkedList<Integer>();
		LinkedList<Integer> lst2 = new LinkedList<Integer>();
		for (i=0; i<tokens.length; i++) {
			lst.addFirst(Integer.parseInt(tokens[i]));
			lst2.add(Integer.parseInt(tokens[i]));
		}
		ListIterator<Integer> listIterator = lst.listIterator();
		ListIterator<Integer> listIterator2 = lst2.listIterator();
		while (listIterator.hasNext()) {
		    j = listIterator.next();
		    if (j % 2 == 0) System.out.print(j + " ");
		}
		System.out.println();
		while (listIterator2.hasNext()) {
		    j = listIterator2.next();
		    if (j % 2 == 0) System.out.print(j + " ");
		}
		System.out.println();

		/*
		// --> Describe this section
		//
		// UVa #####
		// Language feature
		line = r.Next();
		tokens = line.trim().split("\\s+");
		for (i=0; i<tokens.length; i++) {
			System.out.print(tokens[i] + " ");
		}
		System.out.println();
		*/
		
		// --> Use block above as a template

	}	// end of public static void main
}	// end of public class Reference
