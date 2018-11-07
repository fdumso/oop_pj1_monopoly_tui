package player;

import card.*;

import java.util.ArrayList;

public class CardPacket {
    private ArrayList<Card> cardList;

    CardPacket() {
        this.cardList = new ArrayList<>();

        cardList.add(new AverageRichCard());
        cardList.add(new BarricadeCard());
        cardList.add(new BuyHouseCard());
        cardList.add(new ControlDiceCard());
        cardList.add(new DemolitionCard());
        cardList.add(new ResidenceCard());
        cardList.add(new TurnAroundCard());

        cardList.add(new ControlDiceCard());
        cardList.add(new ControlDiceCard());
        cardList.add(new ControlDiceCard());
        cardList.add(new ControlDiceCard());
        cardList.add(new ControlDiceCard());
        cardList.add(new ControlDiceCard());

        cardList.add(new TurnAroundCard());
        cardList.add(new TurnAroundCard());
        cardList.add(new TurnAroundCard());

    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }
}
