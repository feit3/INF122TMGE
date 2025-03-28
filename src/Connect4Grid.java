import java.util.*;

public class Connect4Grid extends Grid{
    private int rows;
    private int cols;
    private Tile[][] board;

    public Connect4Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new Tile[rows][cols];

        initialize();
    }

    public Tile getTile(int row, int col) {
        return board[row][col];
    }

    public void setTile(int row, int col, Tile tile) {
        board[row][col] = tile;
    }

    public Set<Point> checkMatch(int row, int col) {
        Set<Point> matched = new HashSet<>();

        // Check all directions
        checkDirection(row, col, 0, 1, matched);    // Horizontal
        checkDirection(row, col, 1, 0, matched);    // Vertical
        checkDirection(row, col, 1, 1, matched);    // Diagonal ↘
        checkDirection(row, col, 1, -1, matched);   // Diagonal ↙
    
        return matched.size() >= 4 ? matched : Collections.emptySet();
    }


    // Helper to check in both directions and collect matched tiles
    private void checkDirection(int row, int col, int rowDir, int colDir, Set<Point> matched) {
        List<Point> temp = new ArrayList<>();
        temp.add(new Point(row, col));  // Start from current tile

        // Positive direction
        collectConsecutive(row, col, rowDir, colDir, temp);

        // Negative direction
        collectConsecutive(row, col, -rowDir, -colDir, temp);

        // Only if one line got 4 tiles matched then we return these back
        if (temp.size() >= 4) {
            matched.addAll(temp);
        }
    }

    private void collectConsecutive(int row, int col, int deltaRow, int deltaCol, List<Point> temp) {
        Tile currentTile = board[row][col];

        // Don't check for empty tile XD
        if (verifyTilePos(row, col, "empty")) return;

        int r = row + deltaRow;
        int c = col + deltaCol;

        while (r >= 0 && r < rows && c >= 0 && c < cols && currentTile.equals(board[r][c])) {
            temp.add(new Point(r, c));
            r += deltaRow;
            c += deltaCol;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean clearMatchedTiles(Set<Point> matchedTiles, Map<String, Player> playerColor) {
        if (matchedTiles.size() == 0) { return false; } // board has not changed
        for (Point pos : matchedTiles) {
            // Every tile cleared, add 1 point to corresponding player
            // In this method, cleared tile could be either color
            playerColor.get(board[pos.x][pos.y].getType()).addPoints(1);

            board[pos.x][pos.y] = new TokenTile("Empty", " ");  // Clear tile in this way XD
        }
//        printBoard();
        applyTileFall();

        return true;    // Represent the board has changed, check match again
    }

    @Override
    public void initialize() {
        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], new TokenTile("Empty", " "));
        }
    }

    @Override
    public boolean checkMatch(Tile tile) {
        return false;
    }

    @Override
    public void clearMatchedTiles(ArrayList<Tile> tiles) {

    }

    @Override
    public boolean placeTile(int x_coord, int y_coord) {

        return false;
    }

    @Override
    public boolean verifyTilePos(int row, int col, String check) {
        switch (check) {
            case "empty" -> {
                return board[row][col].getType().equals("Empty");
            }
        }
        return false;
    }

    public void applyTileFall() {
        // Apply gravity for each column
        for (int col = 0; col < cols; col++) {
            int writeRow = rows - 1;

            for (int row = rows - 1; row >= 0; row--) {
                if (!verifyTilePos(row, col, "empty")) {

                    // Swap these two tile if they are not same
                    if (writeRow != row) {
                        Tile temp = board[writeRow][col];
                        board[writeRow][col] = board[row][col];
                        board[row][col] = temp;
                    }
                    writeRow--;
                }
            }
        }
    }

    public void printBoard() {
        for ( Tile[] tile_List : getBoard() ) {
            for (Tile t : tile_List) {
                System.out.printf("%s ", t);
            }
            System.out.println();
        }
        for (int col = 0; col < getBoard()[0].length; col++) {
            System.out.printf(" %d  ", col+1);
        }
        System.out.println();
    }

    @Override
    public Tile[][] getBoard() {
        return board;
    }
}
