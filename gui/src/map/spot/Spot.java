package map.spot;

import player.Player;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Spot {
    private String name;
    private Spot next;
    private Spot prev;
    private boolean hasBarricade;
    private ArrayList<Player> playerList;
    private Type type;

    private int x;
    private int y;
    private int h;
    private int w;



    public Spot(String name, Type type, int x, int y, int h, int w) {
        this.playerList = new ArrayList<>();
        this.hasBarricade = false;
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public boolean hasPlayerOn() {
        return !playerList.isEmpty();
    }

    public boolean hasBarricade() {
        return hasBarricade;
    }

    public Player getTopPlayer() {
        if (hasPlayerOn()) {
            return playerList.get(playerList.size()-1);
        } else {
            return null;
        }
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public void setBarricade() {
        hasBarricade = true;
    }

    public enum Type {
        BANK, GET_CARD, GET_TICKET, HOUSE, LOTTERY, NEWS, PROPS_MART, HOSPITAL;

        @Override
        public String toString() {
            switch (this) {
                case BANK: return "Bank";
                case GET_CARD: return "GetCard";
                case GET_TICKET: return "GetTicket";
                case HOUSE: return "House";
                case LOTTERY: return "Lottery";
                case NEWS: return "News";
                case PROPS_MART: return "PropsMart";
                case HOSPITAL: return "Hospital";
                default: return "";
            }
        }
    }

    public String getIconName() {
        return this.type.toString();
    }

    public void stepIn(Player player) {
        playerList.add(player);
    }
    public boolean stepOut(Player player) {
        if (hasBarricade) {
            JOptionPane.showMessageDialog(null, "您被路障拦住，停留在了"+name);
            hasBarricade = false;
            return false;
        } else {
            playerList.remove(player);
            return true;
        }
    }
    public abstract void stay(Player player);

    public void setNext(Spot next) {
        this.next = next;
    }

    public void setPrev(Spot prev) {
        this.prev = prev;
    }

    public Spot getNext() {
        return next;
    }

    public Spot getPrev() {
        return prev;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
