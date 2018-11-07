package card;

import player.Player;

public abstract class Card {
    private Type type;
    private int price;

    public Card(Type type, int price) {
        this.type = type;
        this.price = price;
    }

    public String getName() {
        switch (this.type) {
            case AVERAGE_RICH: return "均富卡";
            case BARRICADE: return "路障卡";
            case BUY_HOUSE: return "购房卡";
            case CONTROL_DICE: return "遥控骰子";
            case DEMOLITION: return "拆迁卡";
            case RESIDENCE: return "滞留卡";
            case TURN_AROUND: return "转向卡";
            default: return "";
        }
    }

    public String getIconName() {
        return this.type.toString();
    }


    public enum Type {
        AVERAGE_RICH, BARRICADE, BUY_HOUSE, CONTROL_DICE, DEMOLITION, RESIDENCE, TURN_AROUND;

        @Override
        public String toString() {
            switch (this) {
                case AVERAGE_RICH: return "Average Rich Card";
                case BARRICADE: return "Barricade Card";
                case BUY_HOUSE: return "Buy House Card";
                case CONTROL_DICE: return "Control Dice Card";
                case DEMOLITION: return "Demolition Card";
                case RESIDENCE: return "Residence Card";
                case TURN_AROUND: return "Turn Around Card";
                default: return "";
            }
        }
    }

    public int getPrice() {
        return price;
    }

    public abstract boolean use(Player user);
}
