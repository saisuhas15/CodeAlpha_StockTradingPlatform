import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("     STOCK TRADING PLATFORM");
        System.out.println("================================");

        // Load previous data
        TradingPlatform platform = DataStore.loadData();

        // Register new user if no saved data exists
        if (platform == null) {

            System.out.println("\nUser Registration");

            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Initial Wallet Balance: ");
            double balance = sc.nextDouble();

            User user = new User(userId, username, balance);

            platform = new TradingPlatform(user);

        } else {

            System.out.println("Welcome back!");
        }

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("       TRADING DASHBOARD");
            System.out.println("================================");

            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Wallet Balance");
            System.out.println("6. Transaction History");
            System.out.println("7. User Details");
            System.out.println("8. Profit / Loss");
            System.out.println("9. Search Stocks");
            System.out.println("10. Update Stock Price");
            System.out.println("11. Exit");

            System.out.println("================================");
            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    platform.displayMarket();
                    break;

                case 2:

                    platform.displayMarket();

                    System.out.print("Enter Stock Symbol: ");
                    String buySymbol = sc.next();

                    System.out.print("Enter Quantity: ");
                    int buyQuantity = sc.nextInt();

                    platform.buyStocks(buySymbol, buyQuantity);
                    break;

                case 3:

                    platform.displayPortfolio();

                    System.out.print("Enter Stock Symbol: ");
                    String sellSymbol = sc.next();

                    System.out.print("Enter Quantity: ");
                    int sellQuantity = sc.nextInt();

                    platform.sellStocks(sellSymbol, sellQuantity);
                    break;

                case 4:
                    platform.displayPortfolio();
                    break;

                case 5:
                    platform.displayBalance();
                    break;

                case 6:
                    platform.displayTransactions();
                    break;

                case 7:
                    platform.displayUserDetails();
                    break;

                case 8:
                    platform.displayProfitLoss();
                    break;

                case 9:
                    System.out.print("Enter Stock Symbol or Company Name: ");
                    String searchKeyword = sc.next();
                    platform.searchStock(searchKeyword);
                    break;
                    case 10:
                        platform.displayMarket();
                        System.out.print("Enter Stock Symbol: ");
                        String updateSymbol = sc.next();
                        System.out.print("Enter New Stock Price: ");
                        double newPrice = sc.nextDouble();
                        platform.updateStockPrice(updateSymbol, newPrice);
                        break;
                    case 11:
                        System.out.println("Thank you for using our platform!");
                        break;

                default:
                    System.out.println("Invalid choice!");
            }

            // Automatically save data
            DataStore.saveData(platform);

        } while (choice != 11);

        sc.close();
    }
}