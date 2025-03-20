import java.util.ArrayList;
import java.util.Scanner;

public class Battleship extends Game {
    public Battleship(ArrayList<Player> incomingPlayers) {
        players = incomingPlayers; // Passed in from TMGEManager
        gameBoards = new ArrayList<>();

        // for every player entering the game, create their own associated gameboard
        for (Player player : players) {
            BattleshipGrid battleshipGameBoard = new BattleshipGrid(player);
            gameBoards.add(battleshipGameBoard);
        }

        currentPlayer = players.get(0); // first player starts
    }

    @Override
    public String getGameName() {
        return "Battleship";
    }

    @Override
    public void displayInstructions() {
        System.out.println("RULES:");
        System.out.println("1. Each player has their own board, each containing 4 battleships.");
        System.out.println("  - Pieces of a Ship are indicated as 'O' on the board.");
        System.out.println("  - Each ship is between 2 to 4 Ship Pieces long, either vertical or horizontal.");
        System.out.println("  - All other empty tiles on the board are considered water.");

        System.out.println("\n2. Players take turns guessing the coordinates of the Ship Pieces on an opposing player's board.");
        System.out.println("  - MISS --> Player's coordinates were water.");
        System.out.println("  - HIT --> Player's coordinates was a Ship Piece ('O').");

        System.out.println("\n3. When a player successfully makes a HIT,");
        System.out.println("  - The Player that GOT HIT loses a ship piece (indicated as 'O' turns into 'X').");
        System.out.println("    - If that was the last Ship Piece of one of their Ships, then that Ship is destroyed and cleared off the board.");
        System.out.println("  - The player that MADE THE HIT then gains an extra ship on their board (like reinforcements). This will make it harder for them to lose!");

        System.out.println("\n4. The last player left standing with ships on their board is the winner! In other words, if everyone else's ships but theirs are destroyed, they win!\n");
    }

    @Override
    public void play() {
        System.out.println("Welcome to Battleship!");
        displayInstructions();
        initialize();

        // for reading user input
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            System.out.println("Player " + currentPlayer.getUsername() + "'s turn. Here is your Board:");
            displayBoard();

            System.out.println("\nEnter coordinates for your attack (x, y):");
            String coordinates = scanner.nextLine();
            // Check if input matches the pattern "x,y" where x and y are digits
            if (!coordinates.matches("\\d+,\\d+")) {
                System.out.println("Invalid format! Please enter coordinates as 'x,y' (e.g. '3,5').\n");
                continue;
            }

            // Input is valid, parse and continue game
            String[] coordinatesArray = coordinates.split(",");
            int x = Integer.parseInt(coordinatesArray[0]);
            int y = Integer.parseInt(coordinatesArray[1]);
            handleInput(x, y);
        }
    }


    public void displayBoard() {
        for (Grid board : gameBoards) {
            if (board.getPlayer() == currentPlayer) {
                System.out.println(board);
            }
        }
    }

    @Override
    // initialize the game boards
    public void initialize() {
        for (Grid board : gameBoards) {
            board.initialize();
        }
    }

    @Override
    public boolean isGameOver() {
        Player possibleWinner = null;
        int remainingPlayers = 0;

        for (Grid board : gameBoards) {
            if (!((BattleshipGrid) board).allShipsSunk()) {
                remainingPlayers++; // player board is not empty, they are still in the game
                possibleWinner = board.getPlayer(); // this player's board is not empty, could be considered winner IF another player's board is empty
            }
        }

        if (remainingPlayers == 1) {
            // Only one player left with ships still on their board, they are the winner
            winner = possibleWinner;
            return true;
        }
        return false;
    }

    @Override
    public void handleInput(int x_coord, int y_coord) {
        // Grab the opposing player's board and attempt a guess at their battleship locations
        int opposingPlayer = (players.indexOf(currentPlayer) + 1) % players.size();
        BattleshipGrid opponentGrid = (BattleshipGrid) gameBoards.get(opposingPlayer);
        int num_opp_ships_BEFORE = opponentGrid.getShips().size();
        boolean tilePlaced = opponentGrid.placeTile(x_coord, y_coord);
        if (!tilePlaced) {
            return;
        }
        int num_opp_ships_AFTER = opponentGrid.getShips().size();

        // Check if a ship has been hit, generate a new battleship on the current player's board if so
        if (num_opp_ships_BEFORE > num_opp_ships_AFTER) {
            int currentPlayerIdx = players.indexOf(currentPlayer) % players.size();
            BattleshipGrid currentPlayerGrid = (BattleshipGrid) gameBoards.get(currentPlayerIdx);
            currentPlayerGrid.generateBattleShip();
        }

        updateGameState(); // check if the game is over, move on to next player if not
    }

    @Override
    public void updateGameState() {
        if (!isGameOver()) {
            int nextPlayer = (players.indexOf(currentPlayer) + 1) % players.size();
            currentPlayer = players.get(nextPlayer);
        } else {
            System.out.println("Game Over!");
            System.out.println("Player '" + winner.getUsername() + "''s is the Winner!");
        }
    }
}
