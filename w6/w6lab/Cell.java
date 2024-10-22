public class Cell {
	private int row;
	private int col;
	private int value;
	private boolean locked;

	public Cell(int row, int col, int value) { this(row, col, value, false); }
	public Cell(int row, int col, int value, boolean locked) {
		this.row = row;
		this.col = col;
		this.value = value;
		this.locked = locked;
	}

	public int getRow() { return row; }
	public void setRow(int row) { this.row = row; }
	public int getCol() { return col; }
	public void setCol(int col) { this.col = col; }
	public int getValue() { return value; }
	public void setValue(int value) { this.value = value; }
	public boolean isLocked() { return locked; }
	public void setLocked(boolean locked) { this.locked = locked; }

	//public String toString() { return "(" + row + ", " + col + "): " + value; }
    public String toString() { return this.value + ""; }
}
