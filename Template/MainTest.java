import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MainTest {
	private Main m;

	@Before
	public void setUp() throws Exception {
		m = new Main();
		m.setupIO(new FileReader("input-test.txt"), new OutputStreamWriter(System.out));
	}

	@Test
	public void getIntsTest() {
		Integer[] n = m.getInts();
		assertEquals(1, (int)n[0]);
		assertEquals(2, (int)n[1]);
	}
}
