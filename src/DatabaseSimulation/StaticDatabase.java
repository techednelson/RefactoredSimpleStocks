package DatabaseSimulation;

import Model.Stock;

import java.util.ArrayList;

public class StaticDatabase {
    private static ArrayList<Stock> stocks;

    public static ArrayList<Stock> getStocks() { return stocks; }

    public static ArrayList<Stock> createDatabase() {
         stocks = new ArrayList<Stock>();
         Stock Tea = new Stock("TEA", "Common", 0, 0, 100);
         stocks.add(Tea);
         Stock Pop = new Stock("Pop", "Common", 8, 0, 100);
         stocks.add(Pop);
         Stock Ale = new Stock("Ale", "Common", 23, 0, 60);
         stocks.add(Ale);
         Stock Gin = new Stock("Gin", "Preferred", 8, 2, 100);
         stocks.add(Gin);
         Stock Joe = new Stock("Joe", "Common", 13, 0, 250);
         stocks.add(Joe);

         System.out.println("Stock Symbol     Type     Last Dividend     Fixed Dividend     Par Value\n");
         for (Stock stock : stocks) {
             if (stock.getLastDividend() > 9) {
                 System.out.println("    " + stock.getStockSymbol() + "         " + stock.getType() + "           " + stock
                         .getLastDividend()
                         + "               " + stock.getFixedDividend() + "           " + stock.getParValue() + "\n");
             } else if (stock.getType().equals("Preferred")) {
                 System.out.println("    " + stock.getStockSymbol() + "         " + stock.getType() + "        " + stock
                         .getLastDividend() +
                         "                " + stock.getFixedDividend() + "%          " + stock.getParValue() +  "\n");
             } else {
                 System.out.println("    " + stock.getStockSymbol() + "         " + stock.getType() + "           " + stock
                         .getLastDividend()
                         + "                " + stock.getFixedDividend() + "           " + stock.getParValue() +  "\n");
             }
         }
         return stocks;
    }
}

