import java.util.ArrayList;

public class Main {

    public static void main(String[] arg) {

        ArrayList<Player> p = new ArrayList<>();
        p.add(new Player("Player1"));
        p.add(new Player("Player2"));
        Game c4 = new Connect4(p);
        TMGEManager.playGame(c4);
    }
}
