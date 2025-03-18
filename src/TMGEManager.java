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

    public static void playGame(Game game) {
        game.play();
    }

    public void addNewPlayer(String username) {
        Player newPlayer = new Player(username);
        allPlayers.add(newPlayer);
    }

    public void addNewGame(String gameName) {
        Game newGame = gameFactory.createGame(gameName, allPlayers);
        allGames.add(newGame);
    }

    public void displayGames() {
        int counter = 1;
        for (Game game : allGames) {
            System.out.println(counter + ". " + game);
        }
    }
}