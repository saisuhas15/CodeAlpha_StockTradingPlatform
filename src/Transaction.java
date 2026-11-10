import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements java.io.Serializable {

    private String transactionType;
    private String stockSymbol;
    private int quantity;
    private double price;
    private LocalDateTime dateTime;

    // Constructor
    public Transaction(String transactionType,
                       String stockSymbol,
                       int quantity,
                       double price) {

        this.transactionType = transactionType;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.dateTime = LocalDateTime.now();
    }

    // Calculate transaction amount
    public double getTotalAmount() {
        return quantity * price;
    }

    // Display transaction details
    public void displayTransaction() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("--------------------------------");
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Stock Symbol: " + stockSymbol);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: Rs." + price);
        System.out.println("Total Amount: Rs." + getTotalAmount());
        System.out.println("Date & Time: " + dateTime.format(formatter));
        System.out.println("--------------------------------");
    }
}