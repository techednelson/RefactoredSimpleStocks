package Main;

import Exception.WrongFormatInput;
import Interfaces.*;

import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int option;
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                throw new WrongFormatInput(" You have to enter an Integer. Please start again.");
            }
            SimpleStockService.askForTickerPrice();
            if (option != 0) {
                switch (option) {
                    case 1:
                        SimpleStockService stockService = new SimpleStockService();
                        stockService.calculateDividendYield();
                        stockService.calculatePERatio();
                        break;
                    case 2:
                        SimpleTradeService tradeService = new SimpleTradeService();
                        tradeService.recordTrade();
                        tradeService.calculateStockPrice();
                        tradeService.calculateGBCE();
                        break;
                    case 3:
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
            System.out.println("\n Welcome to Super Simple Stock, please choose one option entering 1, 2 or 3." +
                    " \n");
            System.out.println("1 - Calculate Dividend Yield and P/E Ratio.");
            System.out.println("2 - Record a trade, calculate stock price and GBCE");
            System.out.println("3 - Exit \n");
    }

}
