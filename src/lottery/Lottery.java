package lottery;

import player.Player;

/**
 * Created by freemso on 2016/4/30.
 */
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

    public void setNumber(int number) {
        this.number = number;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
