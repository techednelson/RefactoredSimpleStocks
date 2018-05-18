package main;

import services.StockServicesImpl;
import services.TradeServicesImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        StockServicesImpl.loadDatabase();
        while (!exit) {
            printMenu();
            int option;
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println(" You have to enter an Integer. Please run the program again again.");
                continue;
            }
            if (option != 0) {
                StockServicesImpl.printDatabase();
                switch (option) {
                    case 1:
                        String stockSymbol = selectStock();
                        int operation = selectOperation();
                        doStockCalculation(stockSymbol , operation);
                        break;
                    case 2:
                        TradeServicesImpl tradeService = new TradeServicesImpl();
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
            System.out.println("1 - Perform computations for a specific stock.");
            System.out.println("2 - Calculate GBCE for all stocks.");
            System.out.println("3 - Exit \n");
    }

    private static int selectOperation() {
        System.out.println("Select a calculation to perform with the stock: \n");
        System.out.println("1 - Calculate Dividend Yield.");
        System.out.println("2 - P/E Ratio");
        System.out.println("3 - Record a Trade.");
        System.out.println("4 - Calculate Stock Price.");
        System.out.println("5 - <== Back \n");
        int number = 0;
        boolean exit = false;
        while (!exit) {
            try {
                number = Integer.parseInt(br.readLine());
                if(number >= 1 && number <=5) exit = true;
            } catch (IOException e) {
                System.out.println("You must enter an Integer, try again");
                continue;
            }
        }
        return number;
    }

    private static String selectStock() {
        System.out.println("\n Please select a stock.\n");
        System.out.println("1. Tea");
        System.out.println("2. Pop");
        System.out.println("3. Ale");
        System.out.println("4. Gin");
        System.out.println("5. Joe");
        int number;
        boolean exit = false;
        String stock = null;
        while (!exit) {
            try {
                number = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("You must enter an Integer, try again");
                continue;
            }
            if(number == 1) {
                stock = "Tea";
                exit = true;
            } else if(number == 2) {
                stock = "Pop";
                exit = true;
            } else if(number == 3) {
                stock = "Ale";
                exit = true;
            } else if(number == 4) {
                stock = "Gin";
                exit = true;
            } else if(number == 5) {
                stock = "Joe";
                exit = true;
            } else {
                System.out.println("You must enter a number between 1 and 5");
            }
        }
        return stock;
    }

    private static void doStockCalculation(String stockSymbol , int operation) {
        StockServicesImpl.askForTickerPrice(stockSymbol);
        boolean exit = false;
        StockServicesImpl stockService = new StockServicesImpl();
        TradeServicesImpl tradeService = new TradeServicesImpl();
        while(!exit) {
            switch(operation) {
                case 1:
                    stockService.calculateDividendYield(stockSymbol);
                    break;
                case 2:
                    stockService.calculatePERatio(stockSymbol);
                    break;
                case 3:
                    tradeService.recordTrade(stockSymbol);
                    break;
                case 4:
                    tradeService.calculateStockPrice(stockSymbol);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("\nOption entered is not available or doesn't exist.");
                    break;
            }
        }
    }

}
