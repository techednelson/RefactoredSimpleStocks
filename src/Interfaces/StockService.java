package Interfaces;

import Model.Stock;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StockService implements SimpleStockServices {

    @Override
    public void calculateDividendYield(ArrayList<Stock> stocks) {
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
    public void calculatePERatio(ArrayList<Stock> stocks) {
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

    @Override
    public void recordTrade(ArrayList<Stock> stocks) { }

    @Override
    public void calculateStockPrice(ArrayList<Stock> stocks) { }

    @Override
    public void calculateGBCE(ArrayList<Stock> stocks) { }

}
