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
	public void processParamsTest() {
		m.processParams();
		assertEquals(5, m.b);
		assertEquals(4, m.sgreen);
		assertEquals(4, m.sblue);
	}

	@Test
	public void putPowerTest() {
		TreeMap<Integer, Integer> army = new TreeMap<>();
		m.putPower(army, "10");
		Map.Entry<Integer,Integer> entry = army.lastEntry();
		assertEquals(10, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());
		m.putPower(army, "10");
		entry = army.lastEntry();
		assertEquals(10, (int)entry.getKey());
		assertEquals(2, (int)entry.getValue());
		m.putPower(army, "20");
		entry = army.lastEntry();
		assertEquals(20, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());
	}

	@Test
	public void buildArmiesTest() {
		m.processParams();
		m.buildArmies();
		Map.Entry<Integer,Integer> entry = m.gArmy.lastEntry();
		assertEquals(20, (int)entry.getKey());
		assertEquals(2, (int)entry.getValue());
		entry = m.bArmy.lastEntry();
		assertEquals(30, (int)entry.getKey());
		assertEquals(3, (int)entry.getValue());
	}

	@Test
	public void getNextLemmingTest() {
		TreeMap<Integer, Integer> army = new TreeMap<>();
		m.putPower(army, "20");
		m.putPower(army, "20");
		m.putPower(army, "10");
		Map.Entry<Integer,Integer> entry = army.lastEntry();
		assertEquals(20, (int)entry.getKey());
		assertEquals(2, (int)entry.getValue());
		int n = m.getNextLemming(army);
		assertEquals(20, n);
		entry = army.lastEntry();
		assertEquals(20, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());
	}

	@Test
	public void assignBattlefieldsTest() {
		m.processParams();
		m.buildArmies();
		m.assignBattlefields();
		assertEquals((Integer)20, (Integer)m.bfg[0]);
		assertEquals((Integer)30, (Integer)m.bfb[0]);
		assertEquals((Integer)20, (Integer)m.bfg[1]);
		assertEquals((Integer)30, (Integer)m.bfb[1]);
	}

	@Test
	public void fightTest() {
		m.processParams();
		m.buildArmies();
		m.assignBattlefields();
		m.fight();
		assertEquals((Integer)0, (Integer)m.bfg[0]);
		assertEquals((Integer)10, (Integer)m.bfb[0]);
		assertEquals((Integer)0, (Integer)m.bfg[1]);
		assertEquals((Integer)10, (Integer)m.bfb[1]);
	}

	@Test
	public void sendBackToArmiesTest() {
		m.processParams();
		m.buildArmies();
		m.assignBattlefields();
		m.fight();
		m.sendBackToArmies();
		Map.Entry<Integer,Integer> entry = m.gArmy.lastEntry();
		assertEquals(null, entry);
		entry = m.bArmy.lastEntry();
		assertEquals(20, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());
	}

	@Test
	public void fullTest() {
		Map.Entry<Integer,Integer> entry;

		m.processParams();
		m.buildArmies();
		m.processParams();
		m.buildArmies();
		entry = m.gArmy.lastEntry();
		assertEquals(50, (int)entry.getKey());
		assertEquals(2, (int)entry.getValue());
		entry = m.bArmy.lastEntry();
		assertEquals(60, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());

		m.assignBattlefields();
		entry = m.gArmy.lastEntry();
		assertEquals(40, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());
		entry = m.bArmy.lastEntry();
		assertEquals(30, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());

		m.fight();
		m.sendBackToArmies();
		entry = m.gArmy.lastEntry();
		assertEquals(40, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());
		entry = m.bArmy.lastEntry();
		assertEquals(30, (int)entry.getKey());
		assertEquals(1, (int)entry.getValue());

		m.assignBattlefields();
		m.fight();
		m.sendBackToArmies();
		entry = m.gArmy.lastEntry();
		assertEquals(10, (int)entry.getKey());
		assertEquals(2, (int)entry.getValue());
	}
}
