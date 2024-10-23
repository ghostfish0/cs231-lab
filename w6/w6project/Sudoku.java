import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Sudoku {
	public Board board;

	public Sudoku() { board = new Board(); }

	public int findNextValue(Cell c) {
		int curr = c.getValue() + 1;
		while (curr <= 9 && !board.validValue(c.getRow(), c.getCol(), curr)) {
			curr++;
		}
		return (curr <= 9) ? curr : 0;
	}

	public Cell findNextCell() {
		for (int row = 0; row < this.board.getRows(); row++) {
			for (int col = 0; col < this.board.getCols(); col++) {
				if (this.board.get(row, col).getValue() == 0) {
					return this.board.get(row, col);
				}
			}
		}
		return null;
	}

	public boolean solve() {
		return dfs(); // No solution found
	}

	public boolean solveIterative() {
		Stack<Cell> stack = new Stack<>();
		Cell current = findNextCell();

		while (current != null) {
			stack.push(current);
			current = findNextCell();
		}

		while (!stack.isEmpty()) {
			current = stack.pop();
			if (current.isLocked())
				continue;

			int nextValue = findNextValue(current);
			if (nextValue > 0) {
				this.board.set(current.getRow(), current.getCol(), nextValue);
				Cell next = findNextCell();
				if (next != null) {
					stack.push(current); // Push current cell back to try next
						     // value if needed
					stack.push(next);    // Push next empty cell
				} else {
					return true; // Solution found
				}
			} else {
				this.board.set(current.getRow(), current.getCol(),
				               0); // Reset the cell value for backtracking
			}
		}
		return false; // No solution found
	}

	public boolean dfs() {
		Cell next = findNextCell();
		if (next == null) {
			return true;
		}
		for (int i = 1; i < 10; i++) {
			if (!this.board.validValue(next.getRow(), next.getCol(), i))
				continue;
			this.board.set(next.getRow(), next.getCol(), i);
			if (dfs())
				return true;
			else
				this.board.set(next.getRow(), next.getCol(), 0);
		}
		return false;
	}

	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.solve();
		System.out.println(s.board);
	}
}
