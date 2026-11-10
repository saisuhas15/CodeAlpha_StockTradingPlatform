public class Stock implements java.io.Serializable {

    private int stockId;
    private String companyName;
    private String symbol;
    private double price;

    // Constructor
    public Stock(int stockId, String companyName,
                 String symbol, double price) {

        this.stockId = stockId;
        this.companyName = companyName;
        this.symbol = symbol;
        this.price = price;
    }

    // Getters
    public int getStockId() {
        return stockId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    // Update stock price
    public void setPrice(double price) {
        this.price = price;
    }

    // Display stock details
    public void displayStock() {
        System.out.println(
            stockId + "\t" +
            companyName + "\t" +
            symbol + "\t" +
            "Rs." + price
        );
    }
}