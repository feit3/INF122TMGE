import java.util.ArrayList;

public class GameFactory {
    public Game createGame(String gameName, ArrayList<Player> players) {
        if (gameName.equals("Connect4")) {
            return new Connect4(players);
        }
        if (gameName.equals("Battleship")) {
            return new Battleship(players);
        }
        System.out.println("Game: " + gameName + " does not exist");
        return null;
    }
}
