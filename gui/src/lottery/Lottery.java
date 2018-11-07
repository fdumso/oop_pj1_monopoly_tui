package lottery;

import player.Player;


public class Lottery {
    private int number;
    private Player owner;

    public Lottery(int number, Player owner) {
        this.number = number;
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }

    public Player getOwner() {
        return owner;
    }
}
