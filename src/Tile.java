import java.util.ArrayList;

public abstract class Tile {
    private String type;   // The type of tile (e.g., "Ship", "Water", "RedPiece", etc.)
    private String value;  // Used for visuals or logic (e.g., "X", "O", "Red", "Yellow")

    public Tile(String type, String value) {
        this.type = type;
        this.value = value;
    }

    ArrayList<Integer> coordinates;
    boolean filled;
    Color color;
    String Icon;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Tile t) {
        return this.type.equals(t.type);
    }
    @Override
    public String toString() {
        return "[" + value + "]";
    }

    public void setCoordinates(int x, int y) {
        coordinates.clear();
        coordinates.add(x);
        coordinates.add(y);
    }
}
