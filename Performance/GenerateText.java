import java.io.*;
import java.util.*;

public class GenerateText
{
	static final Random _random = new Random();
	static final int targetSize = 1024*1024*100;
	static int size = 0;

	private static Boolean CheckSize() {
		if (size == targetSize) return true;
		if (size == targetSize-2) {
			System.out.println();
			return true;
		}
		return false;
	}

	public static void main(String args[])
	{
		while (true) {
			int len = _random.nextInt(115)+5;
			for (int i=0; i<len; i++) {
				int ch = _random.nextInt(95)+32;
				System.out.print((char)ch);
				size++;
				if (CheckSize()) return;
			}
			System.out.println();
			size += 2;
			if (CheckSize()) return;
		}
	}
}
