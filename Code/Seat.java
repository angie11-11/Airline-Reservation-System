/**
 * Represent a seat with seat number, a seat class, and is associated with a User who reserves it.
 * CS151 Hw2 Solution
 * Instructor: Dr.Kim
 * @author: Angie Do
 * Date: 10/02/2024
 */

 
public class Seat {
    private final String seatNumber;
    private final String seatClass;
    private final User user;

    /**
     * Constructs a new Seat with the given seat number, seat class, and the user who reserved it.
     * 
     * @param seatNumber The seat number.
     * @param seatClass The seat class (First Class, Economy Plus, Economy).
     * @param user The user who reserved the seat.
     */
    public Seat(String seatNumber, String seatClass, User user) {
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.user = user;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return seatNumber + " (" + seatClass + ")";
    }
}
