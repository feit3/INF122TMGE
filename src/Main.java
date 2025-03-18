import java.util.Scanner;

public class Main {
    static TMGEManager manager = TMGEManager.getInstance();

    private static void addAllGamesToManager() {
        manager.addNewGame("Battleship");
        manager.addNewGame("Connect4");
    }

    public static void main(String[] args) {
        System.out.println("Player 1 Username: ");
        Scanner sc =  new Scanner(System.in);
        String p1_username = sc.nextLine();

        System.out.println("Player 2 Username: ");
        String p2_username = sc.nextLine();

        // Add players and games to the manager
        manager.addNewPlayer(p1_username);
        manager.addNewPlayer(p2_username);
        addAllGamesToManager();

        while (true) {
            System.out.println("Game Options: ");
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
