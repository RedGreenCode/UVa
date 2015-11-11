import java.util.*;	// Lots of useful stuff in here
import java.text.DecimalFormat;	// required for formatting decimal output
import static java.lang.Math.*;	// for convenient access to math functions

/**
 * This class contains sample Java code focusing on the subset of Java
 * that is useful for programming puzzles.
 * <p>
 * For more information, see <a href="http://www.redgreencode.com/about-project-462/">About Project 462</a>.
 */
public class Reference {
// Filename (Reference.java) must match the public class name (Reference)

	/**
	 * To debug just one test, call its test method from here.
	 */
	public void runOneTest() {
		countIntegers2();
	}

	/**
	 * This method should call all test methods in sequence. In general, you shouldn't change
	 * the test order, so that you can compare actual output with expected output, which helps
	 * verify that your tests are behaving as expected.
	 */
	public void runAllTests() {
		ru.writeln("--start--");
		printInputLines();
		splitString();
		parseIntegers();
		printAlternateChars();
		printLengthAndSize();
		printGrid();
		changeCharacterCase();
		integerSquareRoot();
		sortIntegers();
		hashLookup();
		printSubstring();
		formatIntegersZeros();
		formatIntegersSpaces();
		sumIntegers();
		ceilingFloor();
		stringContains();
		stringTrim();
		splitStringPipe();
		getASCII();
		ASCIItoChar();
		charToOrdinal();
		ordinalToChar();
		cloneArray();
		initIntArray();
		lookupElement();
		listToArray();
		formatDecimal();
		incrementDate();
		arrayInit();
		loopBreak();
		sortAscendingDescending();
		sortObjects();
		flipBits();
		exponentiateIntegers();
		checkIntervalOverlap();
		convertNumberBase();
		processLinkedLists();
		reverseString();
		dontReverseString();
		ru.writeln("--end--");
	}

	// Start of test section.

	/**
	 * Print a string.<p>
	 * Input: a line of text<p>
	 * Output: the line of text, unchanged<p>
	 * Language features: BufferedReader, BufferedWriter
	 */
	public void printInputLines() {
		for (String line : ru.getTestInput()) ru.write(line);
		ru.writeln();
	}

