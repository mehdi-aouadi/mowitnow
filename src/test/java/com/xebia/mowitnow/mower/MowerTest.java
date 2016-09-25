package com.xebia.mowitnow.mower;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import com.xebia.mowitnow.base.Cell;
import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Move;
import com.xebia.mowitnow.base.Orientation;
import com.xebia.mowitnow.base.Position;
import com.xebia.mowitnow.mower.MowerTest.Data.DataBuilder;
import com.xebia.mowitnow.util.GridPrinter;

@RunWith(JUnitParamsRunner.class)
public class MowerTest {

	public static Lawn lawn;
	public Cell cell;
	public Orientation orientation;
	public Mower mower;

	private static Logger logger = LoggerFactory.getLogger(MowerTest.class);

	@Before
	public void setUp() throws Exception {
		lawn = new Lawn(5, 5);
		cell = new Cell(new Position(3, 3));
		orientation = Orientation.N;
	}
	
	@Test
	@Parameters({ "N", "E", "S", "W" })
	public void mowerTurningRightTest(Orientation orientation) {
		mower = new Mower(cell, orientation);
		this.mower.setOrientation(orientation);
		checkTurningRight(mower);
	}

	@Test
	@Parameters({ "N", "E", "S", "W" })
	public void mowerTurningLeftTest(Orientation orientation) {
		mower = new Mower(cell, orientation);
		this.mower.setOrientation(orientation);
		checkTurningLeft(mower);
	}

	@Test
	@Parameters
	public void startTest(Data data) {
		data.mowers.start();
		assertEquals(data.expectedOrientation, data.mowers.getOrientation());
		assertEquals(data.expectedCell, data.mowers.getCell());
		assertEquals(data.expectedCell,	data.mowers.getCell());
	}

	public Object[][] parametersForStartTest() {
		return new Object[][] { 
			{ DataBuilder.lawn(2, 2).mower(0, 0, Orientation.E).expected(0, 0, Orientation.E) },
				{ DataBuilder.lawn(1, 1).mower(0, 0, Orientation.E).todo(Move.D).expected(0, 0, Orientation.S) },
				{ DataBuilder.lawn(1, 1).mower(0, 0, Orientation.E).todo(Move.G).expected(0, 0, Orientation.N) },
				{ DataBuilder.lawn(2, 2).mower(0, 0, Orientation.W).todo(Move.G, Move.G, Move.G, Move.G).expected(0, 0,
						Orientation.W) },
				{ DataBuilder.lawn(5, 5).mower(2, 2, Orientation.W)
						.todo(Move.D, Move.A, Move.A, Move.G, Move.A, Move.A, Move.G, Move.A, Move.G, Move.A)
						.expected(1, 3, Orientation.E) } };
	}
	
	private void checkTurningRight(Mower mower) {
		Orientation expectedOrientatinon;

		switch (mower.getOrientation()) {
		case N:
			expectedOrientatinon = Orientation.E;
			break;
		case E:
			expectedOrientatinon = Orientation.S;
			break;
		case S:
			expectedOrientatinon = Orientation.W;
			break;
		case W:
			expectedOrientatinon = Orientation.N;
			break;
		default:
			throw new IllegalArgumentException("Invalid direction given");
		}

		mower.turnRight();
		assertEquals(expectedOrientatinon, mower.getOrientation());
	}

	private void checkTurningLeft(Mower mower) {
		Orientation expectedOrientatinon;

		switch (mower.getOrientation()) {
		case N:
			expectedOrientatinon = Orientation.W;
			break;
		case E:
			expectedOrientatinon = Orientation.N;
			break;
		case S:
			expectedOrientatinon = Orientation.E;
			break;
		case W:
			expectedOrientatinon = Orientation.S;
			break;
		default:
			throw new IllegalArgumentException("Invalid direction given");
		}

		mower.turnLeft();
		assertEquals(expectedOrientatinon, mower.getOrientation());
	}


	// Builder design patter : used to create data for the test (Mowers, positions, lawn ... )
	// WARNING : The initial state of the lawn just after setting the mower is
	// not logged : Cannot access non static variable from the inner static
	// class DataBuilder
	static class Data implements Observer {

		private Lawn lawn;
		private Mower mowers;
		private Cell expectedCell;
		private Orientation expectedOrientation;

		@Override
		public void update(Observable o, Object arg) {
			logger.debug("A move has been detected. Here is the Lawn state :\n {}", GridPrinter.draw(lawn, mowers));
		}

		Cell on(final int x, final int y) {
			return lawn.cellAt(x, y);
		}

		static class DataBuilder {

			final Data data = new Data();

			static DataBuilder lawn(final int width, final int height) {
				DataBuilder builder = new DataBuilder();
				builder.data.lawn = new Lawn(width, height);
				return builder;
			}

			DataBuilder mower(final int x, final int y, final Orientation o) {
				data.mowers = new Mower(data.on(x, y), o);
				data.mowers.addObserver(data);
				return this;
			}

			DataBuilder todo(final Move... moves) {
				data.mowers.setMoves(new LinkedList<Move>(Arrays.asList(moves)));
				return this;
			}

			Data expected(final int x, final int y, final Orientation o) {
				data.expectedCell = data.on(x, y);
				data.expectedOrientation = o;
				return data;
			}
		}

	}

}
