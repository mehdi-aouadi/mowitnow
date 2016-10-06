package com.xebia.mowitnow.mower;

import java.util.Objects;
import java.util.Observable;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;

import com.xebia.mowitnow.base.Cell;
import com.xebia.mowitnow.base.Move;
import com.xebia.mowitnow.base.Orientation;
import com.xebia.mowitnow.base.Position;


/**
 * 
 * Represents a Mower
 * 
 * @author Mehdi AOUADI
 *
 */

public class Mower extends Observable{
	
	private static Logger logger = LoggerFactory.getLogger(Mower.class);
	
	/**
	 * The mower identifier (Unique)
	 */
	private final int id;
	/**
	 * The mower cell
	 */
	private Cell cell;
	/**
	 * The mower orientation
	 */
	private Orientation	orientation;
	/**
	 * A queue of instruction to be executed by the mower
	 */
	private	Queue<Move>	moves;
	
	/**
	 * Mower constructor
	 * @param id
	 * @param cell
	 * @param orientation
	 * @param lawn
	 */
	public Mower(int id, Cell cell, Orientation orientation) {
		super();
		checkArgument(!cell.isLocked(), "Error when creating a new Mower at positon X:"+cell.getPosition().getX()+" Y:"+cell.getPosition().getY()+". This cell already contains a mower");
		this.id = id;
		this.orientation = orientation;
		this.cell = cell;
		
		//The initial position of the mower is locked
		this.cell.lock();
	}
	
	/**
	 * 
	 * Mower constructor with a default Id set to 0
	 * @param position
	 * @param orientation
	 * @param lawn
	 */
	public Mower(Cell cell, Orientation orientation) {
		super();
		checkArgument(!cell.isLocked(), "Error when creating a new Mower at positon X:"+cell.getPosition().getX()+" Y:"+cell.getPosition().getY()+". This cell already contains a mower");
		this.id = 0;
		this.orientation = orientation;
		this.cell = cell;
		
		//The initial position of the mower is locked
		this.cell.lock();
	}
	
	/**
	 * Return the mower Cell
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Return the mower Cell
	 * @return
	 */
	public Cell getCell() {
		return this.cell;
	}
	
	/**
	 * Set the mower Cell
	 * @param cell
	 */
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	/**
	 * Returns the mower orientation
	 * @return
	 */
	public Orientation getOrientation() {
		return orientation;
	}
	
	/**
	 * Set the mower orientation
	 * @param orientation
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}	
	
	/**
	 * Returns the mower's instructions queue
	 * @return
	 */
	public Queue<Move> getMoves() {
		return moves;
	}

	/**
	 * Set the mower's instructions queue
	 * @param moves
	 */
	public void setMoves(Queue<Move> moves) {
		this.moves = moves;
	}
	
	public Position getPosition() {
		return this.cell.getPosition();
	}
	
	@Override
	public String toString() {
		return "Mower Id : " + this.id + " at Poisition X:" + this.cell.getPosition().getX() + " Y:" + this.cell.getPosition().getY();
	}

	/**
	 * Make the mower turns right
	 * @return
	 */
	public Mower turnRight(){
		
		switch(this.orientation) {
		case N :
			this.setOrientation(Orientation.E);
			break;
		case E :
			this.setOrientation(Orientation.S);
			break;
		case S :
			this.setOrientation(Orientation.W);
			break;
		case W :
			this.setOrientation(Orientation.N);
			break;		
		default:
            throw new IllegalArgumentException("Invalid direction given");
		}
		
		changeAndNotify();
		return this;
		
	}
	
	/**
	 * Make the mower turns left
	 * @return
	 */
	public Mower turnLeft(){
		
		switch(this.orientation) {
		case N :
			this.setOrientation(Orientation.W);
			break;
		case W :
			this.setOrientation(Orientation.S);
			break;
		case S :
			this.setOrientation(Orientation.E);
			break;
		case E :
			this.setOrientation(Orientation.N);
			break;	
		default:
            throw new IllegalArgumentException("Invalid direction given");
		}	
		
		changeAndNotify();
		return this;
	}
	
	/**
	 * 
	 * Make the mower move forward
	 * @return
	 */
	public void moveForward(){
		Cell nextCell = this.cell.nextCell(this.orientation);
		if(null!=nextCell && !nextCell.isLocked()) {
			this.cell.unLock();
			this.setCell(nextCell);
			this.cell.lock();
			mow();
			changeAndNotify();
		}
	}
	
	/**
	 * Mows the current Cell
	 */
	public void mow() {
		this.cell.mow();
	}
	
	/**
	 * Launh the mower : Execute all the instructions from the queue
	 */
	public void start() {
		logger.debug("The mower {} is coming, brace yourself ", this.id);
	    mow();
	    int index = 1;
		 if (moves != null && !moves.isEmpty()) {
		      while (!moves.isEmpty()) {
		    	  Move move = moves.poll();
		    	  logger.debug("Performing the insctruction N° {} {} .", index++, move);
		    	  move.move(this);		    	  
		      }
		    } else {
		    	logger.warn("The mower {} doesn't have any instruction", this.toString());
		    }
		 logger.debug("Stopping the mower {}", this.toString());
	}	
	
	/**
	 * Notify the observer (The monitor) for any change
	 */
	private void changeAndNotify() {
		setChanged();
	    notifyObservers();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || getClass() != obj.getClass()) {
	        return false;
		}
		
		Mower objMower = (Mower)obj;
		
		return this.getId()==objMower.getId()
				&&Objects.equals(this.getCell(), objMower.getCell())
				&&Objects.equals(this.getOrientation(), objMower.getOrientation())
				?true:false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.cell, this.orientation);
	}
	
	
	
	
	
}
