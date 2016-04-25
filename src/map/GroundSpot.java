package map;

import ui.SpotIcon;

/**
 * Created by freemso on 2016/4/25.
 */
public class GroundSpot extends AbstractSpot {
    public GroundSpot(int id, String name) {
        super(id, name, new SpotIcon(Type.GROUND));
    }
}
