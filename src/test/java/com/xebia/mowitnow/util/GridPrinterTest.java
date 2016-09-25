package com.xebia.mowitnow.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xebia.mowitnow.base.Cell;
import com.xebia.mowitnow.base.Lawn;
import com.xebia.mowitnow.base.Orientation;
import com.xebia.mowitnow.base.Position;
import com.xebia.mowitnow.mower.Mower;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GridPrinterTest {

	private static Lawn lawn;
	private static int id = 1;
	private static List<Mower> mowers;
	
	private static Logger logger = LoggerFactory.getLogger(GridPrinterTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mowers = new ArrayList<Mower>();
		Mower mower = new Mower(id, new Cell(new Position(3, 5)), Orientation.N);
		id++;
		mowers.add(mower);
		mower = new Mower(id, new Cell(new Position(1, 2)), Orientation.S);
		mowers.add(mower);
	}

	@Test
	@Parameters(value = {"5, 5", "11, 5", "5, 11", "11, 11", "101, 101"})
	public void gridPrinterTest(final int width, final int height) {
		lawn = new Lawn(width, height);
		logger.info(GridPrinter.draw(lawn, mowers.toArray(new Mower[] {})));			
	}

}
