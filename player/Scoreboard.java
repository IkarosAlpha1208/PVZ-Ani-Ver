package player;

public class Scoreboard implements Comparable<Scoreboard> {
    private int highWave = 0;

    public Scoreboard(int w) {
        this.highWave = w;

    }

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
