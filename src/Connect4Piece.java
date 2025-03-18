import java.util.ArrayList;

public class Connect4Piece extends Tile{


    //arraylist<integer> = {x, y};
    Connect4Piece(Integer x, Integer y){
        this.coordinates = new ArrayList<>();
        this.coordinates.add(x);
        this.coordinates.add(y);
        this.filled = false;
        this.color = Color.RED; // will be determined later
    }


}
