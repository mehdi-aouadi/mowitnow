package com.xebia.mowitnow.io;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.xebia.mowitnow.base.Cell;
import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Position;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class LawnParserTest {

	private LawnParser 	parser;
	private	Lawn 		actualLawn;
	private	Lawn 		expectedLawn;
	private Cell 		expectedCell;
	private Cell 		actualCell;
	private Position 	expectedPosition;
	

	@Test
	@Parameters(value = {"5 5, 6, 6", "1 1, 2, 2", "3 6, 4, 7"})
	public void lawnIsValid(String line, int width, int height) {
		parser = new LawnParser();
	    actualLawn = parser.parse(line);
	    expectedLawn = new Lawn(width, height);
	    checkLawn(expectedLawn, actualLawn);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(value = {"X Y", "5 Y", "X 5", "ABCD", "-1 5", "3 -2", "5 5 5", "5 5 ABC"})
	public void lawIsNotValid(final String line) {
		parser = new LawnParser();
	    parser.parse(line);
	}
	
	private void checkLawn(Lawn expected, Lawn actual) {
		
		assertEquals(expected.getHeight(), 	actual.getHeight());
	    assertEquals(expected.getWidth(), 	actual.getWidth());
	    
		for(int x=0; x<expected.getWidth(); x++) {			
			for(int y=0; y<expected.getHeight(); y++) {				
				expectedCell 		= expected.cellAt(x, y);
				actualCell 			= actual.cellAt(x, y);
				expectedPosition	= new Position(x, y);
				
				assertEquals(actualCell, expectedCell);
				assertEquals(expectedPosition, actualCell.getPosition());
			}			
		}
	}

}
