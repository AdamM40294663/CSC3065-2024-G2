package org.jfree.data;

import static org.junit.Assert.*;


import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
	
	private Values2D values2D;
	private KeyedValues values;
	

	@Before
	public void setUp() throws Exception {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
	    testValues.addValue(1, 0, 0);
	    testValues.addValue(4, 1, 0);
	    testValues.addValue(2, 0, 1);
	    testValues.addValue(3, 1, 1);
	    testValues.addValue(null, 0, 2);
	    testValues.addValue(null, 2, 0);
	    
	    
	    DefaultKeyedValues keyedTestValues = new DefaultKeyedValues();
	    values = keyedTestValues;
	    keyedTestValues.addValue("key1", 5);
	    keyedTestValues.addValue("key2", 9);
	    keyedTestValues.addValue("key3", 2);
	    

	}

	@After
	public void tearDown() throws Exception {
		values2D = null;
		values = null;

	}
	
	
	
 
	//----------calculateColumnTotal Tests----------
	@Test
	public void testCalculateColumnTotalValidData() {
		assertEquals("CalculateColumnTotal Failed: It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}

	@Test
	public void testCalculateColumnTotalNullColumn() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testCalculateColumnTotalInvalidColumn() {
		try {
			//Checks assert output if it receives one for a failure 
			assertEquals("CalculateColumnTotal Failed: It should return 0.0 for invalid column", 0.0, DataUtilities.calculateColumnTotal(values2D, 3), 0.0000001d);
		}
		catch (Exception e) {
			//if exception is triggered this is also a fail
			fail("Exception should not be thrown and 0.0 returned");
		}
	}
	
	
	
	//----------calulateRowTotal Tests----------
	
	@Test
	public void testCalculateRowTotalValidData() {
		assertEquals("calculateRowTotal Failed it should be 3.0", 3.0, DataUtilities.calculateRowTotal(values2D, 0),  0.0000001d);
	}
	
	@Test
	public void testCalculateRowTotalNull() {
		try {
			DataUtilities.calculateRowTotal(null, 0);
			fail("No exception thrown");
		}
		catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
		
	}
	
	@Test
	public void testCalculateRowTotalInvalidRow() {
		try {
			//Checks assert output if it receives one for a failure 
			assertEquals("CalculateRowTotal Failed: It should return 0.0 for invalid row", 0.0, DataUtilities.calculateRowTotal(values2D, 3), 0.0000001d);
		}
		catch (Exception e) {
			//if exception is triggered this is also a fail
			fail("Exception should not be thrown and 0.0 returned");
		}
		
	}
	
	
	//----------createNumberArray Tests----------
	
	@Test
	public void testCreateNumberArrayValidData() {
		double[] validData = {1.0, 2.0, 3.0};
		Number[] expected = new Number[] {1.0, 2.0, 3.0};
		
		assertArrayEquals("createNumberArray failed: Array does not match expected array", expected, DataUtilities.createNumberArray(validData));
	}
	
	@Test
	public void testCreateNumberArrayNull() {
		try {
			DataUtilities.createNumberArray(null);
			fail("No exception thrown");
		}
		catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	
	
	//----------createNumberArray2D Tests----------
	
	@Test
	public void testCreateNumberArray2DValidData() {
		double[][] validData = {{1.0, 2.0}, {3.0, 4.0}};
		Number[][] expected = new Number[2][2];
		expected[0][0] = 1.0;
		expected[0][1] = 2.0;
		expected[1][0] = 3.0;
		expected[1][1] = 4.0;
		
		assertArrayEquals("createNumberArray2D failed: Array does not match expected array", expected, DataUtilities.createNumberArray2D(validData));
	}
	
	@Test
	public void testCreateNumberArray2DNullData() {
		try {
			DataUtilities.createNumberArray2D(null);
			fail("No exception thrown");
		}
		catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	
	//----------getCummulativePercentages Tests----------
	
	@Test
	public void testGetCummulativePercentageValidData() {
		KeyedValues valid = DataUtilities.getCumulativePercentages(values);
		
		//checks all 3 parts of the arithmetic for full coverage
		assertEquals("getCummulativePercentage Failed: Key 1 arithmetic incorrect", 0.3125, valid.getValue("key1"));
		assertEquals("getCummulativePercentage Failed: Key 2 arithmetic incorrect", 0.875, valid.getValue("key2"));
		assertEquals("getCummulativePercentage Failed: Key 3 arithmetic incorrect", 1.0, valid.getValue("key3"));
	}
	
	@Test
	public void testGetCummulativePercentageNullData() {
		try {
			DataUtilities.getCumulativePercentages(null);
			fail("No exception thrown");
		}
		catch (Exception e){
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testGetCummulativePercentagesNullKeyed() {
		DefaultKeyedValues keyedTestValues = new DefaultKeyedValues();
	    values = keyedTestValues;
	    keyedTestValues.addValue("key1", null);
	    keyedTestValues.addValue("key2", 9);
	    keyedTestValues.addValue("key3", 2);
	    
	    assertThrows(IllegalArgumentException.class, () -> {
	        DataUtilities.getCumulativePercentages(values);
	    });
	    
	}
	
	
	

}
