package SERVICES;

import DAO.*;
import Model.*;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;

public class SimpleTradeService implements TradeServices {
    private SimpleStocksDAO connectDB = new SimpleStocksDAOImpl();
    private ArrayList<Stock> stocks;

    @Override
    public void recordTrade() {
        stocks = connectDB.getStocksDB();//get current stocks with their current values from DB
        Trade trade;
        String indicator;
        System.out.println("\nStock Symbol\t Timestamp\t\t Shares\t\t Buy/Sell\t\t Price");
        for(Stock stock : stocks) {
            if(stock != null) {
                trade = new Trade();
                trade.setTimestamp(LocalTime.now());
                trade.setSharesQuantity((int)(Math.random() * 1000 + 1));
                trade.setPrice(Math.random() * 20 + 1);
                indicator = (int)(Math.random() * 2) == 0 ? Trade.indicator.sell.toString() : Trade.indicator.buy.toString();
                System.out.println("\t" + stock.getStockSymbol() + "\t\t\t" + trade.getTimestamp() + "\t   " + trade
                        .getSharesQuantity
                        () +
                        "\t\t   " + indicator.trim() + "\t\t\t " + new DecimalFormat("#.##").format(trade.getPrice()));
                stock.setTrades(trade);
                for(int i = 1; i < 15; i++) {
                    trade = new Trade();
                    trade.setSharesQuantity((int)(Math.random() * 1000 + 1));
                    trade.setPrice(Math.random() * 20 + 1);
                    stock.setTrades(trade);
                }
            }
        }
        connectDB.setStocksDB(stocks); //update Database after inserting ticker prices
    }

    @Override
    public void calculateStockPrice() {
        stocks = connectDB.getStocksDB();
        double sumTradePrices = 0;
        double sumQuantity = 0;
        double stockPrice;
        System.out.println("\nStock Symbol\t Stock Price");
        for(Stock stock : stocks) {
            for(Trade trade : stock.getTrades()) {
                sumTradePrices += trade.getPrice();
                sumQuantity += trade.getSharesQuantity();;
            }
            stockPrice = (sumTradePrices * sumQuantity) / sumQuantity;
            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t   " +
                    new DecimalFormat("#.##").format(stockPrice));
        }
    }

    @Override
    public void calculateGBCE() {
        stocks = connectDB.getStocksDB();
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
