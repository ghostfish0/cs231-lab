import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Board {

	final public static Random rand = new Random();
	final private static int Size = 9;
	private int count;
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
				this.addCell(row, col, 0);
			}
		}
	}

	public Board(String filename) {
		this();
		if (!this.read(filename)) {
			System.err.println("Board.Board():: unable to read file " + filename);
		}
	}

	public Board(int numLocked) {
		this();
		double prob = (double)numLocked / (this.getRows() * this.getCols());

		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getCols(); col++) {
				if (rand.nextDouble() < prob) {
					int value = rand.nextInt(9) + 1;
					while (!this.addCell(row, col, value) && value <= 9) {
						// value = rand.nextInt(9) + 1;
						value++;
					}
				}
			}
		}
	}

	public boolean addCell(int row, int col, int value) {
		if ((value < 0 || value > 9) || (row < 0 || row >= this.getRows() || col < 0 || col >= this.getCols())) {
			System.err.println("Board.addCell():: invalid row, col, or value; row: " + row +
				   ", col: " + col + ", value: " + value);
			return false;
		}
		if (this.board[row][col] != null) {
			int oldValue = this.board[row][col].getValue();
			this.rowCnt[row][oldValue]--;
			this.colCnt[col][oldValue]--;
			this.sqrCnt[3 * (row / 3) + col / 3][oldValue]--;
		}
		this.board[row][col] = new Cell(row, col, value);
		this.rowCnt[row][value]++;
		this.colCnt[col][value]++;
		this.sqrCnt[3 * (row / 3) + col / 3][value]++;
		return true;
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
					if (value < 0 || value > 9) {
						br.close();
						return false;
					}
					this.addCell(row, col, value);
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
	public int numUnlocked() { return Size * Size - this.numLocked(); }
	public int value(int r, int c) { return this.board[r][c].getValue(); }
	public void set(int r, int c, int value) { this.addCell(r, c, value); }
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
		    (rowCnt[row][value] > 0 || colCnt[col][value] > 0 ||
		     sqrCnt[3 * (row / 3) + col / 3][value] > 0)) // check uniqueness
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
