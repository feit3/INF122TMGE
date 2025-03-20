import java.util.*;

public class Connect4 extends Game {

    private final int POINT_THRESHOLD = 20;
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
    public void initialize() {
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
    public void displayInstructions() {
        System.out.println("RULES:");
        System.out.println("1. Players are given a board. Each player takes turns dropping a token on the board by selecting the corresponding column number.");

        System.out.println("\n2. A match occurs when a player connects 4 tokens in a row horizontally, vertically, or diagonally. The matched tokens are then cleared off the board.");

        System.out.println("\n3. After a match, any tokens that were placed above the 4 matched tokens are then dropped.");
        System.out.println("   It is possible for a match to occur for either player after this happens, so think ahead!");

        System.out.println("\n4. For every tile matched, players gain a point. The game continues until one of the players reaches a certain point threshold.\n");
    }

    @Override
    void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Connect 4!");
        displayInstructions();
        System.out.println("Game Started! First to " + POINT_THRESHOLD +  " points wins!");
        board.printBoard();

        while (!isGameOver()) {
            // Ask & Handle input
            System.out.printf("Now is %s's turn.\n", currentPlayer.getUsername());
            System.out.println("Points: " + currentPlayer.getPoints());
            System.out.println("Enter column:");
//            int row = scanner.nextInt();
            int col = scanner.nextInt()-1; // For user input friendly, our column index start at 1

            handleInput(-1, col);
        }

        System.out.println("Game Ended!");
    }

    @Override
    public boolean isGameOver() {

        // Check if any of player goes over 20 points (Cleared 20 tiles already)
        for (Player p : players)
        {
            if (p.getPoints() >= POINT_THRESHOLD) {
                System.out.println("Player " + p.getUsername() + " wins!");
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleInput(int row, int col) {

        Tile tile = generateNewTile();

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

        updateGameState();
    }

    @Override
    void updateGameState() {
        // Switch the player
        switchTurn();
    }

    @Override
    public String getGameName() {
        return "Connect 4";
    }
}
