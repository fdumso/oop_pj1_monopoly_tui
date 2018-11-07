package util.io;

import map.Street;
import map.spot.*;
import map.MapSystem;

import java.io.BufferedReader;
import java.io.FileReader;

public class MapIO {
    private static final String STREET_DATA_PATH = "res/map/street.data";
    private static final String SPOT_DATA_PATH = "res/map/spot.data";
    private static final MapIO mapIO = new MapIO();
    private MapIO() {}
    public static MapIO getInstance() {
        return mapIO;
    }


    public void loadMapData() {
        BufferedReader fileReader;
        // load street data
        try {
            fileReader = new BufferedReader(new FileReader(STREET_DATA_PATH));
            fileReader.readLine();
            String lineStr;
            while ((lineStr = fileReader.readLine()) != null) {
                String[] line = lineStr.split("\t");
                Street street = new Street(Integer.parseInt(line[0]), line[1]);
                MapSystem.getInstance().addStreet(street);
            }
        } catch (java.io.IOException e) {
            System.out.print("Street data not found!");
        }
        // load spot data
        try {
            fileReader = new BufferedReader(new FileReader(SPOT_DATA_PATH));
            fileReader.readLine();
            String lineStr;
            Spot first = null;
            Spot prev = null;
            while ((lineStr = fileReader.readLine()) != null) {
                Spot spot = null;
                int x;
                int y;
                int h;
                int w;
                String[] line = lineStr.split("\t");
                int spotType = Integer.parseInt(line[0]);
                x = Integer.parseInt(line[4]);
                y = Integer.parseInt(line[5]);
                h = Integer.parseInt(line[6]);
                w = Integer.parseInt(line[7]);
                String spotName = line[1];
                switch (spotType) {
                    case 0: {
                        double initPrice = Double.parseDouble(line[2]);
                        int streetId = Integer.parseInt(line[3]);
                        spot = new HouseSpot(spotName, initPrice, MapSystem.getInstance().getStreetWithID(streetId), x, y, h, w);
                        MapSystem.getInstance().getStreetWithID(streetId).addHouse((HouseSpot) spot);
                        break;
                    }
                    case 1: {
                        spot = new BankSpot(spotName, x, y, h, w);
                        MapSystem.getInstance().setBankSpot((BankSpot) spot);
                        break;
                    }
                    case 2: {
                        spot = new LotterySpot(spotName, x, y, h, w);
                        break;
                    }
                    case 3: {
                        spot = new NewsSpot(spotName, x, y, h, w);
                        break;
                    }
                    case 4: {
                        spot = new GetCardSpot(spotName, x, y, h, w);
                        break;
                    }
                    case 5: {
                        spot = new GetTicketSpot(spotName, x, y, h, w);
                        break;
                    }
                    case 6: {
                        spot = new PropsMartSpot(spotName, x, y, h, w);
                        break;
                    }
                    case 7: {
                        spot = new HospitalSpot(spotName, x, y, h, w);
                        MapSystem.getInstance().setHospitalSpot((HospitalSpot) spot);
                        break;
                    }
                }
                if (first != null) {
                    spot.setPrev(prev);
                    prev.setNext(spot);
                } else {
                    first = spot;
                }
                prev = spot;
            }
            first.setPrev(prev);
            prev.setNext(first);
        } catch (java.io.IOException e) {
            System.out.print("Spot data not found!");
        }

    }
}
