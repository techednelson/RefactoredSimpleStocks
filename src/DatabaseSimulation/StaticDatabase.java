package DatabaseSimulation;

import Model.Stock;

import java.util.ArrayList;

public class StaticDatabase {

    private static ArrayList<Stock> stocksDB = new ArrayList<>();

    public static ArrayList<Stock> getStocksDB() { return stocksDB; }

    public static void setStocksDB(ArrayList<Stock> stocksDB) { StaticDatabase.stocksDB = stocksDB; }

    public static void createDatabase() {
        Stock Tea = new Stock("TEA", "Common", 0, 0, 100);
        stocksDB.add(Tea);
        Stock Pop = new Stock("Pop", "Common", 8, 0, 100);
        stocksDB.add(Pop);
        Stock Ale = new Stock("Ale", "Common", 23, 0, 60);
        stocksDB.add(Ale);
        Stock Gin = new Stock("Gin", "Preferred", 8, 2, 100);
        stocksDB.add(Gin);
        Stock Joe = new Stock("Joe", "Common", 13, 0, 250);
        stocksDB.add(Joe);

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

