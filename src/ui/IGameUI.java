package ui;

/**
 * Created by freemso on 2016/4/27.
 */
public interface IGameUI {
    void init();

    void startRound();

    void endRound();

    void playerStart(int playerId);

    void playerEnd(int playerId);

    void main(int playerId);

    void useCard(int playerId);

    void warning(int playerId);

    void showSpotInfo(int playerId);

    void showPlayerInfo();

    void rollDice(int playerId);

    void concede(int playerId);

    void gameOver();

    int chooseASpot();
}
