//package natural;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class UtilityTest {

	@Test
	public void findMinimumByDefaultComparable()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(3));
		Natural found = Utility.findMinimum(list);
		assertEquals(new Natural(3), found);
	}

	@Test
	public void findMaximumByDefaultComparable()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(3));
		Natural found = Utility.findMaximum(list);
		assertEquals(new Natural(13), found);
	}

	@Test
	public void findMinimumByNumericalComparator()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(8));
		Natural found = Utility.findMinimum(list, Natural.comparatorByNumber());
		assertEquals(new Natural(5), found);
	}

	@Test
	public void findMaximumByNumericalComparator()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(8));
		Natural found = Utility.findMaximum(list, Natural.comparatorByNumber());
		assertEquals(new Natural(13), found);
	}

	@Test
	public void findMinimumByAlphaComparator()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(8));
		Natural found = Utility.findMinimum(list, Natural.comparatorByAlpha());
		assertEquals(new Natural(13), found);
	}

	@Test
	public void findMaximumByAlphaComparator()
	{
		ArrayList<Natural> list = new ArrayList<Natural>();
		list.add(new Natural(9));
		list.add(new Natural(7));
		list.add(new Natural(13));
		list.add(new Natural(5));
		list.add(new Natural(8));
		Natural found = Utility.findMaximum(list, Natural.comparatorByAlpha());
		assertEquals(new Natural(9), found);
	}

}
