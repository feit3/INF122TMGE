import java.util.ArrayList;

public class Connect4Grid extends Grid {
    public Connect4Grid() {}

    @Override
    public void initialize() {}

    @Override
    public boolean checkMatch(Tile tile) {return true;}

    @Override
    public void clearMatchedTiles(ArrayList<Tile> tiles) {}

    @Override
    public void placeTile(int x_coord, int y_coord) {}

    @Override
    public boolean verifyTilePos(int x_coord, int y_coord, String check) {return true;}

}
