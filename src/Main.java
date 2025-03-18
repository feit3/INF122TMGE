import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome gamers to the game, prepare to have your skills tested: ;-;");
//        System.out.println("Please provide your Player usernames");
//        System.out.print("Username1 : ");
//        TMGEManager.getInstance().addNewPlayer(in.next());
//        System.out.print("Username2 : ");
//        TMGEManager.getInstance().addNewPlayer(in.next());


        TMGEManager.getInstance().addNewPlayer("bruh");
        TMGEManager.getInstance().addNewPlayer("dude");
        TMGEManager.getInstance().addNewGame("Battleship");
        TMGEManager.getInstance().displayGames();





    }


}
