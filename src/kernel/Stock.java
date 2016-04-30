package kernel;

/**
 * Created by freemso on 2016/4/30.
 */
public class Stock {
    private int id;
    private String name;
    private double price;
    private double rate;

    public Stock(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void fluc() {
        rate = 0.9 + Math.random()*0.2;
        price = price * rate;
    }
}
