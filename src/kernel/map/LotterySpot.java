package kernel.map;

import ui.SpotIcon;

/**
 * Created by freemso on 2016/4/25.
 */
public class LotterySpot extends AbstractSpot {
    public LotterySpot(int id, String name) {
        super(id, name, new SpotIcon(Type.LOTTERY));
    }
}
