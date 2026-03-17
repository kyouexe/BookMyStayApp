import java.util.*;

/**

 * ===============================================================
 * CLASS – Reservation
 * ===============================================================
 *
 * @version 8.0
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
 * CLASS – BookingHistory
 * ===============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * @version 8.0
 */
class BookingHistory {

    /** List that stores confirmed reservations. */
    private List<Reservation> confirmedReservations;

    /** Initializes an empty booking history. */
    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    /**

     * Adds a confirmed reservation to booking history.
     *
     * @param reservation confirmed booking
     */
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    /**

     * Returns all confirmed reservations.
     *
     * @return list of reservations
     */
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

/**

 * ===============================================================
 * CLASS – BookingReportService
 * ===============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * @version 8.0
 */
class BookingReportService {

    /**

     * Displays a summary report
     * of all confirmed bookings.
     *
     * @param history booking history
     */
    public void generateReport(BookingHistory history) {

        System.out.println("Booking Report\n");

        List<Reservation> reservations = history.getConfirmedReservations();

        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : reservations) {
            System.out.println(
                    "Guest: " + r.getGuestName() +
                            " | Room Type: " + r.getRoomType()
            );
        }
    }
}

/**

 * ===============================================================
 * MAIN CLASS – UseCase8BookingHistoryReport
 * ===============================================================
 *
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

  ```
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history);
  ```

    }
}
