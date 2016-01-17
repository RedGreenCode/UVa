// For more information on this performance test, see
// http://www.redgreencode.com/why-is-java-io-slow
//
// WriteTest4 is based on a discussion in the comments.
import java.io.*;
import java.util.*;

public class WriteTest4
{
	static final Random _random = new Random(211166910);
	static final int numLines = 1000000;
	static final int numChars = 100;
	static StringBuilder sb;
	static PrintWriter stdout;
	static long total = 0;

	private static void PrintLines() {
		stdout = new PrintWriter(new BufferedOutputStream(System.out));
		sb = new StringBuilder(25000);
		for (int i=0; i<numLines; i++) {
			for (int j=0; j<numChars-2; j++) {
				int ch = _random.nextInt(95)+32;
				sb.append((char)ch);
				total += ch;
			}
			sb.append(System.getProperty("line.separator"));
			FlushSb();
		}
		stdout.write(sb.toString());
		stdout.flush();
		stdout.close();
	}

	private static void FlushSb() {
		if (sb.length() >= 20000) {
			stdout.write(sb.toString());
			sb = new StringBuilder(25000);
		}
	}

	public static void main(String args[])
	{
		long startTime = System.nanoTime();
		PrintLines();
		long stopTime = System.nanoTime();
		System.err.println(stopTime - startTime);
		System.err.println("t" + total);
	}
}
