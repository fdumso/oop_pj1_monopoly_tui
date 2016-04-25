package kernel;

import ui.Icon;

/**
 * Created by freemso on 2016/4/25.
 */
public class House extends Spot {
    public House(int id, String name) {
        super(id, name, new Icon("◎"));
    }
}
