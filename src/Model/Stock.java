package Model;

public class Stock {
    private String stockSymbol;
    private String Type;
    private double lastDividend;
    private double fixedDividend;
    private int parValue;
    private double tickerPrice;

    public Stock(String stockSymbol, String type, double lastDividend, double fixedDividend, int parValue) {
        this.stockSymbol = stockSymbol;
        this.Type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public String getStockSymbol() { return stockSymbol; }

    public String getType() { return Type; }

    public double getLastDividend() { return lastDividend; }

    public double getFixedDividend() { return fixedDividend; }

    public int getParValue() { return parValue; }

    public double getTickerPrice() { return tickerPrice;
    }

    public void setTickerPrice(double tickerPrice) { this.tickerPrice = tickerPrice; }
}
