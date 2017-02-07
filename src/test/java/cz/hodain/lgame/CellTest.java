package cz.hodain.lgame;

import org.junit.Test;

import junit.framework.Assert;


public class CellTest {

	private CellGrid cellGrid = new CellGrid(10, 10);
	private Cell underTest = new Cell(0, 0, cellGrid);

	@Test
	public void isAliveTest() {
		Assert.assertFalse(underTest.isAlive());
		underTest.setLife(true);;
		Assert.assertTrue(underTest.isAlive());
		underTest.setLife(false);;
		Assert.assertFalse(underTest.isAlive());

		Assert.assertFalse(new Cell(0, 0, cellGrid).isAlive());
		Assert.assertFalse(new Cell(0, 0, false, cellGrid).isAlive());
		Assert.assertTrue(new Cell(0, 0, true, cellGrid).isAlive());
	}

}
