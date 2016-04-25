package spot;

import ui.Icon;

/**
 * Created by freemso on 2016/4/25.
 */
public class CardSpot extends AbstractSpot {
    public CardSpot(int id, String name) {
        super(id, name, new Icon(SpotType.CardSpot));
    }
}
