package ui;

/**
 * Created by freemso on 2016/4/27.
 */
public class HouseIcon extends Icon {
    public HouseIcon(int player) {
        switch (player) {
            case 0: iconText = "①";
                break;
            case 1: iconText = "②";
                break;
            case 2: iconText = "③";
                break;
            case 3: iconText = "④";
        }
    }
}
