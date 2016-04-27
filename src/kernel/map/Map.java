package kernel.map;

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
                        AbstractSpot house = new HouseSpot(spotList.size(), spotName, housePrice, streetId);
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

    private class Street {
        private int id;
        private String name;
        private ArrayList<HouseSpot> spotList;

        Street(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        void addSpot(HouseSpot spot) {
            spotList.add(spot);
        }

        public double calcSurcharge(int playerId) {
            double surcharge = 0;
            for (HouseSpot spot: spotList) {
                if (spot.getOwnerId() != playerId) {
                    return 0;
                } else {
                    surcharge += spot.calcToll();
                }
            }
            return surcharge;
        }
    }

}
