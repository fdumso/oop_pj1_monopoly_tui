package ui;

/**
 * Created by freemso on 2016/4/25.
 */
public class PlayerIcon extends Icon {

    public PlayerIcon(int playerID) {
        switch (playerID) {
            case 0: iconText = "壹";
                break;
            case 1: iconText = "贰";
                break;
            case 2: iconText = "叁";
                break;
            case 3: iconText = "肆";
        }
    }
}
