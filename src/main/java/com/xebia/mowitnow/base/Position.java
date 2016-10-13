package com.xebia.mowitnow.base;

import java.util.Objects;

/**
 * 
 * Represents a position based on X and Y axis 
 * @author Mehdi AOUADI
 *
 */
public class Position {

	/**
	 * Horizontal index
	 */
	private final int x;
	/**
	 * Vertical index
	 */
	private final int y;	
	
	/**
	 * Position constructor
	 * @param x index
	 * @param y index
	 */
	public Position(int x, int y) {
		super();		
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the horizontal index
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the vertical index
	 */
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || getClass() != obj.getClass()) {
	        return false;
		}
		
		Position objPosition = (Position)obj;
		return this.getX()==objPosition.getX()
				&&this.getY()==objPosition.getY()
				?true:false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getX(), this.getY());
	}
	
	
}
