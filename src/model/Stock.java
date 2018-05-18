package model;

import java.util.ArrayList;

public class Stock {

    private String stockSymbol;
    private String Type;
    private double lastDividend;
    private double fixedDividend;
    private int parValue;
    private double tickerPrice;
    private ArrayList<Trade> trades = new ArrayList<>();

    public Stock(String stockSymbol, String type, double lastDividend, double fixedDividend, int parValue) {
        this.stockSymbol = stockSymbol;
        this.Type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public String getStockSymbol() { return this.stockSymbol; }

    public String getType() { return this.Type; }

    public double getLastDividend() { return this.lastDividend; }

    public double getFixedDividend() { return this.fixedDividend; }

    public int getParValue() { return this.parValue; }

    public double getTickerPrice() { return this.tickerPrice; }

    public ArrayList<Trade> getTrades() { return this.trades; }

    public void setTickerPrice(double tickerPrice) { this.tickerPrice = tickerPrice; }

    public void setTrades(ArrayList<Trade> tradeList) { trades = tradeList; }

    public void addTrade(Trade trade) { this.trades.add(trade); }

}
