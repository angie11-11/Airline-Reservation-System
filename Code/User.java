/**
 * Represents a user in the reservation system with an ID, name, password, and a list of their reservations.
 * CS151 Hw2 Solution
 * Instructor: Dr.Kim
 * @author: Angie Do
 * Date: 10/02/2024
 */

import java.util.HashMap;
import java.util.Map;

public class User {
    private final String userId;
    private final String name;
    private final String password;
    private final Map<String, Double> reservations = new HashMap<>();
    private double totalBalance = 0;

     /**
     * Constructs a new User with the given user ID, name, and password.
     * 
     * @param userId The user's ID.
     * @param name The user's name.
     * @param password The user's password.
     */
    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Retrieves all reservations made by this user.
     * 
     * @return A map of seat numbers to reservation prices.
     */
    public Map<String, Double> getReservations() {
        return reservations;
    }

    /**
     * Calculates the total balance due for all reservations made by this user.
     * 
     * @return The total balance due.
     */
    public double getTotalBalance() {
        return totalBalance;
    }

    /**
     * Adds a reservation for the given seat number and price.
     * 
     * @param seatNumber The seat number being reserved.
     * @param price The price of the reservation.
     */
    public void addReservation(String seatNumber, double price) {
        reservations.put(seatNumber, price);
        totalBalance += price;
    }

    /**
     * Cancels a reservation for the given seat number.
     * 
     * @param seatNumber The seat number being canceled.
     */
    public void cancelReservation(String seatNumber) {
        if (reservations.containsKey(seatNumber)) {
            double price = reservations.remove(seatNumber);
            totalBalance -= price;
        }
    }

   
}
