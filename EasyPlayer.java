import java.util.Random;

import static java.lang.System.out;

public class EasyPlayer extends Board implements Player {
	char mark;
	String name;
	EasyPlayer(char mark, char[] board) {
		super(board);
		this.mark = mark;
		name = mark == 'X' ? "Player1" : "Player2";
		for (int i = 0; i < 9; i++)
			availableMoves.add(i);
	}

	@Override
	public void move() {
		int cell = intVal(9);
		while (!availableMoves.isEmpty() && board[cell - 1] != ' ') {
			cell = intVal(9);
		}
		// out.println(cell);
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
	public int intVal(int n) {
		return new Random().nextInt(n) + 1;
	}
}