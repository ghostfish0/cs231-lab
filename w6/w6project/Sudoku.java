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

	public Cell findNextCellTrailing() {
		for (int row = 0; row < this.board.getRows(); row++) {
			for (int col = 0; col < this.board.getCols(); col++) {
				if (this.board.get(row, col).getValue() == 0) {
					int value = findNextValue(this.board.get(row, col));
					if (value > 0) {
						this.board.set(row, col, value);
						return this.board.get(row, col);
					} else
						return null;
				}
			}
		}
		return null;
	}

	public boolean solve() {
		// return dfs(); // No solution found
		// return solveIterativeDFS();
		return solveIterative();
	}

	public boolean solveIterative() {
		Stack<Cell> stack = new Stack<>();

		// Count unspecified cells
		while (stack.size() < 81) {
			Cell next = findNextCellTrailing();

			// Try to find a valid next cell by backtracking
			while (next == null && !stack.isEmpty()) {
				Cell popped = stack.pop();
                System.out.println("------------ begin ");
                System.out.println("popped: " + popped);
                System.out.println("at popped in board: " + this.board.get(popped.getRow(), popped.getCol()));
                System.out.println(this.board);
				int nextVal = findNextValue(popped);
				board.set(popped.getRow(), popped.getCol(), nextVal);
                System.out.println(this.board.get(popped.getRow(), popped.getCol()));
                System.out.println("------------ end ");
				if (nextVal > 0) {
					next = this.board.get(popped.getRow(), popped.getCol());
				}
			}

			// If next is still null, we've exhausted all possibilities
			if (next == null) {
                System.out.println("no game :(");
				return false;
			} else {
				stack.push(next);
			}
		}

		return true;
	}
	public boolean solveIterativeDFS() {
		Stack<SolverState> stack = new Stack<>();
		Cell current = findNextCell();

		// If the board is already full, check if it's solved
		if (current == null) {
			return true;
		}

		// Initialize with first empty cell and value 1
		stack.push(new SolverState(current, 1));

		while (!stack.isEmpty()) {
			SolverState state = stack.peek();
			current = state.cell;

			// Try values from the last attempted value up to 9
			boolean foundValid = false;
			while (state.currentValue <= 9) {
				if (board.validValue(current.getRow(), current.getCol(),
					     state.currentValue)) {
					board.set(current.getRow(), current.getCol(),
					          state.currentValue);
					foundValid = true;
					break;
				}
				state.currentValue++;
			}

			if (!foundValid) {
				// No valid value found for current cell, backtrack
				board.set(current.getRow(), current.getCol(), 0);
				stack.pop();
				if (!stack.isEmpty()) {
					// Increment the value for the previous cell to try next
					// possibility
					stack.peek().currentValue++;
				}
				continue;
			}

			// Found a valid value, move to next cell
			Cell next = findNextCell();
			if (next == null) {
				// No more empty cells means we've found a solution
				return true;
			}

			// Push next cell to stack with initial value 1
			stack.push(new SolverState(next, 1));
		}

		return false;
	}

	// Helper class to keep track of state for each cell in the stack
	private static class SolverState {
		Cell cell;
		int currentValue;

		SolverState(Cell cell, int currentValue) {
			this.cell = cell;
			this.currentValue = currentValue;
		}
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
