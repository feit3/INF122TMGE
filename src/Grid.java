import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public abstract class Grid {
    private static final int SIZE = 10; // represents width and height of grid square (i.e. size of 10 = a 10x10 grid)

    public boolean isFull;
    protected Tile[][] board;
    public Player player;

    public abstract Set<Point> checkMatch(int row, int col);
    public abstract void initialize();
    public abstract void clearMatchedTiles(ArrayList<Tile> tiles);
    public abstract void placeTile(int x_coord, int y_coord);
    public abstract boolean verifyTilePos(int x_coord, int y_coord);
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

    public Player getPlayer() {
        return player;
    }
}