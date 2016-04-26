package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by freemso on 2016/4/26.
 */
public class InputReader {
    private Scanner input;

    InputReader() {
        input = new Scanner(System.in);
    }

    int readInt() {
        do {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("错误！需要一个整数");
                input.nextLine();
            }
        } while (true);
    }

    int readInt(int l, int g) {
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

    double readDouble() {
        do {
            try {
                return input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("错误！需要一个浮点数");
                input.nextLine();
            }
        } while (true);
    }

    boolean confirm() {
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

    String readString() {
        return input.nextLine();
    }

    String readString(int l, int g) {
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

}
