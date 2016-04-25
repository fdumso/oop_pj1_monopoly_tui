package spot;

import ui.Icon;

/**
 * Created by freemso on 2016/4/25.
 */
public class BankSpot extends AbstractSpot {

    public BankSpot(int id, String name) {
        super(id, name, new Icon(SpotType.BankSpot));
    }
}
