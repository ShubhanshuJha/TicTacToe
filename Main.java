import static java.lang.System.*;

public class Main {
	public static void main(String[] args) throws Exception {
		out.println("\tWelcome To Tic-Tac-Toe Game");
		for (int i = 0; i < 15; i++) out.print("---");
		Game game = new Game();

		out.println("\nEnter \"START\" To Play The Game\nAnd \"EXIT\" To Stop The Game.");
		out.print("Enter Command >> ");
		String line = game.input();
		while (!line.equalsIgnoreCase("start")) {
			if (line.equalsIgnoreCase("exit"))
				out.println("Game Not Started Yet!!");
			else
				out.println("Command Not Found!!");
			out.println("\nEnter \"START\" To Play The Game\nAnd \"EXIT\" To Stop The Game.");
			out.print("Enter Command >> ");
			line = game.input();
		}

		while (!line.equalsIgnoreCase("exit")) {
			game.start();
			int idx = 0;
			while (!game.isFinished()) {
				game.play(game.players[idx]);
				idx = idx == 1 ? 0 : 1;
				Thread.sleep(500);
			}
			if (!Board.won) {
				out.println("Game Draw.");
			}

			out.println("\nEnter \"START\" To Play The Game\nAnd \"EXIT\" To Stop The Game.");
			out.print("Enter Command >> ");
			line = game.input().trim();

			while (!line.equalsIgnoreCase("start") && !line.equalsIgnoreCase("exit")) {
				out.println("Command Not Found!!");
				out.println("\nEnter \"START\" To Play The Game\nAnd \"EXIT\" To Stop The Game.");
				out.print("Enter Command >> ");
				line = game.input().trim();
			}
			if (line.equalsIgnoreCase("start"))
				game = new Game();
		}
		out.println("\nBye Bye.");
	}
}
