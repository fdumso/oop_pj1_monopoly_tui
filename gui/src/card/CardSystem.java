package card;

public class CardSystem {
    private static final CardSystem cardSystem = new CardSystem();
    private CardSystem() {}
    public static CardSystem getInstance() {
        return cardSystem;
    }


    public Card randomCard() {
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

    public Card getCard(Card.Type type) {
        switch (type) {
            case AVERAGE_RICH: {
                return new AverageRichCard();
            }
            case BARRICADE: {
                return new BarricadeCard();
            }
            case BUY_HOUSE: {
                return new BuyHouseCard();
            }
            case CONTROL_DICE: {
                return new ControlDiceCard();
            }
            case DEMOLITION: {
                return new DemolitionCard();
            }
            case RESIDENCE: {
                return new ResidenceCard();
            }
            case TURN_AROUND: {
                return new TurnAroundCard();
            }
            default: {
                return null;
            }
        }
    }
}
