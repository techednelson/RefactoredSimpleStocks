package DAO;

import Model.Stock;

import java.util.ArrayList;

public interface SimpleStocksDAO {
    boolean open();
    void close();
    ArrayList<Stock> getStocksDB();
    void setStocksDB(ArrayList<Stock> stocksDB);
    void createDatabase();
}
