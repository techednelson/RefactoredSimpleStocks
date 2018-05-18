package services;

public interface TradeServices {

    void recordTrade(String stockSymbol);

    void calculateStockPrice(String stockSymbol);

    void calculateGBCE();

}
