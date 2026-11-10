import java.io.*;

public class DataStore {

    private static final String FILE_PATH = "data/trading_data.dat";

    // Save trading platform data
    public static void saveData(TradingPlatform platform) {

        try (ObjectOutputStream output =
                new ObjectOutputStream(
                    new FileOutputStream(FILE_PATH))) {

            output.writeObject(platform);

            System.out.println("Data saved successfully!");

        } catch (IOException e) {

            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load trading platform data
    public static TradingPlatform loadData() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream input =
                new ObjectInputStream(
                    new FileInputStream(FILE_PATH))) {

            TradingPlatform platform =
                    (TradingPlatform) input.readObject();

            System.out.println("Previous trading data loaded successfully!");

            return platform;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading data: " + e.getMessage());

            return null;
        }
    }
}