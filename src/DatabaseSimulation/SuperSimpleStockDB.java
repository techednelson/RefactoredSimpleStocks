package DatabaseSimulation;

import Model.Stock;

import java.sql.*;
import java.util.ArrayList;

public class SuperSimpleStockDB {
    private static ArrayList<Stock> stocksDB = new ArrayList<>();

    public static ArrayList<Stock> getStocksDB() { return stocksDB; }
    public static void setStocksDB(ArrayList<Stock> stocksDB) { SuperSimpleStockDB.stocksDB = stocksDB; }

    private final String URL_DB = "jdbc:postgresql://localhost/SuperSimpleStockDB";
    private final String USER = "postgres";
    private final String PASSWORD = "";

    private Connection conn;

    public boolean open() {
        this.conn = null;
        try {
            this.conn = DriverManager.getConnection(URL_DB, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL server successfully!");
            return true;
        } catch(SQLException e) {
            System.out.println("Couldn't connect to PostgreSQL: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(this.conn != null) {
                this.conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Could't close connection: " + e.getMessage());
        }
    }

    public void retrieveDatabase() {
        try(Statement statement = this.conn.createStatement();
            ResultSet results = statement.executeQuery("select * from Stocks")) {
            while (results.next()) {
                Stock stock = new Stock (
                        results.getString("stock_symbol"),
                        results.getString("stock_type"),
                        results.getDouble("last_dividend"),
                        results.getDouble("fixed_dividend"),
                        results.getInt("par_value")
                );
                stocksDB.add(stock);
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }

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

