import java.util.HashMap;
import java.util.Map;

public class Portfolio implements java.io.Serializable {

    private HashMap<String, Integer> holdings;
    private HashMap<String, Double> investments;

    // Constructor
    public Portfolio() {
        holdings = new HashMap<>();
        investments = new HashMap<>();
    }

    // Buy stocks
    public void buyStock(String symbol, int quantity, double price) {

        if (quantity <= 0 || price <= 0) {
            System.out.println("Invalid stock details!");
            return;
        }

        holdings.put(
            symbol,
            holdings.getOrDefault(symbol, 0) + quantity
        );

        investments.put(
            symbol,
            investments.getOrDefault(symbol, 0.0) + (quantity * price)
        );

        System.out.println("Stock added to portfolio!");
    }

    // Sell stocks
    public boolean sellStock(String symbol, int quantity) {

        int currentQuantity = holdings.getOrDefault(symbol, 0);

        if (quantity > 0 && currentQuantity >= quantity) {

            double currentInvestment = investments.get(symbol);
            double averagePrice = currentInvestment / currentQuantity;

            int remaining = currentQuantity - quantity;

            if (remaining == 0) {
                holdings.remove(symbol);
                investments.remove(symbol);
            } else {
                holdings.put(symbol, remaining);

                investments.put(
                    symbol,
                    averagePrice * remaining
                );
            }

            System.out.println("Stock sold successfully!");
            return true;
        }

        System.out.println("Insufficient stocks!");
        return false;
    }

    // Get stock quantity
    public int getQuantity(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    // Get investment amount
    public double getInvestment(String symbol) {
        return investments.getOrDefault(symbol, 0.0);
    }

    // Display portfolio
    public void displayPortfolio() {

        System.out.println("\n========== MY PORTFOLIO ==========");

        if (holdings.isEmpty()) {
            System.out.println("No stocks purchased yet.");
            return;
        }

        System.out.println("Symbol\tQuantity\tInvestment");

        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {

            System.out.println(
                entry.getKey() + "\t" +
                entry.getValue() + "\t\tRs." +
                investments.get(entry.getKey())
            );
        }
    }
}