package services;

import database.StaticDatabase;
import model.*;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;

public class TradeServicesImpl implements TradeServices {
    private static ArrayList<Stock> stocks;

    /**
     * recordTrade method will record a trade, with timestamp, quantity of shares, buy or sell indicator and price.
     * @param stockSymbol name of stock selected by customer
     */
    @Override
    public void recordTrade(String stockSymbol) {
        stocks = StaticDatabase.getStocksDB(); //get current stocks with their current values from DB at database package
        System.out.println("\n\t Trade Record\n");
        System.out.println("\nStock Symbol\t Timestamp\t\t Shares\t\t Buy/Sell\t\t Price");
        for(Stock stock : stocks) {
            if(stock != null && stock.getStockSymbol().equals(stockSymbol)) {
                Trade trade = new Trade();
                trade.setTimestamp(LocalTime.now());
                trade.setSharesQuantity((int)(Math.random() * 1000 + 1));
                trade.setPrice(Math.random() * 20 + 1);
                if((int)(Math.random() * 2) == 0) trade.setIndicator(Trade.Indicator.sell);
                else trade.setIndicator(Trade.Indicator.buy);
                System.out.println("\t" + stock.getStockSymbol() + "\t\t\t" + trade.getTimestamp() + "\t   " + trade
                        .getSharesQuantity
                        () +
                        "\t\t   " + trade.getIndicator().toString() + "\t\t\t " + new DecimalFormat("#.##").format(trade
                        .getPrice()));
                stock.addTrade(trade);
                break;
            }
        }
        StaticDatabase.setStocksDB(stocks); //update Database after inserting ticker prices
    }

    /**
     * CalculateStockPrice method calculates Stock Price based on trades recorded in past 15 minutes.
     * @param stockSymbol name of stock selected by customer
     */
    @Override
    public void calculateStockPrice(String stockSymbol) {
        getTradesForGivenTime(stockSymbol, 15);
        stocks = StaticDatabase.getStocksDB(); //get current stocks from database after trades for 15 minutes were recorded
        double sumTradePrices = 0;
        double sumQuantity = 0;
        double stockPrice;
        System.out.println("\nStock Symbol\t Stock Price");
        for(Stock stock : stocks) {
            if(stock.getStockSymbol().equals(stockSymbol)) {
                //sum all the trade prices for the last 15 minutes calculated in tgetTradesForGivenTime method
                for(Trade trade : stock.getTrades()) {
                    System.out.println("\t" + stock.getStockSymbol() + "\t\t\t" + trade.getTimestamp() + "\t   " + trade
                            .getSharesQuantity
                                    () +
                            "\t\t   " + trade.getIndicator().toString() + "\t\t\t " + new DecimalFormat("#.##").format(trade
                            .getPrice()));
                    sumTradePrices += trade.getPrice();
                    sumQuantity += trade.getSharesQuantity();
                }
                stockPrice = (sumTradePrices * sumQuantity) / sumQuantity;
                System.out.println("\t" + stock.getStockSymbol() + "\t\t\t   " +
                        new DecimalFormat("#.##").format(stockPrice));
                break;
            }
        }
    }

    /**
     *
     * @param stockSymbol name of stock selected by customer
     * @param time variable represents the time where trades will be recorded for a fixed 15 minutes time.
     */
    private void getTradesForGivenTime(String stockSymbol, int time) {
        stocks = StaticDatabase.getStocksDB(); //get current stocks with their current values from DB at database package
        for(Stock stock : stocks) {
            if(stock.getStockSymbol().equals(stockSymbol)) {
                for(int i = 0; i < time; i++) {
                    Trade trade = new Trade();
                    trade.setTimestamp(LocalTime.now());
                    trade.setSharesQuantity((int)(Math.random() * 1000 + 1));
                    trade.setPrice(Math.random() * 20 + 1);
                    if((int)(Math.random() * 2) == 0) trade.setIndicator(Trade.Indicator.sell);
                    else trade.setIndicator(Trade.Indicator.buy);
                    stock.addTrade(trade);
                }
                break;
            }
        }
        StaticDatabase.setStocksDB(stocks); //update Database after inserting 15 trades generated, 1 trade per minute
    }

    /**
     * calculateGBCE method calculates the geometric mean of prices for all stocks
     */
    @Override
    public void calculateGBCE() {
        stocks = StaticDatabase.getStocksDB(); //get current stocks with their current values from DB at database package
        double productTradePrices = 1;
        double geoMean;
        int count = 0;
        System.out.println("\nStock Symbol\t" + "Geometric Mean");
        for(Stock stock : stocks) {
            for(Trade trade : stock.getTrades()) {
                productTradePrices *= trade.getPrice();
                count++;
            }
            geoMean = Math.pow(productTradePrices, 1.0/ count);
            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t   " + new DecimalFormat("#.##").format(geoMean));
        }
    }

}
