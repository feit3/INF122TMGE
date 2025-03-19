import java.util.ArrayList;

public class Connect4 extends Game {
    final static int COLUMNS = 7;
    final static int ROWS = 6;


    public Connect4(ArrayList<Player> incomingPlayers) {
        this.players = incomingPlayers; // two players at most
        for (Player player : players) {
            Connect4Grid connect4Board = new Connect4Grid(player);
            gameBoards.add(connect4Board);
        }
    }

    boolean dropTile(int column, Tile tile){return false;}
    boolean isValidMove(int column){ //because both players share up to date board, we can take any board from self.boards;
        return !this.gameBoards.getFirst().board[0][0].filled; // if top layer has open spot, then we can slide tile in
    }


    /*Methods inherited from abstract Game class  */
     void play(){

     }
     void initialize(){
         for (Grid board: this.gameBoards) {
             board.initialize();
         }

     }


     Boolean isGameOver(){return false;}
     void handleInput(Tile tile, String x_coord, String y_coord){
        if(!isValidMove(Integer.parseInt(x_coord))){
            return;
            //invalid move
        }


     } //return type was assumed


     void updateGameState(){} // update player scores, game over check, etc.

     public String getName(){
         return "Connect4";
     }

}
