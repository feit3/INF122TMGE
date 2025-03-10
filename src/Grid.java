import java.util.List;

public abstract class Grid {

    // I was thinking about those can be final type
    // (we as developer set the number, kinda like 'hardcoding' default width/height)
    public static int DEFAULT_WIDTH;
    public static int DEFAULT_HEIGHT;

    public boolean isFull;
    protected List<List<Tile>> board;

    public abstract boolean checkMatch();
    public abstract void clearMatchedTiles();
    public abstract void placeTile();
    public abstract boolean verifyTilePos();
    public abstract void moveTile();
}