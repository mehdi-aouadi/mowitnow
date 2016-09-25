package com.xebia.mowitnow.io;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Splitter;
import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Orientation;
import com.xebia.mowitnow.mower.Mower;

/** 
 * Represents a Parser implementation for mowers : Parse a String to a Mower
 * @author Mehdi AOUADI
 *
 */
public class MowerParser implements Parser<String, Mower> {

	private static final String SEPARATOR 	= " ";
	private static final String PATTERN 	= "^\\d+ \\d+ [N|E|W|S]$";

	private final Lawn lawn;
	
	private final AtomicInteger counter = new AtomicInteger();
	
	public MowerParser(Lawn lawn) {
		this.lawn = lawn;
	}
	
	@Override
	public Mower parse(String source) {
		checkArgument(source.matches(PATTERN),
		        "Error when parsing the mower infos [Expecting: 'x y Orientaion'; Actual: '"
		            + source + "']");
		    List<String> fields = Splitter.on(SEPARATOR).splitToList(source);
		    int x = Integer.valueOf(fields.get(0));
		    int y = Integer.valueOf(fields.get(1));
		    Orientation orientation = Orientation.valueOf(fields.get(2));
		    return new Mower(counter.getAndIncrement(), lawn.cellAt(x, y), orientation);
	}

}