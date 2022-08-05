import java.util.HashSet;
public interface Player {
	int INF = 1000; // means the game is over now.
	HashSet<Integer> availableMoves = new HashSet<>();
	void move();
	boolean hasWon();
	String toString();
}