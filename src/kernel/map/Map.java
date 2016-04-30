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
    private final String STREET_DATA_PATH = "/street.data";
    private final String SPOT_DATA_PATH = "/spot.data";
    private final String BOARD_DATA_PATH = "/street.data";
    Scanner fileReader;
    private ArrayList<AbstractSpot> spotList;
    private ArrayList<Street> streetList;
    private AbstractSpot[][] board;

    public Map() {
        // load street data
        try {
            fileReader = new Scanner(new File(STREET_DATA_PATH));
            fileReader.nextLine();
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
            fileReader = new Scanner(new File(SPOT_DATA_PATH));
            fileReader.nextLine();
            while (fileReader.hasNext()) {
                String[] line = fileReader.nextLine().split("\t");
                int spotType = Integer.parseInt(line[0]);
                String spotName = line[1];
                Position position = new Position(Integer.parseInt(line[2]), Integer.parseInt(line[3]));
                switch (spotType) {
                    case 0: {
                        double housePrice = Double.parseDouble(line[4]);
                        int streetId = Integer.parseInt(line[5]);
                        AbstractSpot house = new HouseSpot(spotList.size(), spotName, housePrice, streetList.get(streetId), position);
                        spotList.add(house);
                        streetList.get(streetId).addSpot((HouseSpot) house);
                        break;
                    }
                    case 1: spotList.add(new BankSpot(spotList.size(), spotName, position));
                        break;
                    case 2: spotList.add(new LotterySpot(spotList.size(), spotName, position));
                        break;
                    case 3: spotList.add(new NewsSpot(spotList.size(), spotName, position));
                        break;
                    case 4: spotList.add(new CardSpot(spotList.size(), spotName, position));
                        break;
                    case 5: spotList.add(new TicketSpot(spotList.size(), spotName, position));
                        break;
                    case 6: spotList.add(new StoreSpot(spotList.size(), spotName, position));
                        break;
                    case 7: spotList.add(new EmptySpot(spotList.size(), spotName, position));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("Spot data not found!");
        }
        // load board data
        try {
            fileReader = new Scanner(new File(BOARD_DATA_PATH));
            fileReader.nextLine();
            String[] line = fileReader.nextLine().split("\t");
            int sizeI = Integer.parseInt(line[0]);
            int sizeJ = Integer.parseInt(line[1]);
            board = new AbstractSpot[sizeI][sizeJ];
            for (AbstractSpot spot: spotList) {
                board[spot.getPosition().getI()][spot.getPosition().getJ()] = spot;
            }
        } catch (FileNotFoundException e) {
            System.out.print("Board data not found!");
        }

    }

    public AbstractSpot getSpot(int spotId) {
        return spotList.get(spotId % spotList.size());
    }

    public Street getStreet(int streetId) {
        return streetList.get(streetId % streetList.size());
    }

    public AbstractSpot[][] getBoard() {
        return board;
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
