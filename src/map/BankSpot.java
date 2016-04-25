package map;

import ui.SpotIcon;

/**
 * Created by freemso on 2016/4/25.
 */
public class BankSpot extends AbstractSpot {

    public BankSpot(int id, String name) {
        super(id, name, new SpotIcon(Type.BANK));
    }
}
