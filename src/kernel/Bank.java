package kernel;

import ui.Icon;

/**
 * Created by freemso on 2016/4/25.
 */
public class Bank extends Spot {

    public Bank(int id, String name) {
        super(id, name, new Icon("银"));
    }
}
