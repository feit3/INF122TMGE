import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShipPiece extends Tile {
    int length;
    ShipPieceState state;
    Map<ShipPieceState, String> stateIcons;

    public ShipPiece(int x, int y) {
        super("ShipPiece", "S");
        coordinates = new ArrayList<>();
        coordinates.add(x);
        coordinates.add(y);

        state = ShipPieceState.SAFE;

        stateIcons = new HashMap<>();
        stateIcons.put(ShipPieceState.HIT, "X");
        stateIcons.put(ShipPieceState.SAFE, "O");
        stateIcons.put(ShipPieceState.WATER, " ");
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

    public String getStateIcon(ShipPieceState state) {
        return stateIcons.get(state);
    }




}
