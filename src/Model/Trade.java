package Model;

import java.time.LocalTime;

public class Trade {

    private double price;
    private LocalTime timestamp;
    private int sharesQuantity;
    public enum indicator { buy, sell }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public LocalTime getTimestamp() { return timestamp; }

    public void setTimestamp(LocalTime timestamp) { this.timestamp = timestamp; }

    public int getSharesQuantity() { return sharesQuantity; }

    public void setSharesQuantity(int sharesQuantity) { this.sharesQuantity = sharesQuantity; }
}
