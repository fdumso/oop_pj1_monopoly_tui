package ui;

import kernel.Player;
import kernel.map.AbstractSpot;

/**
 * Created by freemso on 2016/4/27.
 */
public interface IGameUI {
    void init();

    void startRound();

    void endRound();

    void playerStart(Player player);

    void playerEnd(Player player);

    void main(Player player);

    void useCard(Player player);

    void warning(Player player);

    void showSpotInfo(Player player);

    void showPlayerInfo();

    void rollDice(Player player);

    void concede(Player player);

    void gameOver();

    void showMessage(String message);

    AbstractSpot chooseASpotToSetBarricade(Player player);

    boolean confirmMessage(String s);

    int getIntegerMessage(String s);

    int getIntegerMessage(String s, int l, int g);
}
