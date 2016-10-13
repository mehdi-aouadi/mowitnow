package com.xebia.mowitnow.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;

import static com.google.common.base.Preconditions.checkArgument;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Move;
import com.xebia.mowitnow.mower.Commander;
import com.xebia.mowitnow.mower.Mower;

/**
 * 
 * Represents a Loader : Loads the application input and transforms it to a
 * Commander
 * 
 * @author Mehdi AOUADI
 *
 */

public class Loader {

	private static Logger logger = LoggerFactory.getLogger(Loader.class);

	private static final String LINE_SEPRATOR = "\n";

	public Commander fromFile(File file) {
		try {
			return fromLines(Files.readLines(file, Charsets.UTF_8));
		} catch (IOException e) {
			logger.error("Cannot open the specified file at path : {} \n {}", file.getAbsolutePath(), e.toString());
			return null;
		}
	}

	public Commander fromText(final String text) {
		return fromLines(Splitter.on(LINE_SEPRATOR).omitEmptyStrings().splitToList(text));
	}

	public Commander fromLines(List<String> pLines) {
		checkArgument(pLines.size() > 2,
				"Error when loading the Commander: Missing informations in the file. Expecting at least 3 lines.");
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
