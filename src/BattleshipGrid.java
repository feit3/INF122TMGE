import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BattleshipGrid extends Grid {
    private static final int SIZE = 10;
    private ArrayList<Ship> ships;

    public BattleshipGrid(Player owner) {
        board = new ShipPiece[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = new ShipPiece(row, col);
            }
        }
        ships = new ArrayList<>();
        player = owner;
    }

    @Override
    public void initialize() {
        // generate 4 battleships in random areas on the grid, these ships can either be vertical or horizontal
        int totalShips = 0;
        while (totalShips < 4) {
            generateBattleShips();
            totalShips++;
        }
    }

//    private Ship buildShip(Orientation orientation, int size) {
//
//    }

    private boolean canPlaceShip(int x_coord, int y_coord, int size, Orientation orientation) {
        for (int i = 0; i < size; i++) {
            int newX = orientation == Orientation.HORIZONTAL ? x_coord + i : x_coord;
            int newY = orientation == Orientation.VERTICAL ? y_coord + i : y_coord;

            if (verifyTilePos(newX, newY, "bounds") || verifyTilePos(newX, newY, "occupied")) {
                return false;
            }
        }
        return true;
    }

    // BIG PRIORITY HERE!!!
    public void generateBattleShips() {
        Random rand = new Random();
        int numShips = 4;

        for (int i = 0; i < numShips; i++) {
            int shipSize = rand.nextInt(3) + 2; // Ships will be 2-4 tiles long
            Orientation shipOrientation = Orientation.values()[rand.nextInt(Orientation.values().length)];

            boolean placed = false;
            while (!placed) {
                int startX = rand.nextInt(SIZE);
                int startY = rand.nextInt(SIZE);

                if (canPlaceShip(startX, startY, shipSize, shipOrientation)) {
                    // For actually building the ship
                    ArrayList<ShipPiece> shipPieces = new ArrayList<>();
                    for (int j = 0; j < shipSize; j++) {
                        // establish coords for singular ShipPiece based on orientation
                        int x = shipOrientation == Orientation.HORIZONTAL ? startX + j : startX;
                        int y = shipOrientation == Orientation.VERTICAL ? startY + j : startY;

                        // Create shipPiece and add it to array for Ship
                        ShipPiece piece = new ShipPiece(x, y);
                        shipPieces.add(piece);
                        board[x][y] = piece; // place the shipPiece on the board
                    }

                    // Create ship, add it to list of ships, new ship is now generated
                    Ship ship = new Ship(shipPieces, shipOrientation);
                    ships.add(ship);
                    placed = true;
                }
            }
        }
    }

    @Override
    public boolean checkMatch(Tile tile) {
        Map<Boolean, Ship> matchInfo = new HashMap<>();
        int counter = 0;

        if (tile instanceof ShipPiece shipPiece) {
            for (Ship ship : ships) {
                if (ship.getShipStructure().contains(shipPiece) && ship.checkSunk()) {
                    return true; // This ship is sunk
                }
            }
        }
        return false; // No ship was sunk
    }

    private Ship findSunkShip(ShipPiece shipPiece) {
        for (Ship ship : ships) {
            if (ship.getShipStructure().contains(shipPiece) && ship.checkSunk()) {
                return ship;
            }
        }
        return null;
    }

    @Override
    public void clearMatchedTiles(ArrayList<Tile> tiles) {
        if (tiles.isEmpty() || !(tiles.getFirst() instanceof ShipPiece)) {
            return;
        }

        ShipPiece reference = (ShipPiece) tiles.getFirst();
        Ship shipToRemove = findSunkShip(reference);

        if (shipToRemove != null) {
            // There is a ship that is fully sunken, needs to be removed from the board
            // Reset all tiles belonging to this ship
            for (ShipPiece piece : shipToRemove.getShipStructure()) {
                int x = piece.getCoordinates().get(0);
                int y = piece.getCoordinates().get(1);
                board[x][y] = new ShipPiece(x, y);
                ((ShipPiece)board[x][y]).changeState(ShipPieceState.WATER);
            }
            ships.remove(shipToRemove); // Remove the ship from the list
        }
    }

    @Override
    public void placeTile(int x_coord, int y_coord) {
        // params: x_coord and y_coord are the guessed coordinates
        // call verifyTilePos to check out-of-bounds
        // place the shipPiece
        // Check if tile on the board is a ship location
        // Check if entire ship is sunk after that hit, clear it from board if so

        if (!verifyTilePos(x_coord, y_coord, "bounds")) {
            return; // not a valid move
        }

        ShipPiece target = (ShipPiece) board[x_coord][y_coord];

        if (isLocationOfShip(x_coord, y_coord)) {
            target.changeState(ShipPieceState.HIT); // player found a Ship, its shipPiece is HIT now

            // Check if the entire Ship that the target hit is Sunk
            if (checkMatch(target)) {
                ArrayList<Tile> targetTile = new ArrayList<>();
                targetTile.add(target);
                clearMatchedTiles(targetTile);
            }
        }


    }

    // Checks whether the player hit a ShipPiece
    private boolean isLocationOfShip(int x_coord, int y_coord) {
        for (Ship ship : ships) {
            for (ShipPiece piece : ship.getShipStructure()) {
                ArrayList<Integer> coordinates = piece.getCoordinates();
                if (coordinates.get(0) == x_coord && coordinates.get(1) == y_coord) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean verifyTilePos(int x_coord, int y_coord, String check) {
        if (check.equals("bounds")) {
            // Check if coords are in bounds
            return x_coord < SIZE && x_coord >= 0 && y_coord < SIZE && y_coord >= 0;
        }
        if (check.equals("occupied")) {
            // Check if coords are NOT already occupied with another ShipPiece
            return !(board[x_coord][y_coord] instanceof ShipPiece) ||
                    ((ShipPiece)board[x_coord][y_coord]).getState() == ShipPieceState.WATER;
        }
        return false;
    }

    public boolean allShipsSunk() {
        return ships.isEmpty();
    }
}
