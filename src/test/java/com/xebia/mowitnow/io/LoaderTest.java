package com.xebia.mowitnow.io;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Move;
import com.xebia.mowitnow.base.Orientation;
import com.xebia.mowitnow.mower.Commander;
import com.xebia.mowitnow.mower.Mower;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class LoaderTest {

	private Lawn lawn;
	
	@Test
	@Parameters({"src/test/resources/correctData.txt"})
	public void loaderFromExistingValidFileTest(String filePath) throws URISyntaxException {
		Loader loader = new Loader();
		Commander commander = loader.fromFile(new File(filePath));
		lawn = new Lawn(6, 6);
		assertEquals(lawn, commander.getLawn());
		checkMowers(commander.getMowers());
	}
	
	@Test
	public void loaderNotExistingFileTest(){
		Loader loader = new Loader();
		Commander commander = loader.fromFile(new File("xxxx.txt"));
		assertNull(commander);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters({"src/test/resources/badLawn.txt", "src/test/resources/badMowerPosition.txt", "src/test/resources/lessThanTwoLines.txt"})
	public void loaderBadDataFileTest(String path) throws URISyntaxException {
		Loader loader = new Loader();
		loader.fromFile(new File(path));
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
