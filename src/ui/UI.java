package ui;

import kernel.Instruction;
import kernel.card.AbstractCard;
import kernel.spot.AbstractSpot;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Created by freemso on 2016/4/27.
 */
public class UI {
    private Scanner input;

    public UI() {
        input = new Scanner(System.in);
    }

    public int inputInt(String description, int l, int g) {
        System.out.print(description);
        do {
            try {
                int read = Integer.parseInt(input.nextLine());
                if (read >= l && read <= g) {
                    return read;
                } else {
                    System.out.print("错误！大小不在范围内，重新输入：");
                }
            } catch (NumberFormatException e) {
                System.out.print("错误！需要一个整数，重新输入：");
                input.nextLine();
            }
        } while (true);
    }

    public int inputInt(String description) {
        System.out.print(description);
        do {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("错误！需要一个整数，重新输入：");
                input.nextLine();
            }
        } while (true);
    }

    public String inputStr(String description, int l, int g) {
        System.out.print(description);
        do {
            try {
                String s = input.nextLine().trim();
                int length = s.length();
                if (length >= l && length <= g) {
                    return s;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.print("错误！长度不在范围内，重新输入：");
            }
        } while (true);
    }

    public void startGame() {
        System.out.println("\n=================游戏开始=================\n");
        input.nextLine();
    }

    public void showMessage(String s) {
        System.out.println(s);
    }

    public Instruction getInstruction() {
        int operationId = inputInt("现在可以执行如下操作：\n" + "0 - 查看地图\n"
                + "1 - 查看原始地图\n" + "2 - 使用道具\n" + "3 - 前方十步内示警\n"
                + "4 - 查看前后指定步数的具体信息\n" + "5 - 查看玩家的资产信息\n"
                + "6 - 想看的都看了，心满意足地掷骰子\n" + "7 - 不玩了！认输！\n"
                + "8 - 进入股市\n" + "请选择：", 0, 8);
        switch (operationId) {
            case 0: {
                return Instruction.PRINT_CUR_MAP;
            }
            case 1: {
                return Instruction.PRINT_ORI_MAP;
            }
            case 2: {
                return Instruction.USE_CARD;
            }
            case 3: {
                return Instruction.SHOW_WARNING;
            }
            case 4: {
                return Instruction.SPOT_INFO;
            }
            case 5: {
                return Instruction.PLAYER_INFO;
            }
            case 6: {
                return Instruction.ROLL_DICE;
            }
            case 7: {
                return Instruction.CONCEDE;
            }
            case 8: {
                return Instruction.CHECK_STOCK;
            }
        }
        return null;
    }

    public int select(String description, ArrayList<AbstractCard> optionList) {
        System.out.print(description + "\n输入选项前编号<输入 -1 取消>：\n");
        for (int i = 0; i < optionList.size(); i++) {
            System.out.println(i + ". " + optionList.get(i).getName());
        }
        return inputInt("> ", -1, optionList.size()-1);
    }

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
                System.out.print("错误！需要Y/N，请重新输入：");
            }
        } while (true);
    }

    public void printOriMap(AbstractSpot[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getIcon().toTextual());
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("\n");
        }
    }

    public void printCurMap(AbstractSpot[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].printIcon().toTextual());
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("\n");
        }
    }
}
