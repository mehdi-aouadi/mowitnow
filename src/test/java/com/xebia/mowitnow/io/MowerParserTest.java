package com.xebia.mowitnow.io;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Orientation;
import com.xebia.mowitnow.base.Position;
import com.xebia.mowitnow.mower.Mower;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class MowerParserTest {
	
	private static MowerParser parser;
	private static Lawn	lawn;
	private Mower actualMower;
	private Position expectedPosition;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		lawn = new Lawn(10, 10);
		parser = new MowerParser(lawn);
	}
	
	@Test
	@Parameters(value = {"1 2 N, 1, 2, N", "0 0 E, 0, 0, E", "5 5 S, 5, 5, S", "2 1 W, 2, 1, W"})
	public void mowerIsValid(String line, int x, int y, Orientation expectedOrientation) {
		expectedPosition = new Position(x, y);
		actualMower = parser.parse(line);		
	    checkMower(expectedPosition, expectedOrientation, actualMower);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(value = {"1 2 O", "-1 2 N", "2 -3 S", "3 2 5", "N 1 3"})
	public void mowerIsNotValid(final String line) {
		parser = new MowerParser(lawn);
		parser.parse(line);
	}

	private void checkMower(Position expectedPosition, Orientation expectedOrientation, Mower actualMower) {
		assertEquals(expectedPosition, actualMower.getPosition());
		assertEquals(expectedOrientation, actualMower.getOrientation());
	}

}