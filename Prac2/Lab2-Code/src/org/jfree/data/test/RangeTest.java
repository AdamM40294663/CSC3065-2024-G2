package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {

	private Range rangeObjectUnderTest1;
	private Range rangeObjectUnderTest2;
	private Range rangeObjectBothNegative1;
	private Range rangeObjectBothNegative2;
	private Range rangeObjectBothNegativeAndEqual;
	private Range rangeObjectBothEqual;

	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest1 = new Range(1.0, 5.0);
		rangeObjectUnderTest2 = new Range(6.0, 10.0);
		rangeObjectBothNegative1 = new Range(-5.0, -1.0);
		rangeObjectBothNegative2 = new Range(-10.0, -6.0);
		rangeObjectBothNegativeAndEqual = new Range(-4.0, -4.0);
		rangeObjectBothEqual = new Range(4.0, 4.0);
	}

	@After
	public void tearDown() throws Exception {
		rangeObjectUnderTest1 = null;
		rangeObjectUnderTest2 = null;
		rangeObjectBothNegative1 = null;
		rangeObjectBothNegative2 = null;
		rangeObjectBothNegativeAndEqual = null;
		rangeObjectBothEqual = null;
		
	}

	// ----------Intersects Method Tests----------

	// Testing intersecting range and input values
	@Test
	public void testIntersectsIntersectingData() {
		assertEquals("Intersects Method Failed: Data and Range values input intersect", true,
				rangeObjectUnderTest1.intersects(2.0, 4.0));
	}

	// Testing not intersecting range and input values
	@Test
	public void testIntersectsNotIntersectingData() {
		assertEquals("Intersects Method Failed: Data and Range values input do not intersect", false,
				rangeObjectUnderTest1.intersects(6.0, 8.0));
	}
	
	//Testing intersecting with equal range
	@Test
	public void testIntersectsIntersectingDataEqualRange() {
		assertEquals("Intersecting Method Failed: Data intersects with equal Range", true, rangeObjectBothEqual.intersects(1.0, 5.0));
	}
	
	//Testing not intersecting with equal range
	@Test
	public void testIntersectsNotIntersectingDataEqualRange() {
		assertEquals("Intersecting Method Failed: Data doesn't intersect with equal Range", false, rangeObjectBothEqual.intersects(6.0, 8.0));
	}
	
	//Testing negative range and negative intersecting values
	@Test
	public void testIntersectsIntersectingNegativeData() {
		assertEquals("Intersects Method Failed: Data and Range values input intersect", true,
				rangeObjectBothNegative1.intersects(-4.0, -1.0));
	}
	
	//Testing negative range and not intersecting values
	@Test
	public void testIntersectsNotIntersectingNegativeData() {
		assertEquals("Intersects Method Failed: Data and Range values input do not intersect", false,
				rangeObjectBothNegative1.intersects(-8.0, -6.0));
	}
	
	//Testing intersecting data with negative and equal range 
	@Test
	public void testIntersectsIntersectingDataNegativeEqualRange() {
		assertEquals("Intersecting Method Failed: Data intersects with equal Range", true, rangeObjectBothNegativeAndEqual.intersects(-5.0, -1.0));
	}
	
	//Testing not intersecting data with negative and equal range
	@Test
	public void testIntersectsNotIntersectingDataNegativeEqualRange() {
		assertEquals("Intersects Method Failed: Data does not intersect with range", false, rangeObjectBothNegativeAndEqual.intersects(-10.0, -6.0));
	}

	// Testing upper and lower value equal and intersects range
	@Test
	public void testIntersectsEqualBoundsIntersectingRange() {
		assertEquals("Intersects Method Failed: Equal Range does intersect with rangeObjectUnderTest1 (1.0-5.0) ", true,
				rangeObjectUnderTest1.intersects(4.0, 4.0));
	}
	
	//Testing upper and lower value equal and not intersecting 
	@Test
	public void testIntersectsEqualBoundsNotIntersectingRange() {
		assertEquals("Intersects Method Failed: Equal Range does interect with rangeObjectUnderTest1 (1.0-5.0) ", false,
				rangeObjectUnderTest1.intersects(8.0, 8.0));
	}
	
	//Testing upper and lower values equal and intersecting negative
	@Test
	public void testIntersectsEqualBoundsIntersectingNegativeRange() {
		assertEquals("Intersects Method Failed: Equal Range intersects with rangeObjectBothNegative1 (-5.0, -1.0) ", true,
				rangeObjectBothNegative1.intersects(-4.0, -4.0));
	}
	
	//Testing upper and lower values equal and not intersecting negative
	@Test
	public void testIntersectsEqualBoundsNotIntersectingNegativeRange() {
		assertEquals("Intersects Method Failed: Equal Range intersects with rangeObjectBothNegative1 (-5.0, -1.0) ", false,
				rangeObjectBothNegative1.intersects(-8.0, -8.0));
	}
	
	
	  
	// ----------Testing Combine Method-----------

	// Testing not null ranges
	@Test
	public void testCombineValidRanges() {
		try {
			Range combinedRange = Range.combine(rangeObjectUnderTest1, rangeObjectUnderTest2);

			assertEquals("Combine method Failed: Lower Range is incorrect", 1.0, combinedRange.getLowerBound(),
					0.000000001d);
			assertEquals("Combine method Failed: Upper Range is incorrect", 10.0, combinedRange.getUpperBound(),
					0.000000001d);
		} catch (Exception e) {
			fail("Combine method failed to create new Range");
		}
	}

	//Testing not null ranges negative
	@Test
	public void testCombineValidNegativeRanges() {
		try {
			Range combinedRange = Range.combine(rangeObjectBothNegative1, rangeObjectBothNegative2);
			Range expectedRange = new Range(-10.0, -1.0);
			
			assertEquals("Combine method failed: Ranges did not combine correctly", expectedRange, combinedRange);
		} catch (Exception e) {
			fail("Combine method failed to create new Range");
		}
	}
	
	
	// Testing Range1 is null
	@Test
	public void testCombineRange1Null() {
		try {
			Range combinedRange = Range.combine(null, rangeObjectUnderTest2);

			assertEquals("Combine method failed: Should return range 6.0-10.0", rangeObjectUnderTest2, combinedRange);
		} catch (Exception e) {
			fail("Combine method failed to create new Range");
		}
	}

	// Testing Range2 is null
	@Test
	public void testCombineRange2Null() {
		try {
			Range combinedRange = Range.combine(rangeObjectUnderTest1, null);

			assertEquals("Combine method failed: Should return range 1.0-5.0", rangeObjectUnderTest1, combinedRange);
		} catch (Exception e) {
			fail("Combine method failed to create new Range");
		}
	}

	// Testing Both Null
	@Test
	public void testCombineBothRangesNull() {
		try {
			Range combinedRange = Range.combine(null, null);

			assertEquals("Combine method failed: Should return null", null, combinedRange);
		} catch (Exception e) {
			fail("Combine method failed to create new Range");
		}
	}
	

	// ----------Testing Constrain Method----------

	// Testing Constrain with values inside rangeObjectUnderTest1
	@Test
	public void testConstrainInsideRange() {
		assertEquals("Constrain Method Failed: Should return 4.0", 4.0, rangeObjectUnderTest1.constrain(4.0),
				0.000000001d);
	}

	// Testing Constrain with values below rangeObjectUnderTest1
	@Test
	public void testConstrainOutsideRangeBelow() {
		assertEquals("Constrain Method Failed: Should return 1.0", 1.0, rangeObjectUnderTest1.constrain(0.0),
				0.000000001d);
	}

	// Testing Constrain with values above rangeObjectUnderTest1
	@Test
	public void testConstrainOutsideRangeAbove() {
		assertEquals("Constrain Method Failed: Should return 5.0", 5.0, rangeObjectUnderTest1.constrain(6.0),
				0.000000001d);
	}
	
	//Testing Constrain with values inside rangeObjectBothNegative1
	@Test
	public void testConstrainInsideNegativeRange() {
		assertEquals("Constrain Method Failed: Should return -4.0", -4.0, rangeObjectBothNegative1.constrain(-4.0),
				0.000000001d);
	}
	
	//Testing Constrain with values below rangeObjectBothNegative1
	public void testConstrainBelowNegativeRange() {
		assertEquals("Constrain Method Failed: Should return -1.0", -1.0, rangeObjectBothNegative1.constrain(0.0),
				0.000000001d);
	}
	
	//Testing Constrain with values above rangeObjectBothNegative1
	public void testConstrainAboveNegativeRange() {
		assertEquals("Constrain Method Failed: Should return -5.0", -5.0, rangeObjectBothNegative1.constrain(-6.0),
				0.000000001d);
	}
	
	//Testing Constrain with values inside Equal range
	public void testConstrainInsideEqualRange() {
		assertEquals("Constrain Method Failed: Should return 4.0", 4.0, rangeObjectBothEqual.constrain(4.0),
				0.000000001d);
	}
	
	//Testing Constrain with values above equal range
	public void testConstrainAboveEqualRange() {
		assertEquals("Constrain Method Failed: Should return 4.0", 4.0, rangeObjectBothEqual.constrain(6.0),
				0.000000001d);
	}
	
	//Testing Constrain with values below equal range
	public void testConstrainBelowEqualRange() {
		assertEquals("Constrain Method Failed: Should return 4.0", 4.0, rangeObjectBothEqual.constrain(3.0),
				0.000000001d);
	}
	

	
	// ----------Testing Expand To Include Method----------

	// Testing a valid range and including value above upper bound
	@Test
	public void testExpandToIncludeAboveRangeValue() {
		Range expanded = new Range(6.0, 12.0);

		assertEquals("ExpandToInclude Method Failed: Should return the expanded range 6.0-12.0", expanded,
				Range.expandToInclude(rangeObjectUnderTest2, 12.0));
	}

	// Testing a valid range and including a value below lower bound
	@Test
	public void testExpandToIncludeBelowRangeValue() {
		Range expanded = new Range(4.0, 10.0);

		assertEquals("ExpandToInclude Method Failed: Should return the expanded range 4.0-10.0", expanded,
				Range.expandToInclude(rangeObjectUnderTest2, 4.0));
	}

	// Testing a valid range and including a value already within the range
	@Test
	public void testExpandToIncludeInsideRangeValue() {
		assertEquals("ExpandToInclude Method Failed: Should return rangeObjectUnderTest2 range (6.0 - 10.0)",
				rangeObjectUnderTest2, Range.expandToInclude(rangeObjectUnderTest2, 8.0));
	}

	// Testing a null range and including a value
	@Test
	public void testExpandToIncludeNullRange() {
		Range expectedRange = new Range(5.0, 5.0);
		
		assertEquals("ExpandToInclude Method Failed: Should return rangeObjectUnderTest1 range (1.0 - 5.0)",
				expectedRange, Range.expandToInclude(null, 5.0));
	}
	
	//Testing a negative range and including a value below that range
	@Test
	public void testExpandToIncludeBelowNegativeRange() {
		Range expanded = new Range(-12.0, -6.0);

		assertEquals("ExpandToInclude Method Failed: Should return the expanded range -12.0, -6.0", expanded,
				Range.expandToInclude(rangeObjectBothNegative2, -12.0));
	}
	
	//Testing a negative range and including a value above that range
		@Test
		public void testExpandToIncludeAboveNegativeRange() {
			Range expanded = new Range(-10.0, -4.0);

			assertEquals("ExpandToInclude Method Failed: Should return the expanded range -10.0, -4.0", expanded,
					Range.expandToInclude(rangeObjectBothNegative2, -4.0));
		}
	
	
	//Testing a negative range and including a value inside that range
	@Test
	public void testExpandToInlcudeInsideNegativeRange() {
		assertEquals("ExpandToInclude Method Failed: Should return rangeObjectBothNegative2 range (-10.0, -6.0)",
				rangeObjectBothNegative2, Range.expandToInclude(rangeObjectBothNegative2, -8.0));
	}
	
	
	
	
	//----------Testing Contains Method----------
	
	//Testing Valid range and value inside range
	@Test
	public void testContainsValueInsideRange() {
		assertEquals("Contains Method Failed: Value within range should return true", true, rangeObjectUnderTest1.contains(3.0));
	}
	
	//Testing Valid range and value outside range
	@Test
	public void testContainsValueOutsideRange() {
		assertEquals("Contains Method Failed: Value outside range should return false", false, rangeObjectUnderTest1.contains(7.0));
	}
	
	//Testing equal range and inside range
	@Test 
	public void testContainsValueWithinEqualRange() {
		assertEquals("Contains Method Failed: Value within Equal range", true, rangeObjectBothEqual.contains(4.0));
	}
	
	//Testing equal range and outside range 
	@Test
	public void testContainsValueOutsideEqualRange() {
		assertEquals("Contains Method Failed: Value within range should return true", true, rangeObjectBothEqual.contains(8.0));
	}
	
	//Testing Valid negative range and value inside range
	@Test
	public void testContainsValueWithinNegativeRange() {
		assertEquals("Contains Method Failed: Value within range should return true", true, rangeObjectBothNegative1.contains(-3.0));
	}
	
	//Testing valid negative range and value outside range
	@Test
	public void testContainsValueOutsideNegativeRange() {
		assertEquals("Contains Method Failed: Value outside range should return false", false, rangeObjectBothNegative1.contains(-7.0));
	}

	
	

}
