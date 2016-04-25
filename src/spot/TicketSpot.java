package spot;

import ui.Icon;

/**
 * Created by freemso on 2016/4/25.
 */
public class TicketSpot extends AbstractSpot {
    public TicketSpot(int id, String name) {
        super(id, name, new Icon(SpotType.TicketSpot));
    }
}
