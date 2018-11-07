import gui.MainPage;
import util.io.MapIO;

public class Monopoly {
    private static final Monopoly monopoly = new Monopoly();
    private Monopoly() {}
    public static Monopoly getInstance() {
        return monopoly;
    }

    public static void main(String[] args) {
        MapIO.getInstance().loadMapData();
        MainPage.getInstance().showPanel("start");
    }
}
