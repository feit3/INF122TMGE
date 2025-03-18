import java.util.ArrayList;


public abstract class Game {
    ArrayList<Grid> gameBoards;
    ArrayList<Player> players; //if need be Array we can do that
    int turnBelongsTo;
    Player currentPlayer;
    Player winner;

    abstract void initialize();

    abstract void play(); //return type was assumed
    abstract Boolean isGameOver();
    abstract void handleInput(Tile tile, String x_coord, String y_coord); //return type was assumed
    abstract void updateGameState(); // update player scores, game over check, etc.
    public Player getWinner() {
        return winner;
    }

}

