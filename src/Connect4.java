import java.util.*;

public class Connect4 extends Game {

    private final int POINT_THRESHOLD = 10;
    private Map<String, Player> playerColors;
    private Connect4Grid board;

    public Connect4(ArrayList<Player> players) {
        this.players = players;
        initialize();
    }

    public void switchTurn() {
        currentPlayer = players.get( (players.indexOf(currentPlayer) + 1) % players.size());
    }

    private TokenTile generateNewTile()
    {
        String type = players.indexOf(currentPlayer) == 0 ? "Red" : "Yellow";
        String value = players.indexOf(currentPlayer) == 0 ? "R" : "Y";
        return new TokenTile(type, value);
    }

    public Point dropPiece(int col) {
        try {
            for (int i = board.getRows()-1; i >= 0; i--) {
                if (board.getTile(i, col).getType().equals("Empty")) {
                    return new Point(i, col);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    void initialize() {
        // Create game board
        board = new Connect4Grid(6, 7);

        // Start at player 1 (index 0)
        currentPlayer = players.get(0);

        // Map the color to the user, easy to track points
        playerColors = new HashMap<>();
        playerColors.put("Red", players.get(0));
        playerColors.put("Yellow", players.get(1));
    }

    @Override
    void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Game Started!");
        board.printBoard();

        while (!isGameOver()) {
            // Ask&Handle input
            System.out.printf("Now is %s's turn.\n", currentPlayer.getUsername());
            System.out.println("Enter column:");
//            int row = scanner.nextInt();
            String col = scanner.nextLine(); // For user input friendly, our column index start at 1

//            try {
            handleInput(generateNewTile(),"None", String.valueOf(col));
//            }
//            catch (Exception e) {
//                System.out.println(e.getMessage()); // Error message (Column Full)
//                continue; // Do not update the board or switch turns, let current user input again
//            }
        }

        System.out.println("Game Ended!");

    }

    @Override
    Boolean isGameOver() {

        // Check if any of player goes over 20 points (Cleared 20 tiles already)
        for (Player p : players)
        {
            if (p.getPoints() >= POINT_THRESHOLD) { return true; }
        }
        return false;
    }

    void handleInput(Tile tile, String r, String c) {
        int col = Integer.parseInt(c)-1; // User input friendly

        // Place the Tile
        Point p = dropPiece(col);
        if (p == null) return;

        board.setTile(p.x, p.y, tile);
        board.printBoard();

        // Check Match
        Set<Point> matchedSet = board.checkMatch(p.x, p.y);

        boolean changed = false;
        if (matchedSet.size() > 0)
        {
            // Clear the matched Tile, return true if cleared any tile
            // clearMatchedTiles also automatically do gravity fall (inner call)
            changed = board.clearMatchedTiles(matchedSet, playerColors);
            board.printBoard();
        }

        if (isGameOver()) { return; }

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
                        board.clearMatchedTiles(matchedSet, playerColors);
                        board.printBoard();
                        changed = true;
                    }
                }
            }
        }

        // Switch the player
        switchTurn();
    }

    @Override
    void updateGameState() {

    }
}
