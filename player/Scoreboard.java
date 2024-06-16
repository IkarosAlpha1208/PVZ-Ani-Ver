package player;

// class for the scoreboard (Made entirely just to fill the sort requriemnt)
public class Scoreboard implements Comparable<Scoreboard> {
    private int highWave = 0;

    // constructor class gets the high wave
    public Scoreboard(int w) {
        this.highWave = w;

    }

    // compares the high waves between different players
    // Returns a integer stating whether its smaller bigger or equal
    public int compareTo(Scoreboard s) {
        return s.getHighWave() - this.highWave;

    }

    // Getters and Setters methods

    public int getHighWave() {
        return this.highWave;
    }

    public void setHighWave(int w) {
        this.highWave = w;
    }

}
