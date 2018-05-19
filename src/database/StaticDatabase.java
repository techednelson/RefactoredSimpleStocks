package database;

import model.Stock;
import model.Trade;

import java.time.LocalTime;
import java.util.ArrayList;

public class StaticDatabase {

    private static ArrayList<Stock> stocksDB = new ArrayList<>();
    public static ArrayList<Stock> getStocksDB() { return stocksDB; }
    public static void setStocksDB(ArrayList<Stock> stocksDB) { StaticDatabase.stocksDB = stocksDB; }

    private static ArrayList<Trade> tradesDB = new ArrayList<>(); //After database is created tradesDB will have 25 trades for starting the app.

    /**
     * createStocks method will create the 5 stocks first and add to each stock an ArrayList that will create 5
     * trades each time createTrades method is called.
     */
    public static void createDatabase() {
        createStocks();
        createTrades();
        int count = 1;
        for(int i = 0; i < 5; i++) {// 5 trades from tradesDB are add to each Stock ArrayList for trades
            for(int j = count; j <= 25; j++) {
                stocksDB.get(i).addTrade(tradesDB.get(j-1));
                if(j % 5 == 0) {
                    count += 5;
                    break;
                }
            }
        }
    }

   private static void createStocks() {
        Stock Tea = new Stock("Tea", "Common", 0, 0, 100);
        stocksDB.add(Tea);
        Stock Pop = new Stock("Pop", "Common", 8, 0, 100);
        stocksDB.add(Pop);
        Stock Ale = new Stock("Ale", "Common", 23, 0, 60);
        stocksDB.add(Ale);
        Stock Gin = new Stock("Gin", "Preferred", 8, 2, 100);
        stocksDB.add(Gin);
        Stock Joe = new Stock("Joe", "Common", 13, 0, 250);
        stocksDB.add(Joe);
    }

    /**
     * createTrades Method will create 25 trades when is invoked and add to tradesDB ArrayList
     */
    private static void createTrades() {
        for(int i=0; i < 25; i++) {
            Trade trade = new Trade();
            trade.setTimestamp(LocalTime.now());
            trade.setSharesQuantity((int) (Math.random() * 1000 + 1));
            trade.setPrice(Math.random() * 20 + 1);
            if ((int) (Math.random() * 2) == 0) trade.setIndicator(Trade.Indicator.sell);
            else trade.setIndicator(Trade.Indicator.buy);
            tradesDB.add(trade);
        }
    }

    public static void printDatabase() {
        System.out.println("\nStock Symbol     Type     Last Dividend     Fixed Dividend     Par Value\n");
        for (Stock stock : stocksDB) {
            if(stock.getLastDividend() > 9) {
                System.out.println("\t" + stock.getStockSymbol() + "         " + stock.getType() + "           " + stock
                        .getLastDividend()
                        + "               " + stock.getFixedDividend() + "           " + stock.getParValue() + "\n");
            } else if (stock.getType().equals("Preferred")) {
                System.out.println("\t" + stock.getStockSymbol() + "         " + stock.getType() + "        " + stock
                        .getLastDividend() +
                        "                " + stock.getFixedDividend() + "%          " + stock.getParValue() +  "\n");
            } else {
                System.out.println("\t" + stock.getStockSymbol() + "         " + stock.getType() + "           " + stock
                        .getLastDividend()
                        + "                " + stock.getFixedDividend() + "           " + stock.getParValue() +  "\n");
            }
        }

    }

}

