// For more information on this performance test, see
// http://www.redgreencode.com/why-is-java-io-slow
//
// ReadTest3 is based on a discussion in the comments.
import java.io.*;
import java.util.*;

public class ReadTest3
{
	static long total = 0;

	private static void ReadLines() {
		try {
			String s = "";
			Scanner stdin = new Scanner(new BufferedInputStream(System.in));
			while (stdin.hasNextLine()) {
				s = stdin.nextLine();
				for (int i=0; i<s.length(); i++)
					total += s.charAt(i);
			}
		} catch (Exception e) { System.err.println("Error"); }
	}

	public static void main(String args[])
	{
		long startTime = System.nanoTime();
		ReadLines();
		long stopTime = System.nanoTime();
		System.err.println(stopTime - startTime);
		System.err.println("t" + total);
	}
}
