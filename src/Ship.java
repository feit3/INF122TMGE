import java.util.ArrayList;

public class Ship {
    ArrayList<ShipPiece> shipStructure;
    int length;
    boolean isSunk;
    Orientation orientation;
    Color color;
    int id; // shares same id as all of its ShipPieces

    public Ship(ArrayList<ShipPiece> newShip, Orientation shipOrientation) {
        shipStructure = newShip;
        length = newShip.size();
        isSunk = false;
        orientation = shipOrientation;
    }

    public ArrayList<ShipPiece> getShipStructure() {
        return shipStructure;
    }

    public boolean checkSunk() {
        int sunkenPieces = 0;
        for (ShipPiece shipPiece : shipStructure) {
            if (shipPiece.isHit()) {
                sunkenPieces++;
            }
        }
        return sunkenPieces == length;
    }
}
