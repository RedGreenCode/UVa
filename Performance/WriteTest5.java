// For more information on this performance test, see
// http://www.redgreencode.com/why-is-java-io-slow
import java.io.*;
import java.util.*;
import java.nio.channels.*;

public class WriteTest5
{
	static final Random _random = new Random(211166910);
	static final int numLines = 1000000;
	static final int numChars = 100;
	static StringBuilder sb;
	static BufferedWriter stdout;
	static long total = 0;

	private static void PrintLines() {
		try {
			FileOutputStream fos = new FileOutputStream(FileDescriptor.out);
			FileChannel outFileChannel = fos.getChannel();
			stdout = new BufferedWriter(Channels.newWriter(outFileChannel, "UTF-8"));

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
		} catch (IOException ioe) { System.err.println("Error"); }
	}
	
	private static void FlushSb() {
		try {
			if (sb.length() >= 20000) {
				stdout.write(sb.toString());
				sb = new StringBuilder(25000);
			}
		} catch (IOException ioe) { System.err.println("Error"); }
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
