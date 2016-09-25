package com.xebia.mowitnow.io;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.google.common.io.Resources;
import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Move;
import com.xebia.mowitnow.base.Orientation;
import com.xebia.mowitnow.mower.Commander;
import com.xebia.mowitnow.mower.Mower;

public class LoaderTest {

	private Lawn lawn;
	
	@Test
	public void loaderNotExistingFileTest() throws URISyntaxException {
		Loader loader = new Loader();
		Commander commander = loader.fromFile(new File("xxxx.txt"));
		assertNull(commander);
	}
	
	@Test
	public void loaderFromExistingValidFileTest() throws URISyntaxException {
		Loader loader = new Loader();
		Commander commander = loader.fromFile(new File(Resources.getResource("data.txt").toURI()));
		lawn = new Lawn(6, 6);
		assertEquals(lawn, commander.getLawn());
		checkMowers(commander.getMowers());
	}

	private void checkMowers(List<Mower> mowers) {
		for (Mower mower : mowers) {
			assertTrue(this.expectedMowers().contains(mower));
		}
	}
	
	private List<Mower> expectedMowers() {
	    Mower firstMower = new Mower(0, lawn.cellAt(1, 2), Orientation.N);
	    LinkedList<Move> moves = new LinkedList<Move>(Arrays.asList(Move.G, Move.A, Move.G, Move.A, 
	    		Move.G, Move.A, Move.G, Move.A, Move.A));
	    firstMower.setMoves(moves);
	    Mower secondMower = new Mower(1, lawn.cellAt(3, 3), Orientation.E);
	    moves = new LinkedList<Move>(Arrays.asList(Move.A, Move.A, Move.D, Move.A, Move.A, Move.D, Move.A, Move.D
	    		, Move.D, Move.A));
	    secondMower.setMoves(moves);
	    return new ArrayList<Mower>(Arrays.asList(firstMower, secondMower));
	  }

}
