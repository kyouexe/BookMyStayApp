import java.util.*;

/**

 * ===============================================================
 * CLASS – InvalidBookingException
 * ===============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * @version 9.0
 */
class InvalidBookingException extends Exception {

    /**

     * Creates an exception with a descriptive error message.
     *
     * @param message error description
     */
    public InvalidBookingException(String message) {
        super(message);
    }
}

/**

 * ===============================================================
 * CLASS – Reservation
 * ===============================================================
 *
 * @version 9.0
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/**

 * ===============================================================
 * CLASS – RoomInventory
 * ===============================================================
 *
 * @version 9.0
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
}

/**

 * ===============================================================
 * CLASS – BookingRequestQueue
 * ===============================================================
 *
 * @version 9.0
 */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }
}

/**

 * ===============================================================
 * CLASS – ReservationValidator
 * ===============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * @version 9.0
 */
class ReservationValidator {

    /**

     * Validates booking input provided by the user.
     *
     * @throws InvalidBookingException if validation fails
     */
    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (!availability.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (availability.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for selected type.");
        }
    }
}

/**

 * ===============================================================
 * MAIN CLASS – UseCase9ErrorHandlingValidation
 * ===============================================================
 *
 * @version 9.0
 */
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

  ```
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {

            System.out.print("Enter Guest Name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter Room Type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(guestName, roomType, inventory);

            // If valid, add to queue
            bookingQueue.addRequest(new Reservation(guestName, roomType));

            System.out.println("Booking request accepted.");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
  ```

    }
}
