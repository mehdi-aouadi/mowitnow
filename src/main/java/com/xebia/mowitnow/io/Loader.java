package com.xebia.mowitnow.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Move;
import com.xebia.mowitnow.mower.Commander;
import com.xebia.mowitnow.mower.Mower;

/**
 * 
 * Represents a Loader : Loads the application input and transforms it to a Commander
 * @author Mehdi AOUADI
 *
 */

public class Loader {
	
	private static final String LINE_SEPRATOR = "\n";
	
	private static Logger logger = LoggerFactory.getLogger(Loader.class);

	  public Commander fromFile(File file) {
	    try {
			return fromLines(Files.readLines(file, Charsets.UTF_8));
		} catch (IOException e) {
			logger.error("Cannot open the specified file at path : {} \n {}", file.getAbsolutePath(), e.toString());
			return null;
		}
	  }

	  public Commander fromText(String text) {
	    return fromLines(Splitter.on(LINE_SEPRATOR).omitEmptyStrings().splitToList(text));
	  }

	  public Commander fromLines(List<String> pLines) {
	    Preconditions.checkArgument(pLines.size() > 2,
	        "Error when loading the Commander: Missing informations in the file");
	    Iterator<String> lines = pLines.iterator();
	    LawnParser lawnParser = new LawnParser();
	    InstructionsParser instructionParser = new InstructionsParser();
	    Lawn lawn = lawnParser.parse(lines.next());
	    MowerParser mowerParser = new MowerParser(lawn);
	    List<Mower> mowers = Lists.newArrayList();
	    while (lines.hasNext()) {
	      Mower mower = mowerParser.parse(lines.next());
	      Queue<Move> moves = instructionParser.parse(lines.next());
	      mower.setMoves(moves);
	      mowers.add(mower);
	    }
	    return new Commander(lawn, mowers);
	  }
}
