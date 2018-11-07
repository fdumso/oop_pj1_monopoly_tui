package util.io;

import card.Card;
import map.spot.HouseSpot;
import map.spot.Spot;
import player.Character;

import javax.swing.*;

public class ImageIO {
    private static final ImageIO imageIO = new ImageIO();
    private static final String IMAGE_PATH_PREFIX = "res/image/";
    private static final String BACKGROUND_PATH_PREFIX = IMAGE_PATH_PREFIX + "background/";
    private static final String CARD_PATH_PREFIX = IMAGE_PATH_PREFIX + "card/";
    private static final String SPOT_PATH_PREFIX = IMAGE_PATH_PREFIX + "spot/";
    private static final String CHARACTER_PATH_PREFIX = IMAGE_PATH_PREFIX + "character/";
    private static final String DICE_PATH_PREFIX = IMAGE_PATH_PREFIX+"dice/";
    private static final String BUTTON_PATH_PREFIX = IMAGE_PATH_PREFIX+"button/";

    private ImageIO() {}

    public static ImageIO getInstance() {
        return imageIO;
    }

    public ImageIcon getSpotIcon(Spot spot) {
        ImageIcon imageIcon;
        if (spot.getIconName().equals("House")) {
            if (((HouseSpot) spot).getHouse().getOwner() == null) {
                imageIcon = new ImageIcon(SPOT_PATH_PREFIX+"house/null_"+((HouseSpot) spot).getHouse().getLevel()+".png");
            } else {
                imageIcon = new ImageIcon(SPOT_PATH_PREFIX+"house/"+((HouseSpot) spot).getHouse().getOwner().getCharacter().toString()+"_"+((HouseSpot) spot).getHouse().getLevel()+".png");
            }
        } else {
            imageIcon = new ImageIcon(SPOT_PATH_PREFIX+spot.getIconName()+".png");
        }
        return imageIcon;
    }

    public ImageIcon getCharIcon(Character character) {
        return new ImageIcon(CHARACTER_PATH_PREFIX+"icon/"+character+".png");
    }

    public ImageIcon getCharAvatar(Character character) {
        return new ImageIcon(CHARACTER_PATH_PREFIX+"avatar/"+character+".png");
    }

    public ImageIcon getCharPortrait(Character character) {
        return new ImageIcon(CHARACTER_PATH_PREFIX+"portrait/"+character+".png");
    }

    public ImageIcon getDiceIcon(int point) {
        return new ImageIcon(DICE_PATH_PREFIX+point+".png");
    }

    public ImageIcon getCardIcon(Card card) {
        return new ImageIcon(CARD_PATH_PREFIX+card.getIconName()+".jpg");
    }

    public ImageIcon getBackground(String name) {
        return new ImageIcon(BACKGROUND_PATH_PREFIX+name+".jpg");
    }

    public ImageIcon getButtonIcon(String name) {
        return new ImageIcon(BUTTON_PATH_PREFIX+name+".png");
    }

}
