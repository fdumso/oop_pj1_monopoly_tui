package kernel;

/**
 * Created by freemso on 2016/4/30.
 */
public class Dice {
    private int point;
    private boolean isControlled;

    public int roll() {
        if (!isControlled) {
            point = (int) (Math.random() * 6 + 1);
        }
        return point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        isControlled = true;
        this.point = point;
    }
}
