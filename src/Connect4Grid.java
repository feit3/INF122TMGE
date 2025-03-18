import java.util.ArrayList;

public class Connect4Grid extends Grid {
    final static int COLUMNS = 7;
    final static int ROWS = 6;


    public Connect4Grid(Player Owner) {
        Tile[][] board = new Tile[COLUMNS][ROWS]; //sets up board with predefined values for connect4
        for(int i = 0; i < COLUMNS; i++) {
            for(int j = 0; j < ROWS; j++) {
                board[i][j] = new Connect4Piece(i,j); // fill board with unfilled Tiles
            }
        }
        this.player = Owner; //owner of class object
    }

     public void initialize(){

     }


     public boolean checkMatch(Tile tile) { //assuming tile is the newly placed tile
//         for (int i = 0; i < 4; ++i) { // cross, diagonals
//             int coordinate_x = tile.getCoordinates().get(0); //coordinate x
//             int coordinate_y = tile.getCoordinates().get(1); // coordinate y
//             Color colorToCheck = tile.getColor();
//             //loop checking every angle
//
//             //end of loop
//
//             return false;
//         }
//         if(checkRows())

         return false;
     }

     public void clearMatchedTiles(ArrayList<Tile> tiles){}
     public void placeTile(int x_coord, int y_coord){}
     public boolean verifyTilePos(int x_coord, int y_coord){


        return false;
    }


    public boolean checkRows(int coordinate_x, int coordinate_y, Color colorToCheck){
        int count = 1; //how many in that row/collumn
        for (int j = coordinate_y; j < COLUMNS; ++j) {
            if (this.board[coordinate_x][j].getColor() == colorToCheck) {
                count++;
            } else {
                break;
            } //move on to next row;
        }
        for (int j = coordinate_x - 1; 0 <= j; ++j) { // -1 to compensate for previous
            if (this.board[coordinate_x][j].getColor() == colorToCheck) {
                count++;
            } else {
                break;
            } //move on to next row;
        }
        if (count == 4) { //after both loops have gone through, check value;
            return true;
        } // a Connect4 was made;

        return false;
    }

    public boolean checkColumns(int coordinate_x, int coordinate_y, Color colorToCheck){
        int count = 1; //how many in that row/collumn
        for (int x = coordinate_x; x < ROWS; ++x) {
            if (this.board[x][coordinate_y].getColor() == colorToCheck) {
                count++;
            } else {
                break;
            } //move on to next row;
        }
        for (int x = coordinate_x - 1; 0 <= x; ++x) { // -1 to compensate for previous
            if (this.board[x][coordinate_y].getColor() == colorToCheck) {
                count++;
            } else {
                break;
            } //move on to next row;
        }
        if (count == 4) { //after both loops have gone through, check value;
            return true;
        } // a Connect4 was made;

        return false;
    }





}
