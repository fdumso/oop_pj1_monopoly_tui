package ui;

import kernel.Game;
import kernel.spot.BankSpot;
import kernel.util.Option;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/27.
 */
public interface UI {

    int inputInt(String description, int l, int g);

    int inputInt(String description);

    String inputStr(String description, int l, int g);

    void start();

    void showMessage(String description);

    Game.Operation showMainPanel();

    void popMessage(String description);

    int select(String description, ArrayList<Option> optionList);

    boolean confirm(String description);

    BankSpot.Operation showBankPanel();
}
