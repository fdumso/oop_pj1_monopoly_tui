package map;

import map.AbstractSpot;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/26.
 */
public class Map {
    private ArrayList<AbstractSpot> spotList;
    private ArrayList<Street> streetList;

    public Map() {
    }

    class Street {
        private ArrayList<AbstractSpot> spotList;

    }

}
