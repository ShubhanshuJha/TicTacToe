import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import static java.lang.System.*;
import java.util.stream.*;

public class Game {
	final BufferedReader input;
	static final char[] marks = {'X', 'O'};
	volatile char[] gameBoard;
	Player[] players;

	final String[] choice = {"easy easy", "easy hard", "easy medium", "easy user", "hard easy", "hard hard", "hard medium", "hard user", "medium easy", "medium hard", "medium medium", "medium user", "user easy", "user hard", "user medium", "user user"};

	final HashMap<String, Integer> map;

	Game() {
		gameBoard = new char[9];
		Arrays.fill(gameBoard, ' ');
		input = new BufferedReader(new InputStreamReader(in));
		players = new Player[2];

		map = new HashMap<>(){
			{
				for (int i = 1; i <= choice.length; i++)
					put(choice[i - 1], i);
			}
		};
	}

	public void printModes() {
		out.println("\n----- Choose Game Mode -----\nSelect Your Game Choice Mode From Below:");
		for (int i = 1; i <= choice.length; i++)
			out.println("\t" + i + ". " + choice[i - 1]);
	}

	private int getInput() {
		out.print("Enter The Choice >> ");
		try {
			return Integer.parseInt(input.readLine().trim());
		} catch (IOException ioe) {
			return 0;
		}
	}

	public String getName() {
		out.print("Enter Player Name >> ");
		return input();
	}

	public String input() {
		try {
			return input.readLine().trim();
		} catch (IOException ioe) {
			return null;
		}
	}

	public int getCommand() {
		printModes();
		out.print("Enter Command >> ");
		String input = input().trim();
		if (input.length() <= 2 && Character.isDigit(input.charAt(0)))
			return Integer.parseInt(input);
		int opt = 0;
		for (int i = 1; i <= choice.length; i++)
			if (choice[i - 1].equalsIgnoreCase(input)) {
				opt = i;
				break;
			}
		return opt;
	}

	public static String upperCaseAllFirstCharacter(final String words) {
	    return Stream.of(words.trim().split("\\s"))
	    .filter(word -> word.length() > 0)
	    .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
	    .collect(Collectors.joining(" "));
	}

	public void start() {
		// int gameChoice = getInput();
		// while (gameChoice < 1 || gameChoice > choice.length)
		// 	gameChoice = getInput();
		int gameChoice = getCommand();
		while (gameChoice < 1 || gameChoice > choice.length) {
			out.println("Wrong Selection!!\n");
			gameChoice = getCommand();
		}
		out.println("------- " + upperCaseAllFirstCharacter(choice[gameChoice - 1]) + " Mode-------");
		switch (gameChoice) {
			case 1 -> {
				players[0] = new EasyPlayer(marks[0], gameBoard);
				players[1] = new EasyPlayer(marks[1], gameBoard);
			}
			case 2 -> {
				players[0] = new EasyPlayer(marks[0], gameBoard);
				players[1] = new HardPlayer(marks[1], gameBoard);
			}
			case 3 -> {
				players[0] = new EasyPlayer(marks[0], gameBoard);
				players[1] = new MediumPlayer(marks[1], gameBoard);
			}
			case 4 -> {
				players[0] = new EasyPlayer(marks[0], gameBoard);
				players[1] = new HumanPlayer(getName(), marks[1], gameBoard);
			}
			case 5 -> {
				players[0] = new HardPlayer(marks[0], gameBoard);
				players[1] = new EasyPlayer(marks[1], gameBoard);
			}
			case 6 -> {
				players[0] = new HardPlayer(marks[0], gameBoard);
				players[1] = new HardPlayer(marks[1], gameBoard);
			}
			case 7 -> {
				players[0] = new HardPlayer(marks[0], gameBoard);
				players[1] = new MediumPlayer(marks[1], gameBoard);
			}
			case 8 -> {
				players[0] = new HardPlayer(marks[0], gameBoard);
				players[1] = new HumanPlayer(getName(), marks[1], gameBoard);
			}
			case 9 -> {
				players[0] = new MediumPlayer(marks[0], gameBoard);
				players[1] = new EasyPlayer(marks[1], gameBoard);
			}
			case 10 -> {
				players[0] = new MediumPlayer(marks[0], gameBoard);
				players[1] = new HardPlayer(marks[1], gameBoard);
			}
			case 11 -> {
				players[0] = new MediumPlayer(marks[0], gameBoard);
				players[1] = new MediumPlayer(marks[1], gameBoard);
			}
			case 12 -> {
				players[0] = new MediumPlayer(marks[0], gameBoard);
				players[1] = new HumanPlayer(getName(), marks[1], gameBoard);
			}
			case 13 -> {
				players[0] = new HumanPlayer(getName(), marks[0], gameBoard);
				players[1] = new EasyPlayer(marks[1], gameBoard);
			}
			case 14 -> {
				players[0] = new HumanPlayer(getName(), marks[0], gameBoard);
				players[1] = new HardPlayer(marks[1], gameBoard);
			}
			case 15 -> {
				players[0] = new HumanPlayer(getName(), marks[0], gameBoard);
				players[1] = new MediumPlayer(marks[1], gameBoard);
			}
			case 16 -> {
				players[0] = new HumanPlayer(getName(), marks[0], gameBoard);
				players[1] = new HumanPlayer(getName(), marks[1], gameBoard);
			}
			default -> {
				out.println("Wrong Input Format!!");
				return;
			}
		}
		Board.println();
	}

	public void play() {
		for (int i = 0; i < 2; i++) {
			players[i].move();
			out.println(players[i]);
		}
	}

	public void play(Player p) {
		p.move();
		// out.println(p);
	}

	public boolean isFinished() {
		return Board.moveCount >= 9;
	}
}