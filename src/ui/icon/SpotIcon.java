package ui.icon;

import kernel.spot.AbstractSpot;
import kernel.util.SpotSystem;

/**
 * Created by freemso on 2016/4/25.
 */
public class SpotIcon extends Icon {

    public SpotIcon(SpotSystem.Type spotType) {
        switch (spotType) {
            case BANK: iconText = "银";
                break;
            case EMPTY: iconText = "空";
                break;
            case CARD: iconText = "卡";
                break;
            case HOUSE: iconText = "◎";
                break;
            case LOTTERY: iconText = "彩";
                break;
            case NEWS: iconText = "新";
                break;
            case STORE: iconText = "道";
                break;
            case TICKET: iconText = "券";
        }
    }
}