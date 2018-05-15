package Main;

import DatabaseSimulation.StaticDatabase;
import Exception.WrongFormatInput;
import Interfaces.*;
import Model.Stock;

import java.io.*;
import java.util.ArrayList;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Stock> stocks = StaticDatabase.createDatabase();

    public static void main(String[] args) throws IOException {
        boolean exit = false;

        while (!exit) {
            int option;
            printMenu();
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                throw new WrongFormatInput(" You have to enter an Integer. Please start again.");
            }
            if (option != 0) {
                switch (option) {
                    case 1:
                        startTradeSimulation();
                        break;
                    case 2:
                        System.out.println("\nGoodbye! To play again, run the program");
                        exit = true;
                        break;
                    default:
                        System.out.println("\nOption entered is not available or doesn't exist.");
                        break;
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n Welcome to Super Simple Stock, please choose one option entering 1 or 2. \n");
        System.out.println("1 - Record a trade and Calculate the GBCE for all stocks.");
        System.out.println("2 - Exit \n");
    }

    private static void startTradeSimulation() throws IOException {
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
        System.out.println("\n");
        StockService stockService = new StockService();
        stockService.calculateDividendYield(stocks);
        stockService.calculatePERatio(stocks);
        TradeService tradeService = new TradeService();
        tradeService.recordTrade(stocks);
    }

}
