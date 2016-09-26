package com.xebia.mowitnow.base;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a Lawn
 * @author Mehdi AOUADI
 *
 */
public class Lawn {

	/**
	 * The Lawn width (East limit)
	 */
	private final int width;
	/**
	 * The Lawn height (North limit)
	 */
	private final int height;
	/**
	 * A grid of cells representing the lawn surface
	 */
	private final Cell[][] grid;
	
	private static Logger logger = LoggerFactory.getLogger(Lawn.class);

	/**
	 * 
	 * Lawn constructor
	 * 
	 * @param width
	 * @param heitgh
	 */
	public Lawn(int width, int heitgh) {
		super();
		checkArgument(width > 0 && heitgh > 0, "Width and Height of the Lawn must be > 0");
		this.width = width;
		this.height = heitgh;
		this.grid = new Cell[this.width][this.height];
		
		for(int x=0; x<this.width; x++)	{
			for(int y=0; y<this.height; y++) {
				grid[x][y] = new Cell(new Position(x, y));
 			}
		}	
		
		//Adding the cells neighbors
		for(int x=0; x<this.width; x++)	{
			for(int y=0; y<this.height; y++) {
				
				if(x>0) {
					cellAt(x, y).nextCell(Orientation.W, cellAt(x-1, y));
				}
				
				if(x<width-1) {
					cellAt(x, y).nextCell(Orientation.E, cellAt(x+1, y));
				}
				
				if(y>0) {
					cellAt(x, y).nextCell(Orientation.S, cellAt(x, y-1));
				}
				
				if(y<height-1) {
					cellAt(x, y).nextCell(Orientation.N, cellAt(x, y+1));
				}
 			}			
		}
		
		logger.debug("A lawn has been initialized : North limit is {} and East limit is {} ", this.height, this.width);
	}
	

	/**
	 * Returns the lawn width
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the lawn height
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Return the cell located at X:Y
	 * @param x
	 * @param y
	 * @return
	 */
	public Cell cellAt(int x, int y) {
		checkArgument(in(x, y), "The position X:" + x + ", Y:" + y
			        + " is out of the Lawn");
		return grid[x][y];
	}
	
	/**
	 * Returns TRUE if the x:y is within the Lawn
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean in(int x, int y) {
	    return (x < width && x >= 0) && (y < height && y >= 0);
	  }

	@Override
	public String toString() {
		return "I am the Lawn. My Width is " + this.getWidth() + " and my Height is " + this.getHeight();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || getClass() != obj.getClass()) {
	        return false;
		}
		
		Lawn objLawn = (Lawn)obj;
		return this.getWidth()==objLawn.getWidth()
				&&this.getHeight()==objLawn.getHeight()
				?true:false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getHeight(), this.getWidth());
	}
	
}
