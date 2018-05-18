package model;

import java.time.LocalTime;

public class Trade {

    public enum Indicator { buy, sell }

    private double price;
    private LocalTime timestamp;
    private int sharesQuantity;
    private Indicator indicator;

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public LocalTime getTimestamp() { return timestamp; }

    public void setTimestamp(LocalTime timestamp) { this.timestamp = timestamp; }

    public int getSharesQuantity() { return sharesQuantity; }

    public void setSharesQuantity(int sharesQuantity) { this.sharesQuantity = sharesQuantity; }

    public Indicator getIndicator() { return indicator; }

    public void setIndicator(Indicator indicator) { this.indicator = indicator; }
}