	/**
	 * Change the delimiters in a string.<p>
	 * Input: a line of space-delimited strings<p>
	 * Output: the same strings, delimited by dashes, with a trailing dash<p>
	 * Language features: String.split
	 */
	public void splitString() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.split(" ");	// delimiter must be a string, not a char
			for (String s : tokens) ru.write(s + "-");
		}
		ru.writeln();
	}

	/**
	 * Sum a list of integers.<p>
	 * Input: a line of space-delimited integers<p>
	 * Output: the sum of the integers<p>
	 * Language features: Integer.parseInt
	 */
	public void parseIntegers() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");	// this regex consumes all whitespaces delimiters between tokens
			int sum = 0;
			for (String s : tokens) sum += Integer.parseInt(s);
			ru.writeln(sum);
		}
	}

	/**
	 * Print every other character in a string.<p>
	 * Input: a line of text<p>
	 * Output: the line of text, with every other character removed<p>
	 * Language features: String.charAt
	 */
	public void printAlternateChars() {
		for (String line : ru.getTestInput()) {
			for (int i=0; i<line.length(); i+=2)
				ru.write(line.charAt(i));
			ru.writeln();
		}
	}

	/**
	 * Print sizes of collections.<p>
	 * 
	 * Initialize a StringBuilder, an array, and an ArrayList with the characters
	 * from the input string. Then print the size/length of the string,
	 * StringBuilder, array, and ArrayList.<p>
	 *
	 * Input: a line of text<p>
	 * Output: <p>
	 * Language features: String.length(), StringBuilder.length(), Array.length, ArrayList.size()
	 */
	public void printLengthAndSize() {
		for (String line : ru.getTestInput()) {
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
			ru.writeln(line.length());				// length() for String and StringBuilder
			ru.writeln(sb.length());
			ru.writeln(arrLetters.length);		// length for primitive arrays
			ru.writeln(lettersList.size());		// size() for other collections
		}
	}

	/**
	 * Create and print a 2D grid.<p>
	 *
	 * Fill a 5x5 array with the first 25 characters of the input string,
	 * and print it in a 5x5 grid.<p>
	 *
	 * Input: a line of text<p>
	 * Output: the text in the form of a 5x5 grid<p>
	 * Language features: 2D array
	 * UVa problem: 11581
	 */
	public void printGrid() {
		for (String line : ru.getTestInput()) {
			char[][] grid = new char[5][5];
			int pos = 0;
			for (int row=0; row<5; row++) {
				for (int col=0; col<5; col++) {
					grid[row][col] = line.charAt(pos++);
				}
			}
			for (int row=0; row<5; row++) {
				for (int col=0; col<5; col++) {
					ru.write(grid[row][col]);
				}
				ru.writeln();
			}
		}
	}

	/**
	 * Convert characters in a string to a different case.<p>
	 * Input: two strings<p>
	 * Output: the first string in all uppercase, and the second string in all lowercase<p>
	 * Language features: Character.toUpperCase, Character.toLowerCase
	 * UVa problems: 10945, 156
	 */
	public void changeCharacterCase() {
		ArrayList<String> lines = ru.getTestInput();
		for (int i=0; i<lines.size(); i+=2) {
			// Can't do this -- error: for-each not applicable to expression type
			//for (char c : line) System.out.print(Character.toUpperCase(c));
			for (char c : lines.get(i).toCharArray()) ru.write(Character.toUpperCase(c));
			ru.writeln();
			for (char c : lines.get(i+1).toCharArray()) ru.write(Character.toLowerCase(c));
			ru.writeln();
		}
	}

	/**
	 * Calculate integer square roots.<p>
	 * Input: a space-delimited list of integers<p>
	 * Output: a space-delimited list of the integer square root of each input integer<p>
	 * Language features: Math.sqrt
	 * UVa problem: 10920
	 */
	public void integerSquareRoot() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) {
				int n = Integer.parseInt(s);
				ru.write((int)sqrt(n));
				ru.write(" ");
			}
			ru.writeln();
		}
	}

	/**
	 * Sort an array and a collection of integers.<p>
	 * Input: two lines of space-delimited integers<p>
	 * Output: two lines of space-delimited integers, each one sorted in ascending order<p>
	 * Language features: Arrays.sort, Collections.sort
	 * UVa problems: 10258, 1061, 11926, 156, 195, 454, 732
	 */
	public void sortIntegers() {
		ArrayList<String> lines = ru.getTestInput();
		for (int i=0; i<lines.size(); i+=2) {
			// Sort an array
			String[] tokens = lines.get(0).split("\\s+");
			int[] nums = new int[tokens.length];
			int j = 0;
			for (String str : tokens) nums[j++] = Integer.parseInt(str);
			Arrays.sort(nums);
			for (int k=0; k<nums.length; k++) ru.write(nums[k] + " ");
			ru.writeln();

			// Sort an ArrayList
			tokens = lines.get(1).split("\\s+");
			// Can't do this -- error: unexpected type (have to use Integer, not int) 
			//List<int> numsList = new ArrayList<int>();
			List<Integer> numsList = new ArrayList<Integer>();
			for (String str : tokens) numsList.add(Integer.parseInt(str));
			Collections.sort(numsList);
			for (int n : numsList) ru.write(n + " ");
			ru.writeln();
		}
	}

	/**
	 * Look up integers in a hash table.<p>
	 * Input: a list of integers to insert into a hash table; a second list of integers to look up<p>
	 * Output: for each integer, "yes" if it exists in the table, "no" if it doesn't<p>
	 * Language features: HashMap
	 * UVa problem: rarely necessary
	 */
	public void hashLookup() {
		ArrayList<String> lines = ru.getTestInput();
		for (int i=0; i<lines.size(); i+=2) {
			// Insert into the table
			String[] tokens = lines.get(0).split("\\s+");
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			for (String str : tokens) hm.put(Integer.parseInt(str), 0);

			// Lookup
			tokens = lines.get(1).split("\\s+");
			for (String str : tokens) {
				int n = Integer.parseInt(str);
				ru.write(hm.containsKey(n) ? "yes " : "no ");
			}
			ru.writeln();
		}
	}

	/**
	 * Print a substring of a string.<p>
	 * Input: a line of text<p>
	 * Output: the substring of the line, from the 3rd to the 10th character inclusive<p>
	 * Language features: String.substring
	 * UVa problems: 11340, 11947
	 */
	public void printSubstring() {
		for (String line : ru.getTestInput()) {
			ru.writeln(line.substring(2, 10));
		}
	}

	/**
	 * Print formatted integers (leading zeros).<p>
	 * Input: space-delimited integers<p>
	 * Output: integers with 3 digits, padded with zeros if necessary<p>
	 * Language features: String.format
	 * UVa problems: 161, 11947
	 */
	public void formatIntegersZeros() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) ru.write(String.format("%03d", Integer.parseInt(s)) + " ");
			ru.writeln();
		}
	}

	/**
	 * Print formatted integers (leading spaces).<p>
	 * Input: space-delimited integers<p>
	 * Output: integers with 3 digits, padded with spaces if necessary<p>
	 * Language features: String.format
	 * UVa problem: 556
	 */
	public void formatIntegersSpaces() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) ru.write(String.format("%3s", Integer.parseInt(s)) + " ");
			ru.writeln();
		}
	}

	/**
	 * Sum space-delimited integers, ignoring characters mixed in.<p>
	 * Input: space-delimited integers and characters<p>
	 * Output: sum of the integers, ignoring the characters<p>
	 * Language features: Integer.parseInt, catching NumberFormatException
	 * UVa problem: 161
	 */
	public void sumIntegers() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			int sum = 0;
			for (String s : tokens) {
				if (!s.equals("")) {
					try {
						sum += Integer.parseInt(s);
					} catch (NumberFormatException nfe) {}
				}
			}
			ru.writeln(sum);
		}
	}

	/**
	 * Print ceiling and floor of floating point numbers.<p>
	 * Input: space-delimited list of doubles<p>
	 * Output: space-delimited list of ceiling and floor of each number<p>
	 * Language features: Double.parseDouble, Math.ceil, Math.floor
	 * UVa problems: 10920, 637
	 */
	public void ceilingFloor() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) {
				double d = Double.parseDouble(s);
				ru.writeln(ceil(d) + " " + floor(d));
			}
		}
	}

	/**
	 * Determine if one string is a substring of another.<p>
	 * Input: two space-delimited strings<p>
	 * Output: true if the first string contains the second string, false otherwise<p>
	 * Language features: String.contains
	 * UVa problems: 403, 1061, 11340
	 */
	public void stringContains() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			ru.writeln(tokens[0].contains(tokens[1]));
		}
	}

	/**
	 * Print a trimmed string.<p>
	 * Input: a line of texst<p>
	 * Output: the line with leading and trailing spaces removed<p>
	 * Language features: String.trim
	 */
	public void stringTrim() {
		for (String line : ru.getTestInput()) {
			ru.writeln(line.trim());
		}
	}

	/**
	 * Change the delimiters in a string (pipe to dash).<p>
	 * Input: a line of pipe-delimited strings<p>
	 * Output: the same strings, delimited by dashes, with a trailing dash<p>
	 * Language features: String.split with escaped regex
	 * UVa problem: 403
	 */
	public void splitStringPipe() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.split("\\|");	// split accepts a regex, so | has to be escaped
			for (String s : tokens) ru.write(s + "-");
		}
		ru.writeln();
	}

	/**
	 * Convert characters to their ASCII values.<p>
	 * Input: a line of text<p>
	 * Output: space-delimited ASCII values of the line's characters<p>
	 * Language features: cast char to int
	 * UVa problem: 146
	 */
	public void getASCII() {
		for (String line : ru.getTestInput()) {
			for (int j=0; j<line.length(); j++) ru.write(((int)line.charAt(j)) + " ");
			ru.writeln();
		}
	}

	/**
	 * Convert numeric ASCII values to characters.<p>
	 * Input: a space-delimited list of integers<p>
	 * Output: a string composed of the characters represented by those integers<p>
	 * Language features: cast int to char
	 * UVa problems: 146, 195, 608
	 */
	public void ASCIItoChar() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) {
				int c = Integer.parseInt(s);
				ru.write((char)c);
			}
			ru.writeln();
		}
	}

	/**
	 * Convert the characters in a string to ordinal numbers (a=0, b=1, ...).<p>
	 * Input: a line of text<p>
	 * Output: space-delimited integers representing the characters in the line<p>
	 * Language features: char arithmetic
	 * UVa problems: 156, 403, 489, 608
	 */
	public void charToOrdinal() {
		for (String line : ru.getTestInput()) {
			for (int j=0; j<line.length(); j++) ru.write((line.charAt(j) - 'a') + " ");
			ru.writeln();
		}
	}

	/**
	 * Convert ordinal numbers (a=0, b=1, ...) to characters.<p>
	 * Input: space-delimited integers representing characters (a=0, b=1, ...)<p>
	 * Output: a line of text<p>
	 * Language features: char arithmetic
	 * UVa problem: 195
	 */
	public void ordinalToChar() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) {
				int c = Integer.parseInt(s);
				ru.write((char)(c + 'a'));
			}
			ru.writeln();
		}
	}

	/**
	 * Clone and reference an array.<p>
	 *
	 * Read whitespace-delimited integers into an array; print them out; create a
	 * reference to the array; modify the first element; print the original array;
	 * create a copy of the array; modify the first element; print the original array.
	 *
	 * Input: a space-delimited list of integers<p>
	 * Output: see above<p>
	 * Language features: Array.clone
	 * UVa problem: 1061
	 */
	public void cloneArray() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			int[] nums = new int[tokens.length];
			int i = 0;
			for (String str : tokens) nums[i++] = Integer.parseInt(str);
			for (int n : nums) ru.write(n + " ");
			ru.writeln();
			int[] nums2 = nums;	// this is a reference to nums, so changes to nums2 will affect nums
			nums2[0] = 99;
			for (int n : nums) ru.write(n + " ");
			ru.writeln();
			int[] nums3 = nums.clone();	// this is a copy of nums, so changes to nums3 will NOT affect nums
			nums3[0] = 0;
			for (int n : nums) ru.write(n + " ");
			ru.writeln();
		}
	}

	/**
	 * Initialize an int array using literals.<p>
	 * Input: none<p>
	 * Output: a space-delimited list of integers<p>
	 * Language features: array literal syntax
	 * UVa problem: 556
	 */
	public void initIntArray() {
		int[] literals = new int[] { 9, 8, 7, 6, 5 };
		for (int n : literals) ru.write(n + " ");
		ru.writeln();
	}

	/**
	 * Look for an element in a list.<p>
	 * Input: space-delimited list of integers<p>
	 * Output: true if list contains 23, false otherwise<p>
	 * Language features: List.contains
	 * UVa problems: 1061
	 */
	public void lookupElement() {
		for (String line : ru.getTestInput()) {
			List<Integer> numsList = new ArrayList<Integer>();
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) numsList.add(Integer.parseInt(s));
			ru.writeln(numsList.contains(23));
		}
	}

	/**
	 * Convert a list to an array.<p>
	 * Input: a space-delimited list of integers<p>
	 * Output: the same space-delimited list of integers<p>
	 * Language features: List.toArray
	 * UVa problems: 1061, 12060
	 */
	public void listToArray() {
		for (String line : ru.getTestInput()) {
			List<Integer> numsList = new ArrayList<Integer>();
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) numsList.add(Integer.parseInt(s));
			Integer[] numsArr = numsList.toArray(new Integer[numsList.size()]);	// have to use Integer, not int
			for (int n : numsArr) ru.write(n + " ");
			ru.writeln();
		}
	}

	/**
	 * Divide floating point numbers and format the result.<p>
	 * Input: two integers, to be interpreted as floats<p>
	 * Output: the first integer divided by the second, formatted to three decimal places<p>
	 * Language features: DecimalFormat
	 * UVa problems: 579, 11340
	 */
	public void formatDecimal() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			double n1 = Double.parseDouble(tokens[0]);
			double n2 = Double.parseDouble(tokens[1]);
			DecimalFormat df = new DecimalFormat("0.000");
			ru.writeln(df.format(n1/n2));
		}
	}

	/**
	 * Add an increment value (in days) to a date.<p>
	 * Input: space-delimited month, day, year, and increment (in days)<p>
	 * Output: the input date plus the increment, in mm/dd/yyyy format, zero-padded
	 * Language features: GregorianCalendar<p>
	 * UVa problems: 11947, 893
	 */
	public void incrementDate() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
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

			ru.writeln(sm + "/" + sd + "/" + sy);
		}
	}

	/**
	 * Demonstrate the difference between int and Boolean array initialization.<p>
	 * Input: none<p>
	 * Output: element 0 of an int array and a Boolean array, and the value of a Boolean variable<p>
	 * Language features: Boolean array<p>
	 */
	public void arrayInit() {
		int[] a = new int[1];	// gets initialized to {0} automatically
		Boolean[] b = new Boolean[1];	// this is {null}; no automatic initialization to false
		Boolean c = true;	// have to initialize before printing; otherwise, error: variable c might not have been initialized
		ru.writeln(a[0]);	// 0
		ru.writeln(b[0]);	// null
		ru.writeln(c);		// true
	}

	/**
	 * Use break to exit the inner loop in a set of two nested loops.<p>
	 * Input: a 5x5 grid of integers<p>
	 * Output: the bottom left section of the grid<p>
	 * Language features: nested loops, break<p>
	 * UVa problem: 10855
	 */
	public void loopBreak() {
		ArrayList<String> lines = ru.getTestInput();
		int s = lines.size();
		int[][] sq = new int[s][s];
		for (int i=0; i<s; i++) {
			String[] tokens = lines.get(i).trim().split("\\s+");
			for (int j=0; j<tokens.length; j++) {
				sq[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		for (int i=0; i<s; i++) {
			for (int j=0; j<s; j++) {
				if (sq[i][j] % 6 == 0) break;
				ru.write(String.format("%2d ", sq[i][j]));
			}
			ru.writeln();
		}
	}

	/**
	 * Sort a list of integers in ascending and descending order.<p>
	 * Input: a list of integers<p>
	 * Output: the list in ascending order; the list in descending order<p>
	 * Language features: PriorityQueue<p>
	 * UVa problem: 10107
	 */
	public void sortAscendingDescending() {
		for (String line : ru.getTestInput()) {
			SortDescending sortd = new SortDescending();
			PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>(100);
			PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(100, sortd);
			String[] tokens = line.trim().split("\\s+");
			for (int j=0; j<tokens.length; j++) {
				int i = Integer.parseInt(tokens[j]);
				pq1.add(i);
				pq2.add(i);
			}
			for (int j=0; j<tokens.length; j++) {
				ru.write(pq1.poll() + " ");
			}
			ru.writeln();
			for (int j=0; j<tokens.length; j++) {
				ru.write(pq2.poll() + " ");
			}
			ru.writeln();
		}
	}

	// TODO: reverse order priority queue (UVa 11995)

	/**
	 * Sort a collection of custom objects.<p>
	 * Input: a list of planet names and density values<p>
	 * Output: the list in descending order of density<p>
	 * Language features: Collections.sort<p>
	 * UVa problem: 10258
	 */
	public void sortObjects() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			List<Planet> planets = new ArrayList<Planet>();
			for (int i=0; i<tokens.length; i+=2) {
				Planet p = new Planet();
				p.Name = tokens[i];
				p.Density = Double.parseDouble(tokens[i+1]);
				planets.add(p);
			}
			Collections.sort(planets);
			for (int i=0; i<planets.size(); i++) {
				Planet p = planets.get(i);
				ru.writeln(p.Name + " has an average density of " + p.Density + " g/cm^3");
			}
		}
	}


	/**
	 * Use bitwise operators to manipulate individual bits in an integer.<p>
	 *
	 * Starting with 00000000, set each bit from right to left,
	 * and print the results as integers. Then unset each bit from
	 * left to right, and print the results again. Then toggle each
	 * bit from right to left and print the results.
	 *
	 * Input: none<p>
	 * Output: integer value as each bit is flipped<p>
	 * Language features: bitwise operations<p>
	 * UVa problem: 10264
	 */
	public void flipBits() {
		int num = 0;
		for (int i=0; i<8; i++) {
			int bits = 1 << i;
			num |= bits;	// set
			ru.write(num + " ");
		}
		ru.writeln();
		for (int i=7; i>=0; i--) {
			int bits = ~(1 << i);
			num &= bits;	// unset
			ru.write(num + " ");
		}
		ru.writeln();
		for (int i=0; i<8; i++) {
			int bits = 1 << i;
			num ^= bits;	// toggle
			ru.write(num + " ");
		}
		ru.writeln();
	}

	/**
	 * Raise integer bases to integer powers.<p>
	 * Input: pairs of integers (base <space> exponent)<p>
	 * Output: space-delimited integer results<p>
	 * Language features: Math.pow<p>
	 * UVa problem: 10264
	 */
	public void exponentiateIntegers() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			for (int i=0; i<tokens.length; i+=2) {
				int base = Integer.parseInt(tokens[i]);
				int exp = Integer.parseInt(tokens[i+1]);
				ru.write((int)pow(base, exp));
				ru.write(" ");
			}
			ru.writeln();
		}
	}

	/**
	 * Determine if two intervals on the integer number line overlap.<p>
	 * Input: two pairs of space-delimited integers representing endpoints of integer intervals<p>
	 * Output: for each pair of intervals, true if they overlap and false if they don't<p>
	 * Language features: BitSet<p>
	 * UVa problem: 11926
	 */
	public void checkIntervalOverlap() {
		ArrayList<String> lines = ru.getTestInput();
		for (int i=0; i<lines.size(); i++) {
			String[] tokens = lines.get(i).trim().split("\\s+");
			BitSet b1 = new BitSet();
			b1.set(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			BitSet b2 = new BitSet();
			b2.set(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
			ru.writeln(b1.intersects(b2));
		}
	}

	/**
	 * Convert between decimal and binary representation.<p>
	 * Input: two lines, an integer represented as base-10 and an integer represented as 8-bit
	 * binary in little endian bit ordering (LSB on the right, as normally written for humans)<p>
	 * Output: two lines, with the first as binary (little endian) and the second as decimal<p>
	 * Language features: BitSet<p>
	 * UVa problem: 11933
	 */
	public void convertNumberBase() {
		ArrayList<String> lines = ru.getTestInput();
		for (int i=0; i<lines.size(); i+=2) {
			int n = Integer.parseInt(lines.get(i));
			BitSet bs = BitSet.valueOf(new long[] {n});
			for (int j=7; j>=0; j--)
				if (bs.get(j)) ru.write("1"); else ru.write("0");
			ru.writeln();
			bs = new BitSet();
			String line = lines.get(i+1);
			for (int j=0; j<line.length(); j++)
				if (line.charAt(j) == '1') bs.set(7-j); else bs.clear(7-j);
			ru.writeln(bs.toLongArray()[0]);
		}
	}

	/**
	 * Create and traverse linked lists: 
	 * Read the input into two linked lists, adding to the head for the first list
	 * and to the tail for the second list. Iterate through each list separately,
	 * printing only the even values.<p>
	 *
	 * Input: a list of space-delimited integers<p>
	 * Output: two copies of only the even integers from the input, on two separate lines<p>
	 * Language features: LinkedList<p>
	 * UVa problem: 11988
	 */
	public void processLinkedLists() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			LinkedList<Integer> lst = new LinkedList<Integer>();
			LinkedList<Integer> lst2 = new LinkedList<Integer>();
			for (int i=0; i<tokens.length; i++) {
				lst.addFirst(Integer.parseInt(tokens[i]));
				lst2.add(Integer.parseInt(tokens[i]));
			}
			ListIterator<Integer> listIterator = lst.listIterator();
			ListIterator<Integer> listIterator2 = lst2.listIterator();
			while (listIterator.hasNext()) {
			    int j = listIterator.next();
			    if (j % 2 == 0) ru.write(j + " ");
			}
			ru.writeln();
			while (listIterator2.hasNext()) {
			    int j = listIterator2.next();
			    if (j % 2 == 0) ru.write(j + " ");
			}
			ru.writeln();
		}
	}

	/**
	 * Reverse a string using a stack.<p>
	 * Input: a string<p>
	 * Output: the same string in reverse<p>
	 * Language features: Stack<p>
	 * UVa problem: 514
	 */
	public void reverseString() {
		Stack<Character> s = new Stack<Character>();
		for (String line : ru.getTestInput()) {
			for (int i=0; i<line.length(); i++) {
				s.push(line.charAt(i));
			}
			while (!s.isEmpty()) ru.write(s.pop());
			ru.writeln();
		}
	}

	/**
	 * Store and then print a string using a queue.<p>
	 * Input: a string<p>
	 * Output: the same string<p>
	 * Language features: Queue<p>
	 * Note: Queue is an interface, so it can't be instantiated.
	 * An implementation like LinkedList needs to be used instead.
	 * UVa problem: 10172
	 */
	public void dontReverseString() {
		Queue<Character> q = new LinkedList<Character>();
		for (String line : ru.getTestInput()) {
			for (int i=0; i<line.length(); i++) {
				q.add(line.charAt(i));
			}
			while (!q.isEmpty()) ru.write(q.poll());
			ru.writeln();
		}
	}

	/**
	 * Count occurrences of integers using a TreeMap.<p>
	 * Input: a list of integers<p>
	 * Output: each integer, and the number of times it occurs, in
	 * descending order by integer<p>
	 * Language features: TreeMap<p>
	 * UVa problem: 978
	 */
	public void countIntegers() {
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		ArrayList<String> lines = ru.getTestInput();
		for (int i=0; i<lines.size(); i++) {
			String[] tokens = lines.get(i).trim().split("\\s+");
			for (String s : tokens) {
				Integer n = Integer.parseInt(s);
				Integer count = tm.get(n);
				if (count == null) count = 1; else count++;
				tm.put(n, count);
			}
			Iterator iter = tm.descendingKeySet().iterator();
			while (iter.hasNext()) {
				Integer n = (Integer)iter.next();
				Integer count = tm.get(n);
				ru.writeln(n + " " + count);
			}
		}
	}

	/**
	 * Count occurrences of integers using a TreeSet.<p>
	 * Input: a list of integers<p>
	 * Output: each integer, and the number of times it occurs, in
	 * descending order by integer<p>
	 * Language features: TreeSet<p>
	 * UVa problem: 978
	 */
	public void countIntegers2() {
		TreeSet<Pair> ts = new TreeSet<>();
		ArrayList<String> lines = ru.getTestInput();
		for (int i=0; i<lines.size(); i++) {

			String[] tokens = lines.get(i).trim().split("\\s+");
			for (String s : tokens) {
				Integer n = Integer.parseInt(s);
				Pair p = new Pair(n, 1);
				Pair count = ts.ceiling(p);
				if (count == null) ts.add(p); else {
					// ts.remove is required because Set.add() doesn't replace an existing entry
					// http://stackoverflow.com/questions/2978087/java-why-does-map-put-overwrite-while-set-add-does-not
					ts.remove(p);
					ts.add(new Pair(n, count.y+1));
				}
			}
			Iterator iter = ts.descendingIterator();
			while (iter.hasNext()) {
				Pair p = (Pair)iter.next();
				ru.writeln(p.x + " " + p.y);
			}
		}
	}

	// TODO:
	// Copying collecdtions like stacks and queues, including @SuppressWarnings("unchecked")
	// Using LinkedList as a queue implementation -- is there a reason to use Queue and not just use LinkedList?
	// Converting long to int (and Long to Integer)

	/**
	 * Describe the purpose of this test.<p>
	 * Input: <p>
	 * Output: <p>
	 * Language features: <p>
	 * UVa problem: 
	 */
	public void sampleTest() {
		for (String line : ru.getTestInput()) {
			String[] tokens = line.trim().split("\\s+");
			for (String s : tokens) ru.write(s);
		}
	}

	// End of test section

	ReferenceUtil ru;

	// main method must match this signature exactly
	public static void main(String[] args) {
		Reference r = new Reference();
		r.ru = new ReferenceUtil();

		r.runAllTests();
		//r.runOneTest();

		r.ru.flushOutput();
	}


	// Classes for use in some of the tests

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

	private class Pair implements Comparable<Pair> {
		public Pair(int x, int y) { this.x = x; this.y = y; }
		public int x;
		public int y;
		
		public int compareTo(Pair p) {
			int c = x - p.x;
			if (c < 0) return -1;
			else if (c > 0) return 1;
			else return 0;
		}
	}

}	// end of public class Reference
