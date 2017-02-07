package cz.hodain.lgame;

public class Cell {

	private boolean alive = false;
	private int x;
	private int y;
	private transient CellGrid grid;


	public Cell(int x, int y, boolean alive, CellGrid cellGrid) {
		this.grid = cellGrid;
		this.alive = alive;
		this.x = x;
		this.y = y;
		this.grid.setCell(x, y, this);
	}


	public Cell(int x, int y, CellGrid arrea) {
		this.grid = arrea;
		this.alive = false;
		this.x = x;
		this.y = y;
		this.grid.setCell(x, y, this);
	}


	public Cell(boolean alive) {
		this.alive = alive;
	}


	public boolean isAlive() {
		return alive;
	}


	public void setLife(boolean alive) {
		this.alive = alive;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getNumberOfAliveNeighbours() {
		int result = 0;
		if (this.grid.getTopNeighbour(this).isAlive()) {
			result++;
		}
		if (this.grid.getBottomNeighbour(this).isAlive()) {
			result++;
		}
		if (this.grid.getLeftNeighbour(this).isAlive()) {
			result++;
		}
		if (this.grid.getRightNeighbour(this).isAlive()) {
			result++;
		}
		if (this.grid.getTopRightNeighbour(this).isAlive()) {
			result++;
		}
		if (this.grid.getTopLeftNeighbour(this).isAlive()) {
			result++;
		}
		if (this.grid.getBottomRightNeighbour(this).isAlive()) {
			result++;
		}
		if (this.grid.getBottomLeftNeighbour(this).isAlive()) {
			result++;
		}
		return result;
	}


	public boolean willAlive() {
		int neighbours = this.getNumberOfAliveNeighbours();
		if (neighbours < 2 || neighbours > 3) {
			return false;
		} else if (neighbours == 3) {
			return true;
		} else {
			return isAlive();
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (alive != other.alive)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}


	public String toString() {
		if (this.isAlive()) {
			return "X";
		} else {
			return " ";
		}
	}

}
