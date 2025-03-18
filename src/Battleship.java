import java.util.ArrayList;

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
    public void play() {

    }

    @Override
    // initialize the game wi
    public void initialize() {
        for (Grid board : gameBoards) {
            board.initialize();
        }
    }

    @Override
    public Boolean isGameOver() {
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
    public void handleInput(Tile shipPiece, String x, String y) {
        int x_coordinate = Integer.parseInt(x);
        int y_coordinate = Integer.parseInt(y);

        // Grab the opposing player's board and attempt a guess at their battleship locations
        int opposingPlayer = (players.indexOf(currentPlayer) + 1) % players.size();
        BattleshipGrid opponentGrid = (BattleshipGrid) gameBoards.get(opposingPlayer);
        opponentGrid.placeTile(x_coordinate, y_coordinate);
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
