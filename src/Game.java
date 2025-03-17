import java.util.ArrayList;


public abstract class Game {
    ArrayList<Player> players; //if need be Array we can do that
    int turnBelongsTo;
    Player winningPlayer;

    abstract void initialize();
    abstract Boolean checkGameOver();
    abstract void handleInput(String input); //return type was assumed
    abstract void updateScreen(); //return type was assumed
    abstract void start(); //return type was assumed

}

