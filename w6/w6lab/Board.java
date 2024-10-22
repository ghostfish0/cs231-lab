import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Board {

	private Cell[][] board;

	public Board() {
		this.board = new Cell[9][9];
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				this.board[row][col] = new Cell(row, col, 0, false);
			}
		}
	}
	public Cell get(int row, int col) { return this.board[row][col]; }

	public void set(int row, int col, int value) { this.board[row][col].setValue(value); }

	public void set(int row, int col, boolean locked) { this.board[row][col].setLocked(locked); }

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
				if (row >= 9) {
					System.err.println("Input board exceeds height");
					break;
				}
				String[] arr = line.split("[ ]+");
				for (int col = 0; col < 9; col++) {
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

	public String toString() {
		String str = new String();
		for (int row = 0; row < 9; row++) {
			if (row % 3 == 0 && row != 0) {
				str += "------+-------+------\n";
			}
			for (int col = 0; col < 9; col++) {
				if (col % 3 == 0 && col != 0) {
					str += "| ";
				}
				int value = this.board[row][col].getValue();
				str += value == 0 ? "  " : value + " ";
			}
			str += "\n";
		}
		return str;
	}

	public static void main(String[] args) {
		Board board = new Board();
		String filename = "board.txt";
		if (args.length > 0)
			filename = args[0];
		board.read(filename);
		System.out.println(board + "");
	}
}
