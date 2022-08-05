import static java.lang.System.out;

public class HardPlayer extends Board implements Player {
	char mark;
	char oppMark;
	String name;
	HardPlayer(char mark, char[] board) {
		super(board);
		this.mark = mark;
		name = mark == 'X' ? "Player1" : "Player2";
		this.oppMark = mark == 'X' ? 'O' : 'X';
		for (int i = 0; i < 9; i++)
			availableMoves.add(i);
	}

	@Override
	public void move() {
		char[][] b = new char[3][3];
		for (int i = 0; i < 9; i++)
			b[i / 3][i % 3] = board[i];
		var bestMove = findBestMove(b);
		// out.println(bestMove);
		int cell = (bestMove.x * 3) + bestMove.y;
		// out.println("Cell: " + cell);
		availableMoves.remove(cell);
		board[cell] = this.mark;
		moveCount++;
		// int r = (cell - 1) / 3, c = (cell - 1) % 3;
		out.println(name + " played on " + String.format("cell[%d][%d].", bestMove.x, bestMove.y));
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

	private Boolean isMovesLeft(char[][] board) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == ' ')
					return true;
		return false;
	}
	private int evaluate(char[][] b) {
		// Checking in rows
	    for (int row = 0; row < 3; row++) {
	        if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
	            if (b[row][0] == this.mark)
	                return +10;
	            else if (b[row][0] == oppMark)
	                return -10;
	        }
	    }
	    // Checking in columns
	    for (int col = 0; col < 3; col++) {
	        if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
	            if (b[0][col] == this.mark)
	                return +10;
	 
	            else if (b[0][col] == oppMark)
	                return -10;
	        }
	    }
	    // Checking for Diagonals for X or O victory.
	    if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
	        if (b[0][0] == this.mark)
	            return +10;
	        else if (b[0][0] == oppMark)
	            return -10;
	    }
	 
	    if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
	        if (b[0][2] == this.mark)
	            return +10;
	        else if (b[0][2] == oppMark)
	            return -10;
	    }
	 
	    // Else if none of them have won then return 0
	    return 0;
    }

    private int minimax(char[][] board, int depth, Boolean isMax) {
	    int score = evaluate(board);
	 
	    // If Maximizer has won the game return his/her evaluated score
	    if (score == 10)
	        return score;
	 
	    // If Minimizer has won the game return his/her evaluated score
	    if (score == -10)
	        return score;
	 
	    // If there are no more moves and no winner then it is a tie
	    if (!isMovesLeft(board))
	        return 0;
	 
	    // If this maximizer's move
		int best;
		if (isMax) {
			best = -Player.INF;
	 
	        // Traverse all cells
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                // Check if cell is empty
	                if (board[i][j] == ' ') {
	                    // Make the move
	                    board[i][j] = this.mark;
	 
	                    // Call minimax recursively and choose
	                    // the maximum value
	                    best = Math.max(best, minimax(board, depth + 1, !isMax));
	 
	                    // Undo the move
	                    board[i][j] = ' ';
	                }
	            }
	        }
		}
	 
	    // If this minimizer's move
	    else {
			best = Player.INF;
	        // Traverse all cells
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                // Check if cell is empty
	                if (board[i][j] == ' ') {
	                    // Make the move
	                    board[i][j] = oppMark;
	 
	                    // Call minimax recursively and choose the minimum value
	                    best = Math.min(best, minimax(board, depth + 1, !isMax));
	                    // Undo move
	                    board[i][j] = ' ';
	                }
	            }
	        }
		}
		return best;
	}
	// This will return the best possible move for the player
	private Pair<Integer, Integer> findBestMove(char[][] board) {
	    int bestVal = -Player.INF;
	    Pair<Integer, Integer> bestMove = new Pair<>(-1, -1);
	 
	    // Traverse all cells, evaluate minimax function
	    // for all empty cells. And return the cell with optimal value.
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            // Check if cell is empty
	            if (board[i][j] == ' ') {
	                // Make the move
	                board[i][j] = this.mark;
	 
	                // compute evaluation function for this move.
	                int moveVal = minimax(board, 0, false);
	 
	                // Undo the move
	                board[i][j] = ' ';
	 
	                // If the value of the current move is more than the best value, then update best
	                if (moveVal > bestVal) {
	                    bestMove.update(i, j);
	                    bestVal = moveVal;
	                }
	            }
	        }
	    }
	    // System.out.printf("The value of the best Move " + "is : %d\n\n", bestVal);
	 
	    return bestMove;
	}

	public String toString() {
		return super.getMove();
	}
}

class Pair <X, Y> {
	X x;
	Y y;
	Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	public void update(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "X: " + x + ", Y: " + y;
	}
}
