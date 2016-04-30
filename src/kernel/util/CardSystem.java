package kernel.util;

import kernel.card.*;

/**
 * Created by freemso on 2016/4/30.
 */
public class CardSystem {

    public enum Type {
        AVERAGE_RICH, BARRICADE, BUY_HOUSE, CONTROL_DICE, DEMOLITION, RESIDENCE, TURN_AROUND
    }

    public AbstractCard randomCard() {
        int randomNum = (int) (Math.random() * 38);
        if (randomNum < 4) {
            // average rich card
            return new AverageRichCard();
        } else if (randomNum < 10) {
            // barricade card
            return new BarricadeCard();
        } else if (randomNum < 20) {
            // buy house card
            return new BuyHouseCard();
        } else if (randomNum < 24) {
            // control dice card
            return new ControlDiceCard();
        } else if (randomNum < 30) {
            // demolition
            return new DemolitionCard();
        } else if (randomNum < 34) {
            // residence
            return new ResidenceCard();
        } else {
            // turn around
            return new TurnAroundCard();
        }
    }
}
