import java.util.ArrayList;

public abstract class Tile {
    ArrayList<Integer> coordinates;
    boolean filled;
    Color color;

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int x, int y) {
        coordinates.clear();
        coordinates.add(x);
        coordinates.add(y);
    }
}