package ui;

import kernel.map.MapLogic;

/**
 * Created by freemso on 2016/4/26.
 */
public class TerminalMap {
    private MapLogic mapLogic = new MapLogic();

    public TerminalMap() {

    }

    public void printOriginalMap() {
        System.out.print("               " + mapLogic.getSpotList().get(0).getIcon() + " " + mapLogic.getSpotList().get(1).getIcon() + " " + mapLogic.getSpotList().get(2).getIcon() + " " + mapLogic.getSpotList().get(3).getIcon() + " " + mapLogic.getSpotList().get(4).getIcon() + " " + mapLogic.getSpotList().get(5).getIcon() + " " + mapLogic.getSpotList().get(6).getIcon() + "\n\n"
                + "               " + mapLogic.getSpotList().get(61).getIcon() + " " + "               " + mapLogic.getSpotList().get(7).getIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(60).getIcon() + " " + "               " + mapLogic.getSpotList().get(8).getIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(59).getIcon() + " " + "               " + mapLogic.getSpotList().get(9).getIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(58).getIcon() + " " + "               " + mapLogic.getSpotList().get(10).getIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(52).getIcon() + " " + mapLogic.getSpotList().get(53).getIcon() + " " + mapLogic.getSpotList().get(54).getIcon() + " " + mapLogic.getSpotList().get(55).getIcon() + " " + mapLogic.getSpotList().get(56).getIcon() + " " + mapLogic.getSpotList().get(57).getIcon() + " " + "               " + mapLogic.getSpotList().get(11).getIcon() + " " + mapLogic.getSpotList().get(12).getIcon() + " " + mapLogic.getSpotList().get(13).getIcon() + " " + mapLogic.getSpotList().get(14).getIcon() + " " + mapLogic.getSpotList().get(15).getIcon() + " " + mapLogic.getSpotList().get(16).getIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(51).getIcon() + " " + "              " + "                " + "               " + mapLogic.getSpotList().get(17).getIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(50).getIcon() + " " + "              " + "                " + "               " + mapLogic.getSpotList().get(18).getIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(49).getIcon() + " " + "              " + "                " + "               " + mapLogic.getSpotList().get(19).getIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(48).getIcon() + " " + "              " + "                " + "               " + mapLogic.getSpotList().get(20).getIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(47).getIcon() + " " + mapLogic.getSpotList().get(46).getIcon() + " " + mapLogic.getSpotList().get(45).getIcon() + " " + mapLogic.getSpotList().get(44).getIcon() + " " + mapLogic.getSpotList().get(43).getIcon() + " " + mapLogic.getSpotList().get(42).getIcon() + " " + "               " + mapLogic.getSpotList().get(26).getIcon() + " " + mapLogic.getSpotList().get(25).getIcon() + " " + mapLogic.getSpotList().get(24).getIcon() + " " + mapLogic.getSpotList().get(23).getIcon() + " " + mapLogic.getSpotList().get(22).getIcon() + " " + mapLogic.getSpotList().get(21).getIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(41).getIcon() + " " + "               " + mapLogic.getSpotList().get(27).getIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(40).getIcon() + " " + "               " + mapLogic.getSpotList().get(28).getIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(39).getIcon() + " " + "               " + mapLogic.getSpotList().get(29).getIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(38).getIcon() + " " + "               " + mapLogic.getSpotList().get(30).getIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(37).getIcon() + " " + mapLogic.getSpotList().get(36).getIcon() + " " + mapLogic.getSpotList().get(35).getIcon() + " " + mapLogic.getSpotList().get(34).getIcon() + " " + mapLogic.getSpotList().get(33).getIcon() + " " + mapLogic.getSpotList().get(32).getIcon() + " " + mapLogic.getSpotList().get(31).getIcon() + " " + "\n"
    );
    }

    public void printCurrentMap() {
        System.out.print("               " + mapLogic.getSpotList().get(0).printIcon() + " " + mapLogic.getSpotList().get(1).printIcon() + " " + mapLogic.getSpotList().get(2).printIcon() + " " + mapLogic.getSpotList().get(3).printIcon() + " " + mapLogic.getSpotList().get(4).printIcon() + " " + mapLogic.getSpotList().get(5).printIcon() + " " + mapLogic.getSpotList().get(6).printIcon() + "\n\n"
                + "               " + mapLogic.getSpotList().get(61).printIcon() + " " + "               " + mapLogic.getSpotList().get(7).printIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(60).printIcon() + " " + "               " + mapLogic.getSpotList().get(8).printIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(59).printIcon() + " " + "               " + mapLogic.getSpotList().get(9).printIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(58).printIcon() + " " + "               " + mapLogic.getSpotList().get(10).printIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(52).printIcon() + " " + mapLogic.getSpotList().get(53).printIcon() + " " + mapLogic.getSpotList().get(54).printIcon() + " " + mapLogic.getSpotList().get(55).printIcon() + " " + mapLogic.getSpotList().get(56).printIcon() + " " + mapLogic.getSpotList().get(57).printIcon() + " " + "               " + mapLogic.getSpotList().get(11).printIcon() + " " + mapLogic.getSpotList().get(12).printIcon() + " " + mapLogic.getSpotList().get(13).printIcon() + " " + mapLogic.getSpotList().get(14).printIcon() + " " + mapLogic.getSpotList().get(15).printIcon() + " " + mapLogic.getSpotList().get(16).printIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(51).printIcon() + " " + "              " + "                " + "               " + mapLogic.getSpotList().get(17).printIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(50).printIcon() + " " + "              " + "                " + "               " + mapLogic.getSpotList().get(18).printIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(49).printIcon() + " " + "              " + "                " + "               " + mapLogic.getSpotList().get(19).printIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(48).printIcon() + " " + "              " + "                " + "               " + mapLogic.getSpotList().get(20).printIcon() + " " + "\n\n"
                + mapLogic.getSpotList().get(47).printIcon() + " " + mapLogic.getSpotList().get(46).printIcon() + " " + mapLogic.getSpotList().get(45).printIcon() + " " + mapLogic.getSpotList().get(44).printIcon() + " " + mapLogic.getSpotList().get(43).printIcon() + " " + mapLogic.getSpotList().get(42).printIcon() + " " + "               " + mapLogic.getSpotList().get(26).printIcon() + " " + mapLogic.getSpotList().get(25).printIcon() + " " + mapLogic.getSpotList().get(24).printIcon() + " " + mapLogic.getSpotList().get(23).printIcon() + " " + mapLogic.getSpotList().get(22).printIcon() + " " + mapLogic.getSpotList().get(21).printIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(41).printIcon() + " " + "               " + mapLogic.getSpotList().get(27).printIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(40).printIcon() + " " + "               " + mapLogic.getSpotList().get(28).printIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(39).printIcon() + " " + "               " + mapLogic.getSpotList().get(29).printIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(38).printIcon() + " " + "               " + mapLogic.getSpotList().get(30).printIcon() + " " + "\n\n"
                + "               " + mapLogic.getSpotList().get(37).printIcon() + " " + mapLogic.getSpotList().get(36).printIcon() + " " + mapLogic.getSpotList().get(35).printIcon() + " " + mapLogic.getSpotList().get(34).printIcon() + " " + mapLogic.getSpotList().get(33).printIcon() + " " + mapLogic.getSpotList().get(32).printIcon() + " " + mapLogic.getSpotList().get(31).printIcon() + " " + "\n"
        );
    }
}
