import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public abstract class Grid {
    private static final int SIZE = 10; // represents width and height of grid square (i.e. size of 10 = a 10x10 grid)

    public boolean isFull;
    protected Tile[][] board;
    public Player player;


    public abstract void initialize();
    public abstract boolean checkMatch(Tile tile);
    public abstract void clearMatchedTiles(ArrayList<Tile> tiles);
    public abstract boolean placeTile(int x_coord, int y_coord);
    public abstract boolean verifyTilePos(int x_coord, int y_coord, String check);
    public abstract Tile[][] getBoard();

    public Player getPlayer() {
        return player;
    }
}