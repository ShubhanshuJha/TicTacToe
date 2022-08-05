import static java.lang.System.*;

public class Board {
	volatile static char[] board;
	static int moveCount;
	volatile static boolean won;
	Board(char[] board) {
		Board.board = board;
		moveCount = 0;
		won = false;
	}

	public void printBoard() {
		out.print(getMove());
	}

	public String getMove() {
		/*
			-------------
			| X | O | X |
			-------------
			| X | O | X |
			-------------
			| X | O | X |
			-------------
		*/
		StringBuilder currMove = new StringBuilder("-------------\n| ");
		for (int i = 0; i < 9; i++) {
			if (i > 0 && i % 3 == 0) currMove.append("\n-------------\n| ");
			currMove.append(board[i]).append(" | ");
			if ((i + 1) % 3 == 0) currMove.deleteCharAt(currMove.length() - 1);
		}
		currMove.append("\n-------------\n");
		return currMove.toString();
	}
	public static void println() {
		/*
			-------------
			| X | O | X |
			-------------
			| X | O | X |
			-------------
			| X | O | X |
			-------------
		*/
		StringBuilder currMove = new StringBuilder("-------------\n| ");
		for (int i = 0; i < 9; i++) {
			if (i > 0 && i % 3 == 0) currMove.append("\n-------------\n| ");
			currMove.append(board[i]).append(" | ");
			if ((i + 1) % 3 == 0) currMove.deleteCharAt(currMove.length() - 1);
		}
		currMove.append("\n-------------\n");
		out.println(currMove);
	}
}