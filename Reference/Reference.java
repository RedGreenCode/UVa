// Sample Java syntax for programming puzzles. I don't use Java
// much in real life, so when I need to look something up, I add it to
// this file as a reference.
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

	// main method must match this signature exactly
	public static void main(String[] args) {
		String line = "";
		String[] tokens = null;
		
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
		for (int i=0; i<line.length(); i+=2) {
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
		for (int i=0; i<line.length(); i++) {
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
		int i = 0;
		for (String str : tokens) nums[i++] = Integer.parseInt(str);
		Arrays.sort(nums);
		for (int j=0; j<nums.length; j++) System.out.print(nums[j] + " ");
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
		tokens = line.split(" ");
		for (String s : tokens) System.out.print(String.format("%03d", Integer.parseInt(s)) + " ");
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
		for (int j=0; j<3; j++) {
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
		for (int j=0; j<line.length(); j++) System.out.print(((int)line.charAt(j)) + " ");
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
		for (int j=0; j<line.length(); j++) System.out.print((line.charAt(j) - 'a') + " ");
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

		// --> Next example goes above this line

	}	// end of public static void main
}	// end of public class Reference
