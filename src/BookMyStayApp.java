import java.util.*;

/**

 * ===============================================================
 * CLASS – Reservation
 * ===============================================================
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

/**

 * ===============================================================
 * CLASS – BookingRequestQueue
 * ===============================================================
 */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean isEmpty() {
        return requestQueue.isEmpty();
    }
}

/**

 * ===============================================================
 * CLASS – RoomInventory
 * ===============================================================
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability = new HashMap<>();

    public RoomInventory() {
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 2);
        roomAvailability.put("Suite", 1);
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
 * CLASS – RoomAllocationService
 * ===============================================================
 */
class RoomAllocationService {

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

  ```
        String type = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(type) > 0) {
            inventory.updateAvailability(type, availability.get(type) - 1);
            System.out.println(Thread.currentThread().getName() +
                    " allocated " + type + " room to " + reservation.getGuestName());
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " FAILED for " + reservation.getGuestName() +
                    " (" + type + " not available)");
        }
  ```

    }
}

/**

 * ===============================================================
 * CLASS – ConcurrentBookingProcessor
 * ===============================================================
 */
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService) {

  ```
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
  ```

    }

    @Override
    public void run() {

  ```
        while (true) {

            Reservation reservation;

            // synchronized queue access
            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) break;
                reservation = bookingQueue.getNextRequest();
            }

            // synchronized inventory update
            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
  ```

    }
}

/**

 * ===============================================================
 * MAIN CLASS – UseCase11ConcurrentBookingSimulation
 * ===============================================================
 */
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

  ```
        System.out.println("Concurrent Booking Simulation\n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Add multiple requests
        bookingQueue.addRequest(new Reservation("A", "Single"));
        bookingQueue.addRequest(new Reservation("B", "Single"));
        bookingQueue.addRequest(new Reservation("C", "Single"));
        bookingQueue.addRequest(new Reservation("D", "Double"));
        bookingQueue.addRequest(new Reservation("E", "Suite"));

        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Create threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService),
                "Thread-1"
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService),
                "Thread-2"
        );

        // Start threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        System.out.println("\nFinal Inventory: " + inventory.getRoomAvailability());
  ```

    }
}
