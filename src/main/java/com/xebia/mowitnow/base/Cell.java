package com.xebia.mowitnow.base;

import java.util.HashMap;
import java.util.Map;

import java.util.Objects;



/**
 * 
 * Represents a cell of the lawn's grid
 * @author Mehdi AOUADI
 *
 */
public class Cell {	
	
	/**
	 * The position of the cell
	 */
	private final Position position;
	/**
	 * TRUE when the cell is mowed
	 */
	private boolean mowed;
	/**
	 * TRUE if the cell contains a mower
	 */
	private boolean locked;
	
	/**
	 * The cell's neighbors (Cell/Orientation)
	 */
	private final Map<Orientation, Cell> neighbors = new HashMap<Orientation, Cell>
						(Orientation.values().length);
	/**
	 * Cell constructor
	 * 
	 * @param position
	 * @param mowed
	 */
	public Cell(Position position) {
		super();
		this.position = position;
		this.mowed = false;
		this.locked = false;
	}		

	/**
	 * Tests if the Cell is mowed  
	 * @return true if the cell is already mowed
	 */
	public boolean isMowed() {
		return mowed;
	}

	/**
	 * Mow the cell : Set mowed as TRUE
	 */
	public void mow() {
		this.mowed = true;
	}
	
	/**
	 * Lock the mow when a mower occupy it
	 */
	public void lock() {
		this.locked = true;
	}
	
	/**
	 * Unlock the cell when a mower leaves it
	 */
	public void unLock() {
		this.locked = false;
	}
	
	/**
	 * Tests if the Cell is locked 
	 * @return true if the cell is locked (contains a mower)
	 */
	public boolean isLocked() {
		return this.locked;
	}

	/**
	 * @return the Position of the Cell
	 */
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Put the next neighbor Cell by Orientation
	 * @param orientation
	 * @param cell
	 */
	public void nextCell(final Orientation orientation, final Cell cell) {
	    this.neighbors.put(orientation, cell);
	  }
	
	/**
	 * Provides the next Cell
	 * @param orientation
	 * @return the neighbor Cell on Orientation
	 */
	public Cell nextCell(Orientation orientation){
		return neighbors.get(orientation);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || getClass()!=obj.getClass()) {
	        return false;
		}
		
		Cell objCell = (Cell)obj;
		return Objects.equals(this.getPosition(), objCell.getPosition())
				&&this.isLocked()==objCell.isLocked()
				&&this.isMowed()==objCell.isMowed()
				?true:false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getPosition(), this.isLocked(), this.isMowed()); 
	}
	
	

}
