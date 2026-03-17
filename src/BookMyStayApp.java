import java.util.*;

/**

 * ===============================================================
 * CLASS – AddOnService
 * ===============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * Represents an optional service that can be added
 * to a confirmed reservation.
 *
 * @version 7.0
 */
class AddOnService {

    /** Name of the service. */
    private String serviceName;

    /** Cost of the service. */
    private double cost;

    /**

     * Creates a new add-on service.
     *
     * @param serviceName name of the service
     * @param cost cost of the service
     */
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    /** @return service name */
    public String getServiceName() {
        return serviceName;
    }

    /** @return service cost */
    public double getCost() {
        return cost;
    }
}

/**

 * ===============================================================
 * CLASS – AddOnServiceManager
 * ===============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * Manages optional services associated with reservations.
 *
 * @version 7.0
 */
class AddOnServiceManager {

    /**

     * Maps reservation ID to selected services.
     *
     * Key   -> Reservation ID
     * Value -> List of selected services
     */
    private Map<String, List<AddOnService>> servicesByReservation;

    /** Initializes the service manager. */
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    /**

     * Attaches a service to a reservation.
     *
     * @param reservationId confirmed reservation ID
     * @param service add-on service
     */
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    /**

     * Calculates total add-on cost for a reservation.
     *
     * @param reservationId reservation ID
     * @return total service cost
     */
    public double calculateTotalServiceCost(String reservationId) {

        List<AddOnService> services =
                servicesByReservation.getOrDefault(reservationId, new ArrayList<>());

        double total = 0;

        for (AddOnService service : services) {
            total += service.getCost();
        }

        return total;
    }
}

/**

 * ===============================================================
 * MAIN CLASS – UseCase7AddOnServiceSelection
 * ===============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

  ```
        System.out.println("Add-On Service Selection\n");

        // Assume reservation already confirmed
        String reservationId = "S-1";

        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 1500);
        AddOnService pickup = new AddOnService("Airport Pickup", 800);

        // Attach services
        serviceManager.addService(reservationId, breakfast);
        serviceManager.addService(reservationId, spa);
        serviceManager.addService(reservationId, pickup);

        // Display selected services
        System.out.println("Services added for Reservation ID: " + reservationId);

        System.out.println("- " + breakfast.getServiceName());
        System.out.println("- " + spa.getServiceName());
        System.out.println("- " + pickup.getServiceName());

        // Calculate total cost
        double totalCost = serviceManager.calculateTotalServiceCost(reservationId);

        System.out.println("\nTotal Add-On Cost: " + totalCost);
  ```

    }
}
