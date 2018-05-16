package Interfaces;

import DatabaseSimulation.StaticDatabase;
import Model.*;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;

public class SimpleTradeService implements TradeServices {
    private static ArrayList<Stock> stocks;

    @Override
    public void recordTrade() {
        stocks = StaticDatabase.getStocksDB(); //get current stocks with their current values from DB
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
                indicator.trim();
                System.out.println("\t" + stock.getStockSymbol() + "\t\t\t" + trade.getTimestamp() + "\t   " + trade
                        .getSharesQuantity
                        () +
                        "\t\t   " + indicator + "\t\t\t " + new DecimalFormat("#.##").format(trade.getPrice()));
                stock.setTrades(trade);
                //each increment simulates a minutes until achieve the 15 minutes
                for(int i = 1; i < 15; i++) {
                    trade = new Trade();
                    trade.setSharesQuantity((int)(Math.random() * 1000 + 1));
                    trade.setPrice(Math.random() * 20 + 1);
                    stock.setTrades(trade);
                }
            }
        }
        StaticDatabase.setStocksDB(stocks); //update Database after inserting ticker prices
    }

    @Override
    public void calculateStockPrice() {
        stocks = StaticDatabase.getStocksDB();
        double sumTradePrices = 0;
        double sumQuantity = 0;
        double stockPrice;
        System.out.println("\nStock Symbol\t Stock Price");
        for(Stock stock : stocks) {
            // sum all the trade prices for the last 15 minutes calculated in the recordTrade method
            for(Trade trade : stock.getTrades()) {
                sumTradePrices += trade.getPrice();
                sumQuantity += trade.getSharesQuantity();
            }
            stockPrice = (sumTradePrices * sumQuantity) / sumQuantity;
            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t   " +
                    new DecimalFormat("#.##").format(stockPrice));
        }
    }

    @Override
    public void calculateGBCE() {
        stocks = StaticDatabase.getStocksDB();
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
