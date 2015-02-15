// Sample Java syntax for programming puzzles. I don't use Java
// much in real life, so when I need to look something up, I add it to
// this file as a reference.
//
// For more information, see http://www.redgreencode.com/about-project-462/

// Lots of useful stuff in here
import java.util.*;

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
		for (String s : tokens) sum += Integer.parseInt(s);
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
	}
}
