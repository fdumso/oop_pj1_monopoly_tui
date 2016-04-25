package ui;

import spot.SpotType;

/**
 * Created by freemso on 2016/4/25.
 */
public class Icon {
    private String iconText;

    public Icon(SpotType spotType) {
        switch (spotType) {
            case BankSpot: iconText = "银";
                break;
            case GroundSpot: iconText = "空";
                break;
            case CardSpot: iconText = "卡";
                break;
            case HouseSpot: iconText = "◎";
                break;
            case LotterySpot: iconText = "彩";
                break;
            case NewsSpot: iconText = "新";
                break;
            case StoreSpot: iconText = "道";
                break;
            case TicketSpot: iconText = "券";
                break;
            default: iconText = "";
        }
    }

    public String getIcon() {
        return iconText;
    }
}
