import java.util.*;

/**

 * ===============================================================
 * CLASS – Reservation
 * ===============================================================
 *
 * @version 6.0
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
 * CLASS – BookingRequestQueue
 * ===============================================================
 *
 * @version 6.0
 */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

/**

 * ===============================================================
 * CLASS – RoomInventory
 * ===============================================================
 *
 * @version 6.0
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
 * CLASS – RoomAllocationService
 * ===============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * @version 6.0
 */
class RoomAllocationService {

    /** Prevent duplicate room assignments */
    private Set<String> allocatedRoomIds;

    /** Track allocated rooms by type */
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

  ```
        String roomType = reservation.getRoomType();

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(roomType) == null || availability.get(roomType) <= 0) {
            System.out.println("No available rooms for " + roomType +
                    " requested by " + reservation.getGuestName());
            return;
        }

        String roomId = generateRoomId(roomType);

        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.updateAvailability(roomType,
                availability.get(roomType) - 1);

        System.out.println(
                "Reservation Confirmed: " +
                        reservation.getGuestName() +
                        " -> " + roomType +
                        " Room (ID: " + roomId + ")"
        );
  ```

    }

    private String generateRoomId(String roomType) {

  ```
        int count = assignedRoomsByType
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;

        return roomType.substring(0,1).toUpperCase() + "-" + count;
  ```

    }
}

/**

 * ===============================================================
 * MAIN CLASS – UseCase6RoomAllocation
 * ===============================================================
 *
 * @version 6.0
 */
public class UseCase6RoomAllocation {

    public static void main(String[] args) {

  ```
        System.out.println("Room Allocation\n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();

        RoomAllocationService allocationService = new RoomAllocationService();

        while (bookingQueue.hasPendingRequests()) {

            Reservation request = bookingQueue.getNextRequest();

            allocationService.allocateRoom(request, inventory);
        }
  ```

    }
}
