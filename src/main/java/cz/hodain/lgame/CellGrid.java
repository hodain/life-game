package cz.hodain.lgame;

public class CellGrid {

	private Cell[][] cells;


	public CellGrid(int sizeX, int sizeY) {
		if (sizeX <= 0) {
			throw new IllegalArgumentException();
		}

		if (sizeY <= 0) {
			throw new IllegalArgumentException();
		}

		cells = new Cell[sizeX][sizeY];
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				new Cell(x, y, this);
			}
		}
	}


	public CellGrid(boolean[][] matrix) {
		cells = new Cell[matrix.length][matrix[0].length];

		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				new Cell(x, y, matrix[x][y], this);
			}
		}
	}


	public void addRow() {
		Cell[][] result = new Cell[getSizeX() + 1][getSizeY()];
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				result[x][y] = cells[x][y];
				cells[x][y] = null;
			}
		}
		this.cells = result;
		for (int y = 0; y < cells[0].length; y++) {
			new Cell(getSizeX() - 1, y, false, this);
		}
	}


	public void removeRow() {
		if (getSizeX() > 1) {
			Cell[][] result = new Cell[getSizeX() - 1][getSizeY()];
			for (int x = 0; x < cells.length - 1; x++) {
				for (int y = 0; y < cells[x].length; y++) {
					result[x][y] = cells[x][y];
				}
			}
			this.cells = result;
		}
	}


	public void addColumn() {
		Cell[][] result = new Cell[getSizeX()][getSizeY() + 1];
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				result[x][y] = cells[x][y];
				cells[x][y] = null;
			}
		}
		this.cells = result;
		for (int x = 0; x < cells.length; x++) {
			new Cell(x, getSizeY() - 1, false, this);
		}
	}


	public void removeColumn() {
		if (getSizeY() > 1) {
			Cell[][] result = new Cell[getSizeX()][getSizeY() - 1];
			for (int x = 0; x < cells.length; x++) {
				for (int y = 0; y < cells[x].length - 1; y++) {
					result[x][y] = cells[x][y];
				}
			}
			this.cells = result;
		}
	}


	public int getSizeX() {
		return cells.length;
	}


	public int getSizeY() {
		return cells[0].length;
	}


	public void setCell(int x, int y, Cell cell) {
		if (x <= 0 && x > getSizeX()) {
			throw new IllegalArgumentException();
		}

		if (y <= 0 && y > getSizeY()) {
			throw new IllegalArgumentException();
		}
		cells[x][y] = cell;
	}


	public Cell getCell(int x, int y) {
		if (x <= 0 && x > getSizeX()) {
			throw new IllegalArgumentException();
		}

		if (y <= 0 && y > getSizeY()) {
			throw new IllegalArgumentException();
		}

		return cells[x][y];
	}


	public void next() {
		this.apply(getNewMatrix());
	}


	public boolean[][] getNewMatrix() {
		boolean[][] result = new boolean[cells.length][cells[0].length];
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				result[x][y] = cells[x][y].willAlive();
			}
		}
		return result;
	}


	public boolean[][] getMatrix() {
		boolean[][] result = new boolean[cells.length][cells[0].length];
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				result[x][y] = cells[x][y].isAlive();
			}
		}
		return result;
	}


	public void apply(boolean[][] matrix) {
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				cells[x][y].setLife(matrix[x][y]);
			}
		}
	}


	public boolean hasAliveCells() {
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				if (cells[x][y].isAlive()) {
					return true;
				}
			}
		}
		return false;
	}


	public Cell getLeftNeighbour(Cell cell) {
		return cells[getCalculatedX(cell.getX() - 1)][cell.getY()];
	}


	public Cell getRightNeighbour(Cell cell) {
		return cells[getCalculatedX(cell.getX() + 1)][cell.getY()];
	}


	public Cell getTopNeighbour(Cell cell) {
		return cells[cell.getX()][getCalculatedY(cell.getY() + 1)];
	}


	public Cell getBottomNeighbour(Cell cell) {
		return cells[cell.getX()][getCalculatedY(cell.getY() - 1)];
	}


	public Cell getTopLeftNeighbour(Cell cell) {
		return cells[getCalculatedX(cell.getX() - 1)][getCalculatedY(cell.getY() + 1)];
	}


	public Cell getTopRightNeighbour(Cell cell) {
		return cells[getCalculatedX(cell.getX() + 1)][getCalculatedY(cell.getY() + 1)];
	}


	public Cell getBottomLeftNeighbour(Cell cell) {
		return cells[getCalculatedX(cell.getX() - 1)][getCalculatedY(cell.getY() - 1)];
	}


	public Cell getBottomRightNeighbour(Cell cell) {
		return cells[getCalculatedX(cell.getX() + 1)][getCalculatedY(cell.getY() - 1)];
	}


	private int getCalculatedX(int x) {
		if (x < 0) {
			return x + cells.length;
		} else if (x >= cells.length) {
			return x - cells.length;
		} else {
			return x;
		}
	}


	private int getCalculatedY(int y) {
		if (y < 0) {
			return y + cells[0].length;
		} else if (y >= cells[0].length) {
			return y - cells[0].length;
		} else {
			return y;
		}
	}


	public String toString() {
		String result = "";
		for (int x = 0; x < cells.length; x++) {
			result += "|";
			for (int y = 0; y < cells[x].length; y++) {
				result += cells[x][y].toString();
			}
			result += "|\n";
		}
		return result;
	}


	public void clear() {
		boolean[][] result = new boolean[cells.length][cells[0].length];
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				cells[x][y].setLife(false);
			}
		}
	}
}
