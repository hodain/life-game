package cz.hodain.lgame;

import org.junit.Test;

import junit.framework.Assert;


public class CellGridTest {

	private static final int SIZE_Y = 10;
	private static final int SIZE_X = 10;

	private CellGrid underTest = new CellGrid(SIZE_X, SIZE_Y);


	@Test
	public void createArreaTest() throws IllegalAccessException {
		boolean[][] matrix = {{false, false, false}, {false, true, true}, {false, false, false}};
		CellGrid arrea = new CellGrid(matrix);

		Assert.assertTrue(arrea.getSizeX() == matrix.length);
		Assert.assertTrue(arrea.getSizeY() == matrix[0].length);
		Assert.assertEquals(true, arrea.getCell(1, 1).isAlive());
		Assert.assertEquals(false, arrea.getCell(0, 0).isAlive());
		Assert.assertEquals(false, arrea.getCell(2, 2).isAlive());
	}


	@Test
	public void getSizeX() {
		Assert.assertEquals(SIZE_X, underTest.getSizeX());
	}


	@Test
	public void getSizeY() {
		Assert.assertEquals(SIZE_Y, underTest.getSizeY());
	}


	@Test
	public void getCellTest() throws IllegalAccessException {
		Assert.assertNotNull(underTest.getCell(0, 0));
		Assert.assertFalse(underTest.getCell(0, 0).isAlive());
		Assert.assertFalse(underTest.getCell(9, 9).isAlive());
	}


	@Test
	public void run() {
		boolean[][] matrix = {	{true, false, false, false, false, true},
								{false, true, false, false, true, false},
								{false, false, true, true, false, false},
								{false, false, true, true, false, false},
								{false, true, false, false, true, false},
								{true, false, false, false, false, true}};

		CellGrid cellGrid = new CellGrid(matrix);
		System.out.println(cellGrid);
		for (int i = 0; i < 100000; i++) {
			cellGrid.next();
			System.out.println("Generation " + i);
			System.out.println(cellGrid);
			if (!cellGrid.hasAliveCells()) {
				return;
			}

		}
		System.out.println("END");
	}


	@Test
	public void testAddRow() throws Exception {
		boolean[][] matrix = {	{true, false},
								{false, true}
		};

		CellGrid cellGrid = new CellGrid(matrix);
		cellGrid.addRow();
	}
}
