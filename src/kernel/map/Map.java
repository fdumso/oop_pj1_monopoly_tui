package kernel.map;

import kernel.spot.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by freemso on 2016/4/26.
 */
public class Map {
    private final String spotDataFilePath = "res/spot.data";
    private final String streetDataFilePath = "res/street.data";
    private ArrayList<AbstractSpot> spotList;
    private ArrayList<Street> streetList;

    public Map() {
        // load street data
        try {
            Scanner fileReader = new Scanner(new File(streetDataFilePath));
            while (fileReader.hasNext()) {
                String[] line = fileReader.nextLine().split("\t");
                Street street = new Street(Integer.parseInt(line[0]), line[1]);
                streetList.add(street);
            }
        } catch (FileNotFoundException e) {
            System.out.print("Street data not found!");
        }
        // load spot data
        try {
            Scanner fileReader = new Scanner(new File(spotDataFilePath));
            while (fileReader.hasNext()) {
                String[] line = fileReader.nextLine().split("\t");
                int spotType = Integer.parseInt(line[0]);
                String spotName = line[1];
                switch (spotType) {
                    case 0: {
                        double housePrice = Double.parseDouble(line[2]);
                        int streetId = Integer.parseInt(line[3]);
                        AbstractSpot house = new HouseSpot(spotList.size(), spotName, housePrice, streetList.get(streetId));
                        spotList.add(house);
                        streetList.get(streetId).addSpot((HouseSpot) house);
                        break;
                    }
                    case 1: spotList.add(new BankSpot(spotList.size(), spotName));
                        break;
                    case 2: spotList.add(new LotterySpot(spotList.size(), spotName));
                        break;
                    case 3: spotList.add(new NewsSpot(spotList.size(), spotName));
                        break;
                    case 4: spotList.add(new CardSpot(spotList.size(), spotName));
                        break;
                    case 5: spotList.add(new TicketSpot(spotList.size(), spotName));
                        break;
                    case 6: spotList.add(new StoreSpot(spotList.size(), spotName));
                        break;
                    case 7: spotList.add(new EmptySpot(spotList.size(), spotName));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("Spot data not found!");
        }
    }

    public AbstractSpot getSpot(int spotId) {
        return spotList.get(spotId % spotList.size());
    }

    public Street getStreet(int streetId) {
        return streetList.get(streetId % streetList.size());
    }

    public int calcDistance(int p1, int p2) {
        if (p1 > p2) {
            int d1 = p1 - p2;
            int d2 = p2 + spotList.size() - p1;
            return Math.min(d1, d2);
        } else if (p1 < p2) {
            int d1 = p2 - p1;
            int d2 = p1 + spotList.size() - p2;
            return Math.min(d1, d2);
        } else {
            return 0;
        }
    }

}
