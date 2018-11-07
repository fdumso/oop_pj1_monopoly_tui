package stock;

public class Stock {
    private int id;
    private String name;
    private double price;
    private double rate;

    public Stock(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = 1;
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
