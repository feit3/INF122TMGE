import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class ConnectFour extends Game {

    private final ConnectFourGrid board;
    public ConnectFour() {
        // Create game board
        board = new ConnectFourGrid(6, 7);

        players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));

        // Start at player 1 (index 0)
        turnBelongsTo = 0;
    }

    public void switchTurn() {
        turnBelongsTo = ( turnBelongsTo + 1 ) % players.size();
    }

    public Point dropPiece(int col) throws Exception{
        for (int i = board.getRows()-1; i >= 0; i--) {
            if (board.getTile(i, col).getType().equals("Empty")) {

                String type = turnBelongsTo == 0 ? "Red" : "Yellow";
                String value = turnBelongsTo == 0 ? "R" : "Y";
                TokenTile tile = new TokenTile(type, value);
                board.setTile(i, col, tile);
                return new Point(i, col);
            }
        }
        throw new Exception("Column Full");
    }

//    @Override
//    public boolean checkWin() {
//        String winner = checkForWin();
//        if (!winner.equals("")) {
//            System.out.println("Player " + (winner.equals("R") ? "1" : "2") + " wins!");
//            board.printGrid();
//            return true;
//        }
//        return false;
//    }

    @Override
    void initialize() {

    }

    @Override
    Boolean checkGameOver() {
        return null;
    }

    @Override
    void handleInput(String input) {

    }

    @Override
    void updateScreen() {

    }

    @Override
    void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Game Started!");
        board.printBoard();

        while (true) {
            // Ask&Handle input
            System.out.printf("Now is %s's turn.\n", players.get(turnBelongsTo));
            System.out.println("Enter column:");
//            int row = scanner.nextInt();
            int col = scanner.nextInt()-1; // For user input friendly, our column index start at 1

            try {
                // Place the Tile
                Point p = dropPiece(col);
                board.printBoard();

                // Check Match
                Set<Point> matchedSet = board.checkMatch(p.row, p.col);

                boolean changed = false;
                if (matchedSet.size() > 0)
                {
                    // Clear the matched Tile, return true if cleared any tile
                    // clearMatchedTiles also automatically do gravity fall (inner call)
                    changed = board.clearMatchedTiles(matchedSet);
                    board.printBoard();
                }


                // Looping to check if there is any matched tiles after falling
                // Checking over whole board until board doesn't change again
                while (changed) {

                    changed = false;
                    for (int i = 0; i < board.getRows(); i++) {
                        for (int j = 0; j < board.getCols(); j++) {

                            // Once ever finding the matched tiles, set changed to true
                            // Will do another board check later
                            matchedSet = board.checkMatch(i, j);
                            if (matchedSet.size() > 0) {
                                board.clearMatchedTiles(matchedSet);
                                board.printBoard();
                                System.out.println("Wawa");
                                changed = true;
                            }
                        }
                    }
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage()); // Error message (Column Full)
                continue; // Do not update the board or switch turns, let current user input again
            }

            // Switch the player
            switchTurn();

//            checkWin();
        }
    }
}
