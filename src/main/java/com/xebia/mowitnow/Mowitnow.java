package com.xebia.mowitnow;

import java.io.File;

import com.xebia.mowitnow.io.*;
import com.xebia.mowitnow.mower.*;

/**
 * Mowitnow application
 * The main class
 * The first argument could be the file path containing the configurations (Lawn, Mowers and Moves)
 */
public class Mowitnow 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Humain, I am going to mow your lawn !" );
        Loader loader = new Loader();
        Commander commander = null;
		commander = loader.fromFile(new File(args[0]));        
        commander.startMowing();
        
        System.out.println( "Mower(s) final position(s) :" );
        for(Mower mower : commander.getMowers()) {
        	System.out.println(mower.getPosition().getX() + " " + mower.getPosition().getY() + " " + mower.getOrientation());
		}
    }
}
