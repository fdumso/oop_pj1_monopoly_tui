package spot;

import ui.Icon;

/**
 * Created by freemso on 2016/4/25.
 */
public class HouseSpot extends AbstractSpot {
    public HouseSpot(int id, String name) {
        super(id, name, new Icon(SpotType.HouseSpot));
    }
}
