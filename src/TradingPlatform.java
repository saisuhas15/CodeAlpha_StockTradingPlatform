import java.util.ArrayList;

public class TradingPlatform implements java.io.Serializable {

private static final long serialVersionUID = 2925267662506278964L;
    private ArrayList<Stock> stocks;
    private ArrayList<Transaction> transactions;

    private User user;
    private Portfolio portfolio;

    // Constructor
    public TradingPlatform(User user) {

        this.user = user;

        stocks = new ArrayList<>();
        transactions = new ArrayList<>();

        portfolio = new Portfolio();

        // Adding sample stocks
        stocks.add(new Stock(1, "TCS", "TCS", 3500));
        stocks.add(new Stock(2, "Infosys", "INFY", 1800));
        stocks.add(new Stock(3, "Reliance", "RELIANCE", 2900));
        stocks.add(new Stock(4, "HDFC Bank", "HDFCBANK", 1700));
        stocks.add(new Stock(5, "Wipro", "WIPRO", 550));
    }

    // Display market data
    public void displayMarket() {

        System.out.println("\n========== STOCK MARKET ==========");
        System.out.println("ID\tCompany\t\tSymbol\t\tPrice");

        for (Stock stock : stocks) {
            stock.displayStock();
        }
    }

    // Find stock using symbol
    private Stock findStock(String symbol) {

        for (Stock stock : stocks) {

            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }

    // Search stocks
    public void searchStock(String keyword) {

        boolean found = false;

        System.out.println("\n========== STOCK SEARCH ==========");

        for (Stock stock : stocks) {

            if (stock.getSymbol().equalsIgnoreCase(keyword) ||
                stock.getCompanyName().equalsIgnoreCase(keyword)) {

                stock.displayStock();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Stock not found!");
        }
    }

    // Buy stocks
    public void buyStocks(String symbol, int quantity) {

        Stock stock = findStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        double total = stock.getPrice() * quantity;

        if (user.deductFunds(total)) {

            portfolio.buyStock(symbol, quantity, stock.getPrice());

            transactions.add(
                new Transaction("BUY", symbol, quantity, stock.getPrice())
            );

            System.out.println("Stock purchased successfully!");
            System.out.println("Total Amount: Rs." + total);
        }
    }

    // Sell stocks
    public void sellStocks(String symbol, int quantity) {

        Stock stock = findStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        if (portfolio.sellStock(symbol, quantity)) {

            double total = stock.getPrice() * quantity;

            user.addFunds(total);

            transactions.add(
                new Transaction("SELL", symbol, quantity, stock.getPrice())
            );

            System.out.println("Stock sold successfully!");
            System.out.println("Amount Received: Rs." + total);
        }
    }

    // Display portfolio
    public void displayPortfolio() {

        portfolio.displayPortfolio();
    }

    // Display wallet balance
    public void displayBalance() {

        System.out.println(
            "Wallet Balance: Rs." + user.getBalance()
        );
    }

    // Display transaction history
    public void displayTransactions() {

        System.out.println("\n======= TRANSACTION HISTORY =======");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }

    // Display user information
    public void displayUserDetails() {

        user.displayUser();
    }

    // Calculate Profit and Loss
    public void displayProfitLoss() {

        double totalInvestment = 0;
        double currentValue = 0;

        System.out.println("\n========== PROFIT & LOSS ==========");

        for (Stock stock : stocks) {

            int quantity = portfolio.getQuantity(stock.getSymbol());

            if (quantity > 0) {

                double investment =
                    portfolio.getInvestment(stock.getSymbol());

                double marketValue =
                    quantity * stock.getPrice();

                totalInvestment += investment;
                currentValue += marketValue;

                System.out.println("\nStock: " + stock.getSymbol());
                System.out.println("Quantity: " + quantity);
                System.out.println("Investment: Rs." + investment);
                System.out.println("Current Value: Rs." + marketValue);

                System.out.println(
                    "Profit/Loss: Rs." + (marketValue - investment)
                );
            }
        }

        double profitLoss = currentValue - totalInvestment;

        System.out.println("\nTotal Investment: Rs." + totalInvestment);
        System.out.println("Current Value: Rs." + currentValue);
        System.out.println("Total Profit/Loss: Rs." + profitLoss);

        if (totalInvestment > 0) {

            double percentage =
                (profitLoss / totalInvestment) * 100;

            System.out.println("Returns: " + percentage + "%");
        }

        if (profitLoss > 0) {

            System.out.println("Status: PROFIT");

        } else if (profitLoss < 0) {

            System.out.println("Status: LOSS");

        } else {

            System.out.println("Status: NO PROFIT / NO LOSS");
        }
    }

    // Update stock prices
    public void updateStockPrice(String symbol, double newPrice) {

        Stock stock = findStock(symbol);

        if (stock == null) {

            System.out.println("Stock not found!");
            return;
        }

        if (newPrice <= 0) {

            System.out.println("Invalid stock price!");
            return;
        }

        double oldPrice = stock.getPrice();

        stock.setPrice(newPrice);

        System.out.println("\nStock price updated successfully!");

        System.out.println("Stock: " + stock.getSymbol());

        System.out.println("Previous Price: Rs." + oldPrice);

        System.out.println("New Price: Rs." + newPrice);
    }
}