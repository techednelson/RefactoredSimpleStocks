package Interfaces;

import Model.Stock;

import java.util.ArrayList;

public interface SimpleStockServices {

    void calculateDividendYield(ArrayList<Stock> stocks);

    void calculatePERatio(ArrayList<Stock> stocks);

    void recordTrade(ArrayList<Stock> stocks);

    void calculateStockPrice(ArrayList<Stock> stocks);

    void calculateGBCE(ArrayList<Stock> stocks);

}
