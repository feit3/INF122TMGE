import java.util.ArrayList;

public class ShipPiece extends Tile {
    int length;
    ArrayList<Integer> coordinates;
    ShipPieceState state;

    public ShipPiece(int x, int y) {
        coordinates = new ArrayList<>();
        coordinates.add(x);
        coordinates.add(y);
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public boolean isHit() {
        return state == ShipPieceState.HIT;
    }

    public ShipPieceState getState() {
        return state;
    }

    public void changeState(ShipPieceState newState) {
        state = newState;
    }




}
