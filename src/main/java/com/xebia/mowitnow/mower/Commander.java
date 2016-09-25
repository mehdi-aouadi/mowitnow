package com.xebia.mowitnow.mower;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.util.GridPrinter;

/**
 * 
 * Represents a Monitor that manages the Lawn and its mowers.
 * It implements the Observer interface in order to handle all the mowers movements.
 * 
 * @author Mehdi AOUADI
 *
 */
public class Commander implements Observer{
	
	/**
	 * The Lawn to mow
	 */
	private final Lawn lawn;
	/**
	 * The list of the mowers
	 */
	private List<Mower> mowers;
	
	private static Logger logger = LoggerFactory.getLogger(Commander.class);
	
	/**
	 * The commander constructor
	 * @param lawn
	 * @param mowers
	 */
	public Commander(Lawn lawn, List<Mower> mowers) {
		this.lawn = lawn;
		this.setMowers(mowers);
		logger.debug("Mowers set. Here is the inital state of the lawn : \n {}",GridPrinter.draw(lawn, mowers.toArray(new Mower[] {})));
	}	
	
	/**
	 * Starts the mowers
	 */
	public void startMowing() {
		for(Mower mower : this.mowers) {
			mower.addObserver(this);
			mower.start();			
		}
	}
	
	/**
	 * Returns the mowers list
	 * @return
	 */
	public List<Mower> getMowers() {
		return mowers;
	}

	/**
	 * Sets the mowers list
	 * @param mowers
	 */
	public void setMowers(List<Mower> mowers) {
		this.mowers = mowers;
	}
	
	/**
	 * Return the lawn to mow
	 * @return
	 */
	public Lawn getLawn() {
		return lawn;
	}

	public void update(Observable o, Object arg) {
		logger.debug("A move has been detected. Here is the lawn status : \n {}",GridPrinter.draw(lawn, mowers.toArray(new Mower[] {})));
	}
	
}
