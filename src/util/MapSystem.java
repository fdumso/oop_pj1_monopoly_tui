package util;

import player.Player;
import spot.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/26.
 */
public class MapSystem {
    private final String STREET_DATA_PATH = "street.data";
    private final String SPOT_DATA_PATH = "spot.data";
    private final String BOARD_DATA_PATH = "board.data";
    BufferedReader fileReader;
    private ArrayList<AbstractSpot> spotList;
    private ArrayList<Street> streetList;
    private AbstractSpot[][] board;

    public MapSystem() {
        // load street data
        streetList = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(STREET_DATA_PATH));
            fileReader.readLine();
            String linestr;
            while ((linestr = fileReader.readLine()) != null) {
                String[] line = linestr.split("\t");
                Street street = new Street(Integer.parseInt(line[0]), line[1]);
                streetList.add(street);
            }
        } catch (java.io.IOException e) {
            System.out.print("Street data not found!");
        }
        // load spot data
        spotList = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(SPOT_DATA_PATH));
            fileReader.readLine();
            String linestr;
            while ((linestr = fileReader.readLine()) != null) {
                String[] line = linestr.split("\t");
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
        } catch (java.io.IOException e) {
            System.out.print("Spot data not found!");
        }
        // load board data
        try {
            fileReader = new BufferedReader(new FileReader(BOARD_DATA_PATH));
            fileReader.readLine();
            String[] line = fileReader.readLine().split("\t");
            int sizeI = Integer.parseInt(line[0]);
            int sizeJ = Integer.parseInt(line[1]);
            board = new AbstractSpot[sizeI][sizeJ];
            for (AbstractSpot spot: spotList) {
                board[spot.getPosition().getI()][spot.getPosition().getJ()] = spot;
            }
        } catch (java.io.IOException e) {
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

    public void addPlayer(Player player) {
        AbstractSpot initPosition = getSpot((int) (Math.random() * spotList.size()));
        Player.Direction initDirection = Player.Direction.CLOCKWISE;
        player.setPosition(initPosition);
        initPosition.addPlayer(player);
        player.setDirection(initDirection);
    }


    public void showWarning(Game game, Player player) {
        int playerPositionId = player.getPosition().getId();
        boolean hasWarning = false;
        for (int i = 1; i <= 10; i++) {
            if (getSpot(playerPositionId + i).hasBarricade()) {
                hasWarning = true;
                game.getUI().showMessage("在你前方 " + i + " 步处有路障！");
            }
        }
        if (!hasWarning) {
            game.getUI().showMessage("前方 10 步内未检测到路障");
        }
    }

    public void showSpotInfo(Game game, Player player) {
        AbstractSpot spot = player.selectSpot(game);
        SpotType type = spot.getType();
        switch (type) {
            case HOUSE: {
                game.getUI().showMessage("类型：房产\n名称：" + spot.getName() + "\n初始价格：" + ((HouseSpot) spot).getOriginalPrice()
                        + " 元\n等级：" + ((HouseSpot) spot).getLevel() + " 级\n当前价格：" + ((HouseSpot) spot).calcPrice() + " 元\n拥有者："
                        + ((HouseSpot) spot).getOwner().getName());
                break;
            }
            case BANK: {
                game.getUI().showMessage("类型：银行\n名称：" + spot.getName());
                break;
            }
            case EMPTY: {
                game.getUI().showMessage("类型：空地\n名称：" + spot.getName());
                break;
            }
            case CARD: {
                game.getUI().showMessage("类型：赠送卡片点\n名称：" + spot.getName());
                break;
            }
            case TICKET: {
                game.getUI().showMessage("类型：赠送点券点\n名称：" + spot.getName());
                break;
            }
            case LOTTERY: {
                game.getUI().showMessage("类型：彩票店\n名称：" + spot.getName());
                break;
            }
            case NEWS: {
                game.getUI().showMessage("类型：新闻点\n名称：" + spot.getName());
                break;
            }
            case STORE: {
                game.getUI().showMessage("类型：道具店\n名称：" + spot.getName());
                break;
            }
        }
    }

    public void printCurMap(Game game) {
        game.getUI().printCurMap(board);
    }

    public void printOriMap(Game game) {
        game.getUI().printOriMap(board);
    }







}
