public class Player {
    private String username;
    private int points;

    Player(String username){
        this.username = username;
        this.points = 0;
    }

    int getPoints(){ //getter
        return this.points;
    }
    void addPoints(int points){ //specified return type was int, but assumed to be void;
        this.points += points;
    }
    void resetPoints(){ //specified return type was int, but assumed to be void
        this.points = 0;
    }
    void removePoints(int points){
        this.points -= points;
    }

}
