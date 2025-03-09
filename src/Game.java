package src;

import java.util.ArrayList;

public abstract class Game {
    Grid grid;
    ArrayList<Player> players; //if need be Array we can do that
    Player turnBelongsTo;
    Player winningPlayer;

    abstract void initialize();
    abstract Boolean checkGameOver();
    abstract void handleInput(String input); //return type was assumed
    abstract void updateScreen(); //return type was assumed
}
