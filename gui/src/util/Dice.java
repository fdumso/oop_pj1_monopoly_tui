package util;

public class Dice {
    private int point = 1;
    private boolean isCheated = false;
    private int cheatNum;
    private static Dice dice = new Dice();
    private Dice() {}

    public static Dice getInstance() {
        return dice;
    }


    public int randomPoint() {
        point = (int) (Math.random()*6) + 1;
        return point;
    }

    public int getPoint() {
        return point;
    }

    public void cheat(int num) {
        cheatNum = num;
        isCheated = true;
    }

    public void clearCheat() {
        isCheated = false;
    }

    public boolean isCheated() {
        return isCheated;
    }

    public int getCheatNum() {
        return cheatNum;
    }
}
