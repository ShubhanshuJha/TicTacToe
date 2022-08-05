import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class MediumPlayer extends Board implements Player {
	char mark;
	String name;
	MediumPlayer(char mark, char[] board) {
		super(board);
		this.mark = mark;
		name = mark == 'X' ? "Player1" : "Player2";
		for (int i = 0; i < 9; i++)
			availableMoves.add(i);
	}

	@Override
	public void move() {
		int cell;
		if (moveCount <= 2) {
			cell = intVal(9);
			while (!availableMoves.isEmpty() && board[cell - 1] != ' ') {
				cell = intVal(9);
			}
		} else {
			List<Integer> sum = new LinkedList<>();
			int currSum = 0;
			for (int i = 1; i <= 9; i++) {
				char cellMark = board[i - 1];
				currSum += cellMark == this.mark ? 10 : cellMark == ' ' ? 0 : -10;
				if (i % 3 == 0) {
					sum.add(currSum);
					currSum = 0;
				}
			}
			for (int i = 1; i <= 3; i++) {
				char cellMark1 = board[i - 1],
					cellMark2 = board[i + 2],
					cellMark3 = board[i + 5];
				currSum = cellMark1 == this.mark ? 10 : cellMark1 == ' ' ? 0 : -10;
				currSum += cellMark2 == this.mark ? 10 : cellMark2 == ' ' ? 0 : -10;
				currSum += cellMark3 == this.mark ? 10 : cellMark3 == ' ' ? 0 : -10;
				sum.add(currSum);
				
			}

			// 0, 4, 8
			sum.add((board[0] == this.mark ? 10 : board[0] == ' ' ? 0 : -10)
				+ (board[4] == this.mark ? 10 : board[4] == ' ' ? 0 : -10)
				+ (board[8] == this.mark ? 10 : board[8] == ' ' ? 0 : -10));
			// 2, 4, 6
			sum.add((board[2] == this.mark ? 10 : board[2] == ' ' ? 0 : -10)
				+ (board[4] == this.mark ? 10 : board[4] == ' ' ? 0 : -10)
				+ (board[6] == this.mark ? 10 : board[6] == ' ' ? 0 : -10));
			// out.println(sum);
			int posIdx = -1, negIdx = -1;
			for (int i = 0; i < sum.size(); i++){
				if (posIdx == -1 && sum.get(i) == 20) {
					posIdx = i;
				}
				if (negIdx == -1 && sum.get(i) == -20) {
					negIdx = i;
				}
			}
			if (posIdx != -1) {
				switch (posIdx) {
					case 0 -> {
						cell = 0;
						while (cell < 3 && board[cell] != ' ') cell++;
					}
					case 1 -> {
						cell = 3;
						while (cell < 6 && board[cell] != ' ') cell++;
					}
					case 2 -> {
						cell = 6;
						while (cell < 9 && board[cell] != ' ') cell++;
					}
					case 3 -> {
						cell = 0;
						while (cell < 7 && board[cell] != ' ') cell += 3;
					}
					case 4 -> {
						cell = 1;
						while (cell < 8 && board[cell] != ' ') cell += 3;
					}
					case 5 -> {
						cell = 2;
						while (cell < 9 && board[cell] != ' ') cell += 3;
					}
					case 6 -> {
						cell = 0;
						while (cell < 9 && board[cell] != ' ') cell += 4;
					}
					case 7 -> {
						cell = 2;
						while (cell < 7 && board[cell] != ' ') cell += 2;
					}
					default -> cell = 0;
				}
				cell++;
			} else if (negIdx != -1) {
				switch (negIdx) {
					case 0 -> {
						cell = 0;
						while (cell < 3 && board[cell] != ' ') cell++;
					}
					case 1 -> {
						cell = 3;
						while (cell < 6 && board[cell] != ' ') cell++;
					}
					case 2 -> {
						cell = 6;
						while (cell < 9 && board[cell] != ' ') cell++;
					}
					case 3 -> {
						cell = 0;
						while (cell < 7 && board[cell] != ' ') cell += 3;
					}
					case 4 -> {
						cell = 1;
						while (cell < 8 && board[cell] != ' ') cell += 3;
					}
					case 5 -> {
						cell = 2;
						while (cell < 9 && board[cell] != ' ') cell += 3;
					}
					case 6 -> {
						cell = 0;
						while (cell < 9 && board[cell] != ' ') cell += 4;
					}
					case 7 -> {
						cell = 2;
						while (cell < 7 && board[cell] != ' ') cell += 2;
					}
					default -> cell = 0;
				}
				cell++;
			} else {
				cell = intVal(9);
				while (!availableMoves.isEmpty() && board[cell - 1] != ' ') {
					cell = intVal(9);
				}
			}
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