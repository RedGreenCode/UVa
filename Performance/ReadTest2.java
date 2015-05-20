// For more information on this performance test, see
// http://www.redgreencode.com/why-is-java-io-slow
import java.io.*;
import java.util.*;

public class ReadTest2
{
	static long total = 0;

	private static void ReadLines() {
		try {
			String s = "";
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			while (stdin.ready()) {
				s = stdin.readLine();
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
