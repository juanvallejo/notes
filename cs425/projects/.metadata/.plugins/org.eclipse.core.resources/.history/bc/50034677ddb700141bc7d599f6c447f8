package natural;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class NaturalTest {

	@Test
	public void testToString()
	{
		Natural n1 = new Natural(2);
		assertEquals("2", n1.toString());
	}

	@Test(expected=Exception.class)
	public void testExceptionOnNegative()
	{
		new Natural(-1);
	}	

	@Test
	public void sortByDefaultComparable()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(0));
		Collections.sort(list);
		assertEquals(new Natural(0), list.get(0));
		assertEquals(new Natural(5), list.get(1));
		assertEquals(new Natural(7), list.get(2));
		assertEquals(new Natural(9), list.get(3));
		assertEquals(new Natural(13), list.get(4));
	}

	@Test
	public void sortByNumericalComparator()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(0));
		Collections.sort(list, Natural.comparatorByNumber());
		assertEquals(new Natural(0), list.get(0));
		assertEquals(new Natural(5), list.get(1));
		assertEquals(new Natural(7), list.get(2));
		assertEquals(new Natural(9), list.get(3));
		assertEquals(new Natural(13), list.get(4));
	}

	@Test
	public void sortByStringComparator()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(0));
		Collections.sort(list, Natural.comparatorByAlpha());
		assertEquals(new Natural(0), list.get(0));
		assertEquals(new Natural(13), list.get(1));
		assertEquals(new Natural(5), list.get(2));
		assertEquals(new Natural(7), list.get(3));
		assertEquals(new Natural(9), list.get(4));
	}
}
