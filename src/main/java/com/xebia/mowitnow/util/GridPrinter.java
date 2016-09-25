package com.xebia.mowitnow.util;

import java.util.Arrays;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Position;
import com.xebia.mowitnow.mower.Mower;

/**
 * Represents a Grid drawer. It draws the Lawn and its status
 * 
 * @author Mehdi AOUADI
 *
 */
public final class GridPrinter {
	
	private static final String HORIZONTAL_SEPARATOR = "-";
	private static final String VERTICAL_SEPARATOR = "|";
	
	/**
	 * Returns a String that represents the Lawn state
	 * 
	 * @param lawn
	 * @param mowers
	 * @return
	 */
	public static String draw(final Lawn lawn, final Mower... mowers) {
		
		int 			width 				= lawn.getWidth();
		int 			height 				= lawn.getHeight();
		boolean 		containsMowers 		= mowers.length > 0;
		
		//The number of digits of Y in order to add spaces
		int yMaxDigitsNumber = String.valueOf(height-1).length();
		
		//The number of digits of X in order to add print it vertically
		int xMaxDigitsNumber = String.valueOf(width-1).length();
		
		StringBuilder gridToPrint = new StringBuilder();
		gridToPrint.append("\n");
		
		//In order to print the Lawn with the 0:0 at the left down position, we need to start printing from 0:Y the left up.
		for(int y=height-1; y>=0; y--) {
			//Print vertical index
			gridToPrint.append(verticalIndexCell(y, yMaxDigitsNumber));
			for(int x=0; x<width; x++) {
				//Must check that a Mower is present within the cell. Otherwise the Iterables.find below will throw an Exception
				if(lawn.cellAt(x, y).isLocked()) {
					gridToPrint.append(containsMowers?cell(getMowerOrientation(new Position(x, y), mowers)):cell(' '));
				}
				else if (lawn.cellAt(x, y).isMowed()) {
						gridToPrint.append(cell('X'));
				}
				else {
						gridToPrint.append(cell(' '));
				}				
			}			
			gridToPrint.append(addHorizontalSeparator(width, yMaxDigitsNumber));
		}
		
		//Print horizontal indexes
		gridToPrint.append(addHorizontalIndexes(width, xMaxDigitsNumber, yMaxDigitsNumber));
		return gridToPrint.toString();
	}
	
	/**
	 * Returns a formatted Cell from a char
	 * @param item
	 * @return
	 */
	private static String cell(char item) {
		return new StringBuilder().append(" ").append(item).append(" ").append(VERTICAL_SEPARATOR).toString();
	}
	
	/**
	 * Returns a formatted Cell from an vertical index 
	 * @param item
	 * @return
	 */	
	private static String verticalIndexCell(int item, int yDigits) {
		int nbDigits = String.valueOf(item).length();
		return new StringBuilder().append(" ").append(item).append(Strings.repeat(" ", yDigits-nbDigits+1)).append(VERTICAL_SEPARATOR).toString();
	}
	
	/**
	 * Adds the horizonal indexes of the lawn
	 * @param width
	 * @return
	 */	
	private static String addHorizontalIndexes(int width,int xMaxDigitsNumber, int yMaxDigitsNumber) {		
		
		StringBuilder horizontalIndexes= new StringBuilder();
		
		for(int lineNumber = 0; lineNumber<xMaxDigitsNumber; lineNumber++) {
			
			//Add spaces according to Y digits numbers to align the horizontal indexes
			horizontalIndexes.append(Strings.repeat(" ", yMaxDigitsNumber+3));
			
			for(int x=0; x<width; x++) {
				
				if(String.valueOf(x).length()>lineNumber) {
					horizontalIndexes.append(cell(String.valueOf(x).toCharArray()[lineNumber]));
				}
				else {
					horizontalIndexes.append(cell(' '));
				}
			}
			//New line to print the next digits
			horizontalIndexes.append("\n");
		}
	
		return horizontalIndexes.toString();
	}
	
	/**
	 * Adds an horizontal line separator
	 * @param width
	 * @return
	 */
	private static String addHorizontalSeparator(int width, int yMaxDigitsNumber) {
		
		StringBuilder horizontalSeprator = new StringBuilder();		
		horizontalSeprator.append("\n").append(Strings.repeat(HORIZONTAL_SEPARATOR, (width+1)*4+yMaxDigitsNumber)).append("\n");
		return horizontalSeprator.toString();
	}
	
	/**
	 * Retrieve the mower orientation as a String
	 * @param position
	 * @param mowers
	 * @return
	 */
	private static char getMowerOrientation(final Position position, final Mower... mowers) {
		Predicate<Mower> predicate = new Predicate<Mower>() {
			@Override
			public boolean apply(Mower input) {
				return input.getCell().getPosition().equals(position);
			}
		};
		//ATTENTION : Throws an NoSuchElement Exception if the element is not found
	    return String.valueOf(Iterables.find(Arrays.asList(mowers), predicate).getOrientation()).charAt(0);
	 }
		
}

