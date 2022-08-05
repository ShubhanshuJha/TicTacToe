import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;
import static java.lang.System.out;

public class HumanPlayer extends Board implements Player {
	char mark;
	String name;
	private final BufferedReader br;
	HumanPlayer(String name, char mark, char[] board) {
		super(board);
		this.mark = mark;
		this.name = name;
		for (int i = 0; i < 9; i++)
			availableMoves.add(i);
		br = new BufferedReader(new InputStreamReader(in));
	}
	HumanPlayer(char mark, char[] board) {
		super(board);
		this.mark = mark;
		name = mark == 'X' ? "Player1" : "Player2";
		for (int i = 0; i < 9; i++)
			availableMoves.add(i);
		br = new BufferedReader(new InputStreamReader(in));
	}

	private int getInput() {
		out.print("Enter the cell number (1-9) >> ");
		try {
			return Integer.parseInt(br.readLine().trim());
		} catch (IOException ioe) {
			return 0;
		}
	}

	@Override
	public void move() {
		int cell = getInput();
		while (cell < 1 || cell > 9) {
			out.println("Please enter value from 1 to 9 only.");
			cell = getInput();
		}
		while (!availableMoves.isEmpty() && board[cell - 1] != ' ') {
			out.println("Cell is already occupied!!");
			cell = getInput();
		}
		availableMoves.remove(cell - 1);
		board[cell - 1] = this.mark;
		moveCount++;
		int r = (cell - 1) / 3, c = (cell - 1) % 3;
		out.println(name + " played on " + String.format("cell[%d][%d].", r, c));
		out.println(this);
		if (hasWon()) {
			out.println(name + " has won the game.");
			Board.moveCount = INF;
			Board.won = true;
		}
	}

	@Override
	public boolean hasWon() {
		char[] b = board;
		return (b[0] == mark && b[1] == mark && b[2] == mark) || (b[3] == mark && b[4] == mark && b[5] == mark)
			|| (b[6] == mark && b[7] == mark && b[8] == mark) || (b[0] == mark && b[3] == mark && b[6] == mark)
			|| (b[1] == mark && b[4] == mark && b[7] == mark) || (b[2] == mark && b[5] == mark && b[8] == mark)
			|| (b[0] == mark && b[4] == mark && b[8] == mark) || (b[2] == mark && b[4] == mark && b[6] == mark);
	}

	public String toString() {
		return super.getMove();
	}
}