import java.util.Scanner;

public class Main {
    static TMGEManager manager = TMGEManager.getInstance();
    private static final int PLAYER_MINIMUM = 2;

    private static void addAllGamesToManager() {
        manager.addNewGame("Battleship");
        manager.addNewGame("Connect4");
    }


    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);

        while (manager.getAllPlayers().size() != PLAYER_MINIMUM) {
            System.out.println("Player 1 Username: ");
            String p1_username = sc.nextLine();

            System.out.println("Player 2 Username: ");
            String p2_username = sc.nextLine();

            // Add players and games to the manager
            manager.addNewPlayer(p1_username);
            manager.addNewPlayer(p2_username);
        }

        // Add the games into the application after the players have been established
        addAllGamesToManager();


        while (true) {
            System.out.println("Welcome to the Tile Matching Game Environment (TMGE)!");
            System.out.println("Here is the list of games you can play:");
            manager.displayGames();

            System.out.println("\nSelect a Game to Play");
            String gameName = sc.nextLine();

            if (gameName.equalsIgnoreCase("q") || gameName.equalsIgnoreCase("exit")) {
                break;
            }
            Game game = manager.checkGameExists(gameName);
            if (game == null) {
                System.out.println("Game " + gameName + " not found\n");
            } else {
                manager.playGame(game);
            }
        }

    }
}
