import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Board {

	final private static int Size = 9;
	private Cell[][] board;
	private int[][] rowCnt;
	private int[][] colCnt;
	private int[][] sqrCnt;

	public Board() {
		this.board = new Cell[Size][Size];
		this.rowCnt = new int[Size][10];
		this.colCnt = new int[Size][10];
		this.sqrCnt = new int[Size][10];
		for (int row = 0; row < Size; row++) {
			for (int col = 0; col < Size; col++) {
				this.board[row][col] = new Cell(row, col, 0, false);
			}
		}
	}

	public Board(String filename) {
		this();
		if (!this.read(filename)) {
            System.err.println("Board.Board():: unable to read file " + filename);
        }
	}

	public void addCell(int row, int col, int value, boolean locked) {
		if ((value < 1 || value > 9) || (row < 0 || row >= this.getRows() || col < 0 || col >= this.getCols()))
			return;
		this.board[row][col] = new Cell(row, col, value, locked);
		this.rowCnt[row][value]++;
		this.colCnt[row][value]++;
		this.sqrCnt[row / 3 + col % 3][value]++;
	}

	public boolean read(String filename) {
		try {
			// assign to a variable of type FileReader a new FileReader object, passing filename to the
			// constructor
			FileReader fr = new FileReader(filename);
			// assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader
			// variable to the constructor
			BufferedReader br = new BufferedReader(fr);

			// assign to a variable of type String line the result of calling the readLine method of
			// your BufferedReader object.
			String line = br.readLine();
			// start a while loop that loops while line isn't null
			int row = 0;
			while (line != null) {
				if (row >= Size) {
					System.err.println("Input board exceeds height");
					break;
				}
				String[] arr = line.split("[ ]+");
				for (int col = 0; col < Size; col++) {
					int value = Integer.parseInt(arr[col]);
					this.board[row][col] =
					        new Cell(row, col, value, (value != 0));
				}
				row++;
				line = br.readLine();
			}
			// call the close method of the BufferedReader
			br.close();
			return true;
		} catch (FileNotFoundException ex) {
			System.out.println("Board.read():: unable to open file " + filename);
		} catch (IOException ex) {
			System.out.println("Board.read():: error reading file " + filename);
		}

		return false;
	}
	public int getCols() { return Size; }
	public int getRows() { return Size; }
	public Cell get(int r, int c) { return this.board[r][c]; }
	public boolean isLocked(int r, int c) { return this.board[r][c].isLocked(); }
	public int numLocked() {
		int count = 0;
		for (int row = 0; row < Size; row++) {
			for (int col = 0; col < Size; col++) {
				if (this.board[row][col].isLocked()) {
					count++;
				}
			}
		}
		return count;
	}
	public int value(int r, int c) { return this.board[r][c].getValue(); }
	public void set(int r, int c, int value) { this.board[r][c].setValue(value); }
	public void set(int r, int c, int value, boolean locked) {
		this.board[r][c].setValue(value);
		this.board[r][c].setLocked(locked);
	}

	public String toString() {
		String str = new String();
		for (int row = 0; row < Size; row++) {
			if (row % (Size / 3) == 0 && row != 0) {
				str += "------+-------+------\n";
			}
			for (int col = 0; col < Size; col++) {
				if (col % (Size / 3) == 0 && col != 0) {
					str += "| ";
				}
				int value = this.board[row][col].getValue();
				str += value == 0 ? "  " : value + " ";
			}
			str += "\n";
		}
		return str;
	}

	public boolean validValue(int row, int col, int value) {
		if ((value < 1 || value > 9) ||			              // check value
		    (row < 0 || row >= this.getRows() || col < 0 || col >= this.getCols()) || // check row and col
		    (rowCnt[row][value] > 1 || colCnt[col][value] > 1 ||
		     sqrCnt[row / 3 + col % 3][value] > 1)) // check uniqueness
			return false;
		return true;
	}

	public boolean validSolution() {
		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getCols(); col++) {
				if (!validValue(row, col, this.board[row][col].getValue()))
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Board board = new Board();
		String filename = "board.txt";
		if (args.length > 0)
			filename = args[0];
		board.read(filename);
		System.out.println(board + "");
        System.out.println(board.validSolution());
            
	}
}
