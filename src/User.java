public class User implements java.io.Serializable {

    private int userId;
    private String username;
    private double balance;

    // Constructor
    public User(int userId, String username, double balance) {
        this.userId = userId;
        this.username = username;
        this.balance = balance;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    // Add money to wallet
    public void addFunds(double amount) {

        if (amount > 0) {
            balance += amount;
            System.out.println("Funds added successfully!");
        } else {
            System.out.println("Invalid amount!");
        }
    }

    // Deduct money from wallet
    public boolean deductFunds(double amount) {

        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }

        System.out.println("Insufficient balance!");
        return false;
    }

    // Display user details
    public void displayUser() {

        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("Wallet Balance: Rs." + balance);
    }
}
