/**
 * The FileHandler class manages loading and saving user and reservation data from files.
 * It handles file input/output operations for both users and seats.
 * CS151 Hw2 Solution
 * Instructor: Dr.Kim
 * @author: Angie Do
 * Date: 10/02/2024
 */


import java.io.*;
import java.util.*;

public class FileHandler {

    /**
     * Loads user data from a file and returns a map of user IDs to User objects.
     * 
     * @param file The file to load users from.
     * @return A map of user IDs to User objects.
     */
    public static Map<String, User> loadUsers(File usrFile) {
        Map<String, User> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(usrFile))) {
            reader.readLine();  
            String line;
            System.out.println("Loading users from file: " + usrFile.getName());
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 50) {  
                    String userId = line.substring(0, 15).trim();  
                    String name = line.substring(15, 35).trim();   
                    String password = line.substring(35).trim();   
                    users.put(userId, new User(userId, name, password));
                } else {
                    System.out.println("Line format error: " + line); 
                }
            }
            System.out.println("Users loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }


     /**
     * Loads reservation data from a file and returns a map of seat numbers to Seat objects.
     * 
     * @param file The file to load reservations from.
     * @param users A map of users to associate with the reservations.
     * @return A map of seat numbers to Seat objects.
     */
    public static Map<String, Seat> loadReservations(File resFile, Map<String, User> users) {
        Map<String, Seat> reservations = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(resFile))) {
            reader.readLine(); 
            String line;
            System.out.println("Loading reservations from file: " + resFile.getName());
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 25) { 
                    String userId = line.substring(0, 15).trim();  
                    String reservedSeat = line.substring(15).trim();  
    
                    User user = users.get(userId);
                    if (user != null) {
                        reservations.put(reservedSeat, new Seat(reservedSeat, ReservationSystem.getSeatClass(reservedSeat), user));
                        double price = ReservationSystem.getPrice(ReservationSystem.getSeatClass(reservedSeat));
                        user.addReservation(reservedSeat, price);
                    } else {
                        System.out.println("Error: No user found for userID " + userId);
                    }
                } else {
                    System.out.println("Line format error: " + line);  
                }
            }
            System.out.println("Reservations loaded successfully from " + resFile.getName());
        } catch (IOException e) {
            System.out.println("Error loading reservations: " + e.getMessage());
        }
        return reservations;
    }

     /**
     * Saves reservation data to a file.
     * 
     * @param file The file to save reservation data to.
     * @param reservations A map of seat numbers to Seat objects.
     */
    public static void saveReservations(File resFile, Map<String, Seat> reservations) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resFile))) {
            writer.write(String.format("%-15s %-10s", "UserID", "ReservedSeat"));
            writer.newLine();
            for (Map.Entry<String, Seat> entry : reservations.entrySet()) {
                Seat seat = entry.getValue();
                writer.write(String.format("%-15s %-10s", seat.getUser().getUserId(), seat.getSeatNumber()));
                writer.newLine();
            }
            System.out.println("Reservations saved to " + resFile.getName());
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }
    
    /**
     * Saves user data to a file.
     * 
     * @param file The file to save user data to.
     * @param users A map of user IDs to User objects.
     */
    public static void saveUsers(File usrFile, Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usrFile))) {
            writer.write(String.format("%-15s %-20s %-15s", "UserID", "Name", "Password"));
            writer.newLine();
    
            for (User user : users.values()) {
                writer.write(String.format("%-15s %-20s %-15s", user.getUserId(), user.getName(), user.getPassword()));
                writer.newLine();
            }
            System.out.println("Users saved to " + usrFile.getName());
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}
