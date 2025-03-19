import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
    public Set<Point> checkMatch(int row, int col) {
        return null;
    }


    @Override
    public void initialize() {
        // generate battleships in random areas on the grid, these ships can either be vertical or horizontal
        generateBattleShips();
    }

    // BIG PRIORITY HERE!!!
    public void generateBattleShips() {
        // create a ship object of a random size (no bigger than the size of the board) out of ShipPiece objects
        Random rand = new Random();
        int numShips = 4; // Number of ships per player
        for (int i = 0; i < numShips; i++) {
            int shipSize = rand.nextInt(SIZE) + 1;
            Orientation shipOrientation = Orientation.values()[rand.nextInt(Orientation.values().length)]; // random orientation value (I think)
            // 1. Got to find a way to build ShipStructure array using shipSize
            // Ship ship = new Ship(shipOrientation);
            // 2. Place the ship on the board somewhere
            // 3. add the ship to the list of ships on the board
            // ships.add(ship);
        }
    }

    public boolean checkMatch(Tile tile) {
        int counter = 0;
        if (tile instanceof ShipPiece) {
            ShipPiece shipPiece = (ShipPiece) tile;
            for (Ship ship : ships) {
                ArrayList<ShipPiece> shipStructure = ship.getShipStructure();
                if (shipStructure.contains(shipPiece)) {
                    for (ShipPiece piece : shipStructure) {
                        if (piece.isHit()) {
                            counter++;
                        }
                    }
                }
            }
            return counter == ships.size(); // checks and returns condition of if ALL shipPieces were hit on a Ship
        }
        return false; // when the obj passed in is NOT a ShipPiece instance
    }

    @Override
    public void clearMatchedTiles(ArrayList<Tile> tiles) {
        // need to do
    }

    @Override
    public void placeTile(int x_coord, int y_coord) {

    }

    @Override
    public boolean verifyTilePos(int x_coord, int y_coord) {
        return false; // need to do
    }

    @Override
    public Tile[][] getBoard() {
        return new Tile[0][];
    }

    public boolean allShipsSunk() {
        return ships.isEmpty();
    }
}
