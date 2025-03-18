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

        currentPlayer = players.getFirst(); // first player starts
    }

    @Override
    public String getGameName() {
        return "Battleship";
    }

    @Override
    public void play() {
        System.out.println("Welcome to Battleship!");
        initialize();

        // for reading user input
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            System.out.println("Player " + currentPlayer.getUsername() + "'s turn. Here is your Board:");
            displayBoard();

            System.out.println("\nEnter coordinates for your attack (x, y):");
            String coordinates = scanner.nextLine();
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
    // initialize the game wi
    public void initialize() {
        for (Grid board : gameBoards) {
            board.initialize();
        }
    }

    @Override
    public boolean isGameOver() {
        Player possibleWinner = null;
        int remainingPlayers = players.size();

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
        opponentGrid.placeTile(x_coord, y_coord);
        updateGameState(); // move to next player
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
