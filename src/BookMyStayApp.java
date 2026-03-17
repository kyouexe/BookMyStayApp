import java.io.*;
import java.util.*;

/**

 * ===============================================================
 * CLASS – RoomInventory
 * ===============================================================
 *
 * @version 12.0
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/**

 * ===============================================================
 * CLASS – FilePersistenceService
 * ===============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * @version 12.0
 */
class FilePersistenceService {

    /**

     * Saves room inventory state to a file.
     * Format: roomType=availableCount
     */
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

    ```
            for (Map.Entry<String, Integer> entry :
                    inventory.getRoomAvailability().entrySet()) {

                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");
    ```

        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**

     * Loads room inventory state from a file.
     */
    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No saved data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

    ```
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    inventory.updateAvailability(roomType, count);
                }
            }

            System.out.println("Inventory loaded successfully.");
    ```

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}

/**

 * ===============================================================
 * MAIN CLASS – UseCase12DataPersistenceRecovery
 * ===============================================================
 *
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

  ```
        System.out.println("Data Persistence & Recovery\n");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        String filePath = "inventory.txt";

        // Load existing data
        persistenceService.loadInventory(inventory, filePath);

        // Simulate changes
        inventory.updateAvailability("Single", 2);
        inventory.updateAvailability("Double", 1);

        System.out.println("Updated Inventory: " + inventory.getRoomAvailability());

        // Save updated data
        persistenceService.saveInventory(inventory, filePath);
  ```

    }
}
