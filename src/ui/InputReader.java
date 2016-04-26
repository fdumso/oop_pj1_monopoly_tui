package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by freemso on 2016/4/26.
 */
public class InputReader {
    private Scanner input;

    public InputReader() {
        input = new Scanner(System.in);
    }

    public int readInt() {
        do {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("错误！需要一个整数");
                input.nextLine();
            }
        } while (true);
    }

    public int readInt(int l, int g) {
        do {
            try {
                int read = input.nextInt();
                if (read >= l && read <= g) {
                    return read;
                } else {
                    System.out.print("错误！不在范围内");
                }
            } catch (InputMismatchException e) {
                System.out.print("错误！需要一个整数");
                input.nextLine();
            }
        } while (true);
    }

    public double readDouble() {
        do {
            try {
                return input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("错误！需要一个浮点数");
                input.nextLine();
            }
        } while (true);
    }

    public boolean confirm() {
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

}
