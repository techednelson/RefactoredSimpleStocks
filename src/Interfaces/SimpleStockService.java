package Interfaces;

import DatabaseSimulation.StaticDatabase;
import Exception.WrongFormatInput;
import Model.Stock;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SimpleStockService implements StockServices {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Stock> stocks;

    public static void askForTickerPrice() throws IOException {
        StaticDatabase.createDatabase();
        stocks = StaticDatabase.getStocksDB(); //get current stocks with their current values from DB

        for (Stock stock : stocks) {
            try {
                System.out.println("\nStock Symbol " + stock.getStockSymbol() + ": ");
                System.out.print("Enter the ticker price: ");
                stock.setTickerPrice(Double.parseDouble(br.readLine()));
            } catch (NumberFormatException e) {
                throw new WrongFormatInput("You entered a String in stead of an integer, please start again!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StaticDatabase.setStocksDB(stocks); //update Database after inserting ticker prices
    }

    @Override
    public void calculateDividendYield() {
        stocks = StaticDatabase.getStocksDB();
        double dividendYield;
        System.out.println("\nStock Symbol\t Dividend Yield");
        for(Stock stock : stocks) {
            if(stock != null) {
                switch(stock.getType()) {
                    case "Preferred":
                        if(stock.getFixedDividend() != 0) {
                            dividendYield = stock.getFixedDividend() * stock.getParValue() / stock.getTickerPrice();
                            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t\t " +
                                    new DecimalFormat("#.##").format(dividendYield));
                        } else {
                            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t\t 0");
                        }
                        break;
                    case "Common":
                        if(stock.getFixedDividend() != 0) {
                            dividendYield = stock.getLastDividend() / stock.getTickerPrice();
                            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t\t " +
                                    new DecimalFormat("#.##").format(dividendYield));
                        } else {
                            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t\t 0");
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void calculatePERatio() {
        stocks = StaticDatabase.getStocksDB();
        double peRatio;
        System.out.println("\nStock Symbol\t P/E Ratio");
        for(Stock stock : stocks) {
            if(stock != null) {
                peRatio = stock.getLastDividend() != 0 ? stock.getTickerPrice() / stock.getLastDividend() : 0;
                System.out.println("\t" + stock.getStockSymbol() + "\t\t\t\t " +
                        new DecimalFormat("#.##").format(peRatio));
            }
        }
    }

}
