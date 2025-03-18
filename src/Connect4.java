import java.util.ArrayList;

public class Connect4 extends Game {
    public Connect4(ArrayList<Player> incomingPlayers) {
        players = incomingPlayers;
    }

    @Override
    public String getGameName() {
        return "Connect 4";
    }

    @Override
    public void play() {}

    @Override
    public void initialize() {}

    @Override
    public boolean isGameOver() {return false;}


    @Override
    public void handleInput(int x_coord, int y_coord) {}

    @Override
    public void updateGameState() {}

}
