import java.util.ArrayList;

public class TMGEManager {

    private static TMGEManager TMGEManagerInstance;
    ArrayList<Player> allPlayers;
    ArrayList<Game> allGames;
    private final GameFactory gameFactory;


    private TMGEManager() {
        allPlayers = new ArrayList<>();
        allGames = new ArrayList<>();
        gameFactory = new GameFactory();
    }

    public static TMGEManager getInstance() {
        if (TMGEManagerInstance == null) {
            TMGEManagerInstance = new TMGEManager();
        }
        return TMGEManagerInstance;
    }

    public Game checkGameExists(String gameName) {
        for (Game game : allGames) {
            if (game.getGameName().equalsIgnoreCase(gameName)) {
                return game;
            }
        }
        return null;
    }

    public void playGame(Game game) {
        game.play();
    }

    public void addNewPlayer(String username) {
        // Check if username is empty
        if (username.isEmpty()) {
            System.out.println("Username is empty. Try again.\n");
            return;
        }
        // Check if player exists
        for (Player player : allPlayers) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                // player already exists, don't create a duplicate, and return
                System.out.println("Player already exists. Try again.\n");
                return;
            }
        }
        Player newPlayer = new Player(username);
        allPlayers.add(newPlayer);
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public void addNewGame(String gameName) {
        Game newGame = gameFactory.createGame(gameName, allPlayers);
        allGames.add(newGame);
    }

    public void displayGames() {
        int counter = 1;
        for (Game game : allGames) {
            System.out.println(counter++ + ". " + game.getGameName());
        }
        System.out.println(counter + ". Exit (Q)");
    }

}