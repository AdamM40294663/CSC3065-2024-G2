package org.jfree.data;

import static org.junit.Assert.*;


import java.security.InvalidParameterException;

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
	private Range rangeObjectPositiveNegative;

	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest1 = new Range(1.0, 5.0);
		rangeObjectUnderTest2 = new Range(6.0, 10.0);
		rangeObjectBothNegative1 = new Range(-5.0, -1.0);
		rangeObjectBothNegative2 = new Range(-10.0, -6.0);
		rangeObjectBothNegativeAndEqual = new Range(-4.0, -4.0);
		rangeObjectBothEqual = new Range(4.0, 4.0);
		rangeObjectPositiveNegative = new Range(-5.0, 5.0);
	}

	@After
	public void tearDown() throws Exception {
		rangeObjectUnderTest1 = null;
		rangeObjectUnderTest2 = null;
		rangeObjectBothNegative1 = null;
		rangeObjectBothNegative2 = null;
		rangeObjectBothNegativeAndEqual = null;
		rangeObjectBothEqual = null;
		rangeObjectPositiveNegative = null;
	}

	// ----------Range creation test----------

	@Test
    public void testConstructorWithInvalidBounds() {
        // Arrange
        double lower = 5.0;
        double upper = 1.0; // lower > upper

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Range(lower, upper);
        });
    }
	
	// ----------equals method tests----------
	
	//testing equal ranges
	@Test
    public void testEqualsSameRange() {
        Range equalRange = new Range(1.0, 5.0);
        
        assertTrue("The same range objects should be equal", rangeObjectUnderTest1.equals(equalRange));
    }

	//testing different ranges
    @Test
    public void testEqualsDifferentRanges() {
        
        assertFalse("Different range objects should not be equal", rangeObjectUnderTest1.equals(rangeObjectUnderTest2));
    }
    
    //Testing equals with a range and an object 
    @Test
    public void testEqualsDifferentTypes() {
        Object obj = new Object();
        
        assertFalse("Objects of different types should not be equal", rangeObjectUnderTest1.equals(obj));
    }

    //testing equals null
    @Test
    public void testEqualsNull() {
        assertFalse("Should return false when comparing to null", rangeObjectUnderTest1.equals(null));
    }
    
    //Testing equals with same lower bound and different upper bound
    @Test
    public void testEqualsSameLowerDifferentUpperBounds() {
        Range equalLowerBoundRange = new Range(1.0, 6.0);
        
        assertTrue("The same range objects should be equal", rangeObjectUnderTest1.equals(equalLowerBoundRange));
    }
	
	
	// ----------hashCode method tests----------
	
	//Testing hashCode with equal ranges
	@Test
    public void testHashCodeEqualRanges() {
        Range equalRange = new Range(1.0, 5.0);
        
        assertEquals("Equal ranges should produce equal hash codes", equalRange.hashCode(), rangeObjectUnderTest1.hashCode());
    }
	
	//testing hashCode with not equal ranges
	@Test
    public void testHashCodeDifferentRanges() {
        assertNotEquals("Different ranges should produce different hash codes", rangeObjectUnderTest1.hashCode(), rangeObjectUnderTest2.hashCode());
    }
	
	
	// ----------getCentralValue Method Tests----------
	
	//getCentralValue with positive Data
	@Test
	public void testGetCentralValuePositive() {
		assertEquals("getCentralValue Method Failed: value should return as 3.0", 3.0, rangeObjectUnderTest1.getCentralValue(), 0.000000001d);
	}
	
	//getCentralValue with negative data
	@Test
	public void testGetCentralValueNegative() {
		assertEquals("getCentralValue Method Failed: value should return as -3.0", -3.0, rangeObjectBothNegative1.getCentralValue(), 0.000000001d);
	}
	
	//getCentralValue with equal ranges 
	@Test
	public void testGetCentralValueEqualData() {
		assertEquals("getCentralValue Method Failed: value should return as 4.0", 4.0, rangeObjectBothEqual.getCentralValue(), 0.000000001d);
	}
	
	
	// ----------Shift Method Tests----------

	// Shift with positive range
	@Test
	public void testShiftPositiveData() {
		Range expectedRange = new Range(3.0, 7.0);
		assertEquals("Shift Method Failed: New range should equal 3.0 - 7.0", expectedRange,
				Range.shift(rangeObjectUnderTest1, 2.0));
	}

	// Shift with negative range
	@Test
	public void testShiftNegativeData() {
		Range expectedRange = new Range(-8.0, -4.0);
		assertEquals("Shift Method Failed: New range should equal 3.0 - 7.0", expectedRange,
				Range.shift(rangeObjectBothNegative2, 2.0));
	}
	
	//Shift with no zero crossing and value of 0
	@Test
	public void testShiftNoZeroCrossingAndZeroRange() {
		Range expectedRange = new Range(2.0, 2.0);
		Range zeroRange = new Range(0.0, 0.0);
		assertEquals("ShiftWithNoZeroCrossing Method failed: Value would equal 2.0", expectedRange, Range.shift(zeroRange, 2.0));
	}
	

	// Shift past zero with allowZeroCrossing true
	@Test
	public void testShiftPastZeroCrossingTrue() {
		Range expectedRange = new Range(-3.0, 1.0);
		assertEquals("Shift Method Failed: New range should pass 0 and equal -3.0 - 1.0", expectedRange,
				Range.shift(rangeObjectBothNegative1, 2.0, true));
	}

	// ----------getLength Method Tests-----------

	// Testing positive data getLength
	@Test
	public void testGetLengthPositiveData() {
		assertEquals("getLength Method Failed: Range should be 4", 4.0, rangeObjectUnderTest1.getLength(),
				0.000000001d);
	}

	// Testing negative data getLength
	@Test
	public void testGetLengthNegativeData() {
		assertEquals("getLength Method Failed: Range should be 4", 4.0, rangeObjectBothNegative1.getLength(),
				0.000000001d);
	}

	// Testing positive and negative data getLength
	@Test
	public void testGetLengthPositiveNegativeData() {
		assertEquals("getLength Method Failed: Range should be 10", 10.0, rangeObjectPositiveNegative.getLength(),
				0.000000001d);
	}

	// Testing equal range getLength
	@Test
	public void testGetLengthEqualData() {
		assertEquals("getLength Method Failed: Range should be 0", 0.0, rangeObjectBothEqual.getLength(), 0.000000001d);
	}

	// ----------Expand Method Tests----------

	// Testing expand with positive range
	@Test
	public void testExpandPositiveData() {
		Range expectedRange = new Range(0.0, 7.0);
		assertEquals("Expand Method Failed: New Range should equal 0.0-7.0 ", expectedRange,
				Range.expand(rangeObjectUnderTest1, 0.25, 0.5));
	}

	// Testing expand with negative range
	@Test
	public void testExpandNegativeData() {
		Range expectedRange = new Range(-7.0, 0.0);
		assertEquals("Expand Method Failed: New Range should equal -7.0 - 0.0", expectedRange,
				Range.expand(rangeObjectBothNegative1, 0.5, 0.25));
	}

	// Testing expand with an equal range
	@Test
	public void testExpandEqualData() {
		Range expectedRange = new Range(3.0, 6.0);
		assertEquals("Expand Method Failed: New Range should equal 3.0 - 6.0", expectedRange,
				Range.expand(rangeObjectBothEqual, 0.25, 0.5));
	}

	// Testing expand with null throws correct exception method
	@Test
	public void testExpandNullData() {
		assertThrows(InvalidParameterException.class, () -> {
			Range.expand(null, 0.25, 0.5);
		});
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

	// Testing intersecting with equal range
	@Test
	public void testIntersectsIntersectingDataEqualRange() {
		assertEquals("Intersecting Method Failed: Data intersects with equal Range", true,
				rangeObjectBothEqual.intersects(1.0, 5.0));
	}

	// Testing not intersecting with equal range
	@Test
	public void testIntersectsNotIntersectingDataEqualRange() {
		assertEquals("Intersecting Method Failed: Data doesn't intersect with equal Range", false,
				rangeObjectBothEqual.intersects(6.0, 8.0));
	}

	// Testing negative range and negative intersecting values
	@Test
	public void testIntersectsIntersectingNegativeData() {
		assertEquals("Intersects Method Failed: Data and Range values input intersect", true,
				rangeObjectBothNegative1.intersects(-4.0, -1.0));
	}

	// Testing negative range and not intersecting values
	@Test
	public void testIntersectsNotIntersectingNegativeData() {
		assertEquals("Intersects Method Failed: Data and Range values input do not intersect", false,
				rangeObjectBothNegative1.intersects(-8.0, -6.0));
	}

	// Testing intersecting data with negative and equal range
	@Test
	public void testIntersectsIntersectingDataNegativeEqualRange() {
		assertEquals("Intersecting Method Failed: Data intersects with equal Range", true,
				rangeObjectBothNegativeAndEqual.intersects(-5.0, -1.0));
	}

	// Testing not intersecting data with negative and equal range
	@Test
	public void testIntersectsNotIntersectingDataNegativeEqualRange() {
		assertEquals("Intersects Method Failed: Data does not intersect with range", false,
				rangeObjectBothNegativeAndEqual.intersects(-10.0, -6.0));
	}

	// Testing upper and lower value equal and intersects range
	@Test
	public void testIntersectsEqualBoundsIntersectingRange() {
		assertEquals("Intersects Method Failed: Equal Range does intersect with rangeObjectUnderTest1 (1.0-5.0) ", true,
				rangeObjectUnderTest1.intersects(4.0, 4.0));
	}

	// Testing upper and lower value equal and not intersecting
	@Test
	public void testIntersectsEqualBoundsNotIntersectingRange() {
		assertEquals("Intersects Method Failed: Equal Range does interect with rangeObjectUnderTest1 (1.0-5.0) ", false,
				rangeObjectUnderTest1.intersects(8.0, 8.0));
	}

	// Testing upper and lower values equal and intersecting negative
	@Test
	public void testIntersectsEqualBoundsIntersectingNegativeRange() {
		assertEquals("Intersects Method Failed: Equal Range intersects with rangeObjectBothNegative1 (-5.0, -1.0) ",
				true, rangeObjectBothNegative1.intersects(-4.0, -4.0));
	}

	// Testing upper and lower values equal and not intersecting negative
	@Test
	public void testIntersectsEqualBoundsNotIntersectingNegativeRange() {
		assertEquals("Intersects Method Failed: Equal Range intersects with rangeObjectBothNegative1 (-5.0, -1.0) ",
				false, rangeObjectBothNegative1.intersects(-8.0, -8.0));
	}
	
	//Testing upper less than lower bound 
	@Test
	public void testsIntersectsUpperLessLowerRange() {
		assertEquals("Intersects Method Failed: Range intersects with rangeObjectUnderTest1 (3.0, 2.0) ",
				false, rangeObjectUnderTest1.intersects(3.0, 2.0));
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

	// Testing not null ranges negative
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

	// Testing Constrain with values inside rangeObjectBothNegative1
	@Test
	public void testConstrainInsideNegativeRange() {
		assertEquals("Constrain Method Failed: Should return -4.0", -4.0, rangeObjectBothNegative1.constrain(-4.0),
				0.000000001d);
	}

	// Testing Constrain with values below rangeObjectBothNegative1
	public void testConstrainBelowNegativeRange() {
		assertEquals("Constrain Method Failed: Should return -1.0", -1.0, rangeObjectBothNegative1.constrain(0.0),
				0.000000001d);
	}

	// Testing Constrain with values above rangeObjectBothNegative1
	public void testConstrainAboveNegativeRange() {
		assertEquals("Constrain Method Failed: Should return -5.0", -5.0, rangeObjectBothNegative1.constrain(-6.0),
				0.000000001d);
	}

	// Testing Constrain with values inside Equal range
	public void testConstrainInsideEqualRange() {
		assertEquals("Constrain Method Failed: Should return 4.0", 4.0, rangeObjectBothEqual.constrain(4.0),
				0.000000001d);
	}

	// Testing Constrain with values above equal range
	public void testConstrainAboveEqualRange() {
		assertEquals("Constrain Method Failed: Should return 4.0", 4.0, rangeObjectBothEqual.constrain(6.0),
				0.000000001d);
	}

	// Testing Constrain with values below equal range
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

	// Testing a negative range and including a value below that range
	@Test
	public void testExpandToIncludeBelowNegativeRange() {
		Range expanded = new Range(-12.0, -6.0);

		assertEquals("ExpandToInclude Method Failed: Should return the expanded range -12.0, -6.0", expanded,
				Range.expandToInclude(rangeObjectBothNegative2, -12.0));
	}

	// Testing a negative range and including a value above that range
	@Test
	public void testExpandToIncludeAboveNegativeRange() {
		Range expanded = new Range(-10.0, -4.0);

		assertEquals("ExpandToInclude Method Failed: Should return the expanded range -10.0, -4.0", expanded,
				Range.expandToInclude(rangeObjectBothNegative2, -4.0));
	}

	// Testing a negative range and including a value inside that range
	@Test
	public void testExpandToInlcudeInsideNegativeRange() {
		assertEquals("ExpandToInclude Method Failed: Should return rangeObjectBothNegative2 range (-10.0, -6.0)",
				rangeObjectBothNegative2, Range.expandToInclude(rangeObjectBothNegative2, -8.0));
	}

	// ----------Testing Contains Method----------

	// Testing Valid range and value inside range
	@Test
	public void testContainsValueInsideRange() {
		assertEquals("Contains Method Failed: Value within range should return true", true,
				rangeObjectUnderTest1.contains(3.0));
	}

	// Testing Valid range and value outside range
	@Test
	public void testContainsValueOutsideRange() {
		assertEquals("Contains Method Failed: Value outside range should return false", false,
				rangeObjectUnderTest1.contains(7.0));
	}

	// Testing equal range and inside range
	@Test
	public void testContainsValueWithinEqualRange() {
		assertEquals("Contains Method Failed: Value within Equal range", true, rangeObjectBothEqual.contains(4.0));
	}

	// Testing equal range and outside range
	@Test
	public void testContainsValueOutsideEqualRange() {
		assertEquals("Contains Method Failed: Value within range should return true", true,
				rangeObjectBothEqual.contains(8.0));
	}

	// Testing Valid negative range and value inside range
	@Test
	public void testContainsValueWithinNegativeRange() {
		assertEquals("Contains Method Failed: Value within range should return true", true,
				rangeObjectBothNegative1.contains(-3.0));
	}

	// Testing valid negative range and value outside range
	@Test
	public void testContainsValueOutsideNegativeRange() {
		assertEquals("Contains Method Failed: Value outside range should return false", false,
				rangeObjectBothNegative1.contains(-7.0));
	}

}
