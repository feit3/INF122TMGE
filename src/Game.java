import java.util.ArrayList;


public abstract class Game {
    ArrayList<Grid> gameBoards;
    ArrayList<Player> players; //if need be Array we can do that
    Player currentPlayer;
    Player winner;

    abstract void play();
    abstract void initialize();
    abstract boolean isGameOver();
    abstract void handleInput(int x_coord, int y_coord); //return type was assumed
    abstract void updateGameState(); // update player scores, game over check, etc.
    abstract void displayInstructions();
    abstract String getGameName();

    public Player getWinner() {
        return winner;
    }
}

