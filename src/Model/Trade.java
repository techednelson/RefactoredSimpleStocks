package Model;

public class Trade {

    private double price;
    private int sharesQuantity;
    public enum indicator { buy, sell }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public int getSharesQuantity() { return sharesQuantity; }

    public void setSharesQuantity(int sharesQuantity) { this.sharesQuantity = sharesQuantity; }

}
