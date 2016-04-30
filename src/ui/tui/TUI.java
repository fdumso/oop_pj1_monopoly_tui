package ui.tui;

import kernel.Game;
import kernel.spot.AbstractSpot;
import kernel.spot.BankSpot;
import kernel.util.Option;
import ui.UI;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Created by freemso on 2016/4/27.
 */
public class TUI implements UI {
    private Scanner input;

    public TUI() {
        input = new Scanner(System.in);
    }

    @Override
    public int inputInt(String description, int l, int g) {
        System.out.print(description);
        do {
            try {
                int read = input.nextInt();
                if (read >= l && read <= g) {
                    return read;
                } else {
                    System.out.print("错误！大小不在范围内");
                }
            } catch (InputMismatchException e) {
                System.out.print("错误！需要一个整数");
                input.nextLine();
            }
        } while (true);
    }

    @Override
    public int inputInt(String description) {
        System.out.print(description);
        do {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("错误！需要一个整数");
                input.nextLine();
            }
        } while (true);
    }

    @Override
    public String inputStr(String description, int l, int g) {
        System.out.print(description);
        do {
            try {
                String s = input.nextLine();
                int length = s.length();
                if (length >= l && length <= g) {
                    return s;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.print("错误！长度不在范围内");
            }
        } while (true);
    }

    @Override
    public void start() {
        System.out.println("\n=================游戏开始=================\n");
        input.nextLine();
    }

    @Override
    public void showMessage(String s) {
        System.out.println(s);
        input.nextLine();
    }

    @Override
    public Game.Operation showMainPanel() {
        int operationId = inputInt("现在可以执行如下操作：\n" + "0 - 查看地图\n"
                + "1 - 查看原始地图\n" + "2 - 使用道具\n" + "3 - 前方十步内示警\n"
                + "4 - 查看前后指定步数的具体信息\n" + "5 - 查看玩家的资产信息\n"
                + "6 - 想看的都看了，心满意足地掷骰子\n" + "7 - 不玩了！认输！\n"
                + "8 - 进入股市\n" + "请选择：", 0, 8);
        switch (operationId) {
            case 0: {
                return Game.Operation.PRINT_CUR_MAP;
            }
            case 1: {
                return Game.Operation.PRINT_ORI_MAP;
            }
            case 2: {
                return Game.Operation.USE_CARD;
            }
            case 3: {
                return Game.Operation.SHOW_WARNING;
            }
            case 4: {
                return Game.Operation.SPOT_INFO;
            }
            case 5: {
                return Game.Operation.PLAYER_INFO;
            }
            case 6: {
                return Game.Operation.ROLL_DICE;
            }
            case 7: {
                return Game.Operation.CONCEDE;
            }
            case 8: {
                return Game.Operation.CHECK_STOCK;
            }
        }
        return null;
    }

    @Override
    public void popMessage(String description) {
        showMessage(description);
    }

    @Override
    public int select(String description, ArrayList<Option> optionList) {
        System.out.print(description + "\n输入选项前编号<输入 -1 取消>：");
        for (int i = 0; i < optionList.size(); i++) {
            System.out.println(i + ". " + optionList.get(i).getName());
        }
        return inputInt("> ", -1, optionList.size()-1);
    }

    @Override
    public boolean confirm(String description) {
        System.out.print(description + "(y/n): ");
        do {
            try {
                String s = input.nextLine();
                if (s.toLowerCase().equals("y")) {
                    return true;
                } else if (s.toLowerCase().equals("n")) {
                    return false;
                } else {
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.print("错误！需要Y/N");
                input.nextLine();
            }
        } while (true);
    }

    @Override
    public BankSpot.Operation showBankPanel() {
        showMessage("==========欢迎光临银行==========");
        int operationId = inputInt("存款业务请输入 0，取款业务请输入 1，离开请输入 -1：", -1, 1);
        switch (operationId) {
            case 0: return BankSpot.Operation.DOPOSIT;
            case 1: return BankSpot.Operation.WITHDRAW;
            default: return BankSpot.Operation.LEAVE;
        }
    }

    public void printOriginalMap(AbstractSpot[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getIcon().toTextual());
                } else {
                    System.out.print(" ");
                }
            }
        }
    }

    public void printCurrentMap(AbstractSpot[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].printIcon().toTextual());
                } else {
                    System.out.print(" ");
                }
            }
        }
    }

}
