package Main;

import DatabaseSimulation.StaticDatabase;
import Exception.WrongFormatInput;
import Interfaces.StockSimpleService;
import Model.Stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                        recordTrade();
                        break;
                    case 2:
                        calculateGBCE();
                        break;
                    case 3:
                        System.out.println("\n Goodbye! To play again, run the program");
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
        System.out.println("\n\n Welcome to Super Simple Stock, please choose one option entering 1,2 or 3 accordingly. \n");
        System.out.println("1 - Record a trade.");
        System.out.println("2 - Calculate the GBCE.");
        System.out.println("3 - Exit \n");
    }

    private static void recordTrade() throws IOException {
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
        StockSimpleService.calculateDividendYield(stocks);
    }

    private static void calculateGBCE() {


    }


}
