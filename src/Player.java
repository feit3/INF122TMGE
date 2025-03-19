public class Player {
    private final String username;
    private int points;

    Player(String PlayerName) {
        username = PlayerName;
        points = 0;
    }

    public int getPoints() { //getter
        return points;
    }

    public void addPoints(int addedPoints) { //specified return type was int, but assumed to be void;
        points += addedPoints;
    }

    public void resetPoints() { //specified return type was int, but assumed to be void
        points = 0;
    }

    public void removePoints(int removedPoints) {
        points -= removedPoints;
    }

    public String getUsername() {
        return username;
    }

}

