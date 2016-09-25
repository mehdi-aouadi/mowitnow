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
	 * Returns true if the cell is already mowed
	 * @return
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
	 * Returns true if the cell is locked (contains a mower)
	 * @return
	 */
	public boolean isLocked() {
		return this.locked;
	}

	/**
	 * Returns the Position of the cell
	 * @return
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
	 * Returns the neighbor Cell on Orientation
	 * @param orientation
	 * @return
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
