import java.util.List;
import java.util.Set;

public abstract class Grid {

    // I was thinking about those can be final type
    // (we as developer set the number, kinda like 'hardcoding' default width/height)
    public static int DEFAULT_WIDTH;
    public static int DEFAULT_HEIGHT;

    public boolean isFull;
    protected List<List<Tile>> board;

    public abstract Set<Point> checkMatch(int row, int col);
    public abstract boolean clearMatchedTiles(Set<Point> matchedTiles);
    public abstract void placeTile();
    public abstract boolean verifyTilePos();
    public abstract void moveTile();
    public abstract Tile[][] getBoard();
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
}