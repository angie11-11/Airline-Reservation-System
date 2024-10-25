/**
 * Manages seat reservations for users and admins, including sign-up, login, reservations, cancellations,
 * and seat availability. Admins can view the seat manifest. Interacts with FileHandler for data loading
 * and saving.
 * CS151 Hw2 Solution
 * Instructor: Dr.Kim
 * @author: Angie Do
 * Date: 10/02/2024
*/

import java.io.*;
import java.util.*;

public class ReservationSystem {
    private static final Map<String, User> users = new HashMap<>();
    private static final Map<String, Seat> reservations = new HashMap<>();
    private static final List<String> employeeIds = Arrays.asList("0101", "0102", "0103","0104");

    /**
     * Main method that runs the reservation system. It loads the necessary files for users and reservations.
     * 
     * @param args Command-line arguments specifying the reservation file and the user file.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ReservationSystem <reservation file> <user file>");
            return;
        }
        String reservationFile = args[0];
        String userFile = args[1];
        File resFile = new File(reservationFile);
        File usrFile = new File(userFile);

        if (!resFile.exists() || !usrFile.exists()) {
            try {
                if (resFile.createNewFile()) {
                    System.out.println(reservationFile + " is now created.");
                }
                if (usrFile.createNewFile()) {
                    System.out.println(userFile + " is now created.");
                }
            } catch (IOException e) {
                System.out.println("Error creating files: " + e.getMessage());
            }
        } else {
            System.out.println("Found existing files. Loading data...");
            users.putAll(FileHandler.loadUsers(usrFile)); 
            reservations.putAll(FileHandler.loadReservations(resFile, users));  
        }      
        runSystem(resFile, usrFile);
    }

    /**
     * Runs the system, allowing the user to select between user and admin roles or to exit.
     * 
     * @param resFile The file containing reservation data.
     * @param usrFile The file containing user data.
     */
    private static void runSystem(File resFile, File usrFile) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Are you a [U]ser, an [A]dmin, or do you want to [E]xit?");
            String userType = sc.nextLine().toUpperCase();

            switch (userType) {
                case "U" -> handleUser(sc);
                case "A" -> handleAdmin(sc);
                case "E" -> {
                    FileHandler.saveReservations(resFile, reservations);
                    FileHandler.saveUsers(usrFile, users);
                    System.out.println("Data saved. Exiting the system.");
                    System.out.println("Good bye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please enter U, A, or E.");
            }
        }
    }

    /**
     * Handles user interactions such as sign-up and login, and allows users to check availability,
     * make, cancel, or view reservations.
     * 
     * @param sc The scanner object to read user input.
     */
    private static void handleUser(Scanner sc) {
        System.out.println("Please [S]ign up or [L]ogin:");
        String choice = sc.nextLine().toUpperCase();
        User currentUser = null;
        if (choice.equals("S")) {
            currentUser = signUp(sc);
        } else if (choice.equals("L")) {
            currentUser = login(sc);  
        }
        if (currentUser == null) {
            return;  
        }
        while (true) {
            System.out.println("Check [A]vailability  Make [R]eservation  [C]ancel Reservation  [V]iew Reservations  [D]one");
            String option = sc.nextLine().toUpperCase();
    
            switch (option) {
                case "A" -> checkAvailability();
                case "R" -> makeReservation(sc, currentUser);
                case "C" -> cancelReservation(sc, currentUser);
                case "V" -> viewReservations(currentUser);
                case "D" -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Handles user sign-up by prompting for user ID, name, and password.
     * Creates a new User object and adds it to the system if the user ID is not already taken.
     *
     * @param sc The scanner object used to read input from the user.
     * @return The newly created User object, or null if the user ID already exists.
     */
    private static User signUp(Scanner sc) {
        System.out.println("Please enter user ID:");
        String userId = sc.nextLine();
        System.out.println("Enter name:");
        String name = sc.nextLine();
        System.out.println("Please enter password:");
        String password = sc.nextLine();
        // Check if the user ID already exists
        if (users.containsKey(userId)) {
            System.out.println("User ID already exists. Please Login.");
            return null;
        }
        // Create a new user and add them to the users map
        User newUser = new User(userId, name, password);
        users.put(userId, newUser);
        System.out.println("User signed up successfully.");
        return newUser;
    }

    /**
    * Handles user login by prompting for user ID and password. 
    * Verifies if the user exists and the password matches.
    *
    * @param sc The scanner object used to read input from the user.
    * @return The User object if the login is successful, or null if the login fails.
    */
    private static User login(Scanner sc) {
        System.out.println("Enter user ID:");
        String userId = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
        // Check if the user exists
        User user = users.get(userId);
        // Check if user ID is not registered
        if (user == null) {
            System.out.println("User ID not found. Please sign up first.");
            return null;
        }
        // Check if the password is incorrect
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password. Please try again.");
            return null;
        }
        System.out.println("Login successful.");
        return user;
    }
    
    /**
     * Allows an admin to log in using an employee ID and view the seat manifest.
     * 
     * @param sc The scanner object to read admin input.
     */
    private static void handleAdmin(Scanner sc) {
        String employeeId;
        do {
            System.out.println("Please enter your employee ID:");
            employeeId = sc.nextLine();
            if (!employeeIds.contains(employeeId)) {
                System.out.println("Invalid employee ID. Try again.");
            }
        } while (!employeeIds.contains(employeeId));

        // Admin Menu
        while (true) {
            System.out.println("Show [M]anifest list  E[X]it");
            String option = sc.nextLine().toUpperCase();

            switch (option) {
                case "M" -> showManifest();
                case "X" -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Checks the availability of all seats and prints the seat map.
     */
    private static void checkAvailability() {
        System.out.println("Seat Map:");
        System.out.println("First Class (Price: $1000/seat):");
        printSeatMap(SeatLayout.firstClassSeats);
    
        System.out.println("\nEconomy Plus (Price: $500/seat):");
        printSeatMap(SeatLayout.economyPlusSeats);
    
        System.out.println("\nEconomy (Price: $250/seat):");
        printSeatMap(SeatLayout.economySeats);
    
        System.out.println();
    }
    
    /**
     * Display available seat in 3 classes
     */
    private static void printSeatMap(List<String> seatList) {
        int count = 0;
        for (String seat : seatList) {
            if (reservations.containsKey(seat)) {
                System.out.print("[X]"); // Seat is reserved
            } else {
                System.out.print("[ ]"); // Seat is available
            }
            System.out.print(" " + seat + "  "); 
    
            count++;
            if (count % 8 == 0) { // Print 8 seats per row for readability
                System.out.println(); 
            }
        }
        System.out.println(); 
    }
    
    /**
     * Makes a reservation on available seat.
     * 
     * @param sc The scanner object to read user input.
     * @param user The user making the reservation.
     */
    private static void makeReservation(Scanner sc, User user) {
        System.out.println("Please enter seat number:");
        String seatNumber = sc.nextLine().toUpperCase();

        if (reservations.containsKey(seatNumber)) {
            System.out.println("Seat is already reserved.");
            return;
        }

        String seatClass = getSeatClass(seatNumber);
        if (seatClass == null) {
            System.out.println("Invalid seat number.");
            return;
        }

        double price = getPrice(seatClass);
        System.out.println("Seat " + seatNumber + " in " + seatClass + " for $" + price + ". Please confirm (Y/N)");
        String confirm = sc.nextLine().toUpperCase();

        if (confirm.equals("Y")) {
            reservations.put(seatNumber, new Seat(seatNumber, seatClass, user));
            user.addReservation(seatNumber, price);
            System.out.println("Your reservation is confirmed.");
        } else {
            System.out.println("Your reservation is cancelled.");
        }
    }

     /**
     * Cancels selected reservation seat.
     * 
     * @param sc The scanner object to read user input.
     * @param user The user canceling their reservation.
     */
    private static void cancelReservation(Scanner sc, User user) {
        if (user.getReservations().isEmpty()) {
            System.out.println("You have no reservations.");
            return;
        }

        System.out.println("Your reservations: " + user.getReservations());
        System.out.println("Please enter seat number to cancel:");
        String seatNumber = sc.nextLine().toUpperCase();

        if (!user.getReservations().containsKey(seatNumber)) {
            System.out.println("Invalid seat number.");
            return;
        }

        reservations.remove(seatNumber);
        user.cancelReservation(seatNumber);
        System.out.println("Reservation for seat " + seatNumber + " cancelled.");
    }

    /**
     * Displays current reservations.
     * 
     * @param user The user whose reservations are being viewed.
     */
    private static void viewReservations(User user) {
        System.out.println("Name: " + user.getName());
        System.out.println("Seats: " + user.getReservations());
        System.out.println("Total Balance Due: $" + user.getTotalBalance());
    }

     /**
     * Retrieves the seat class for a given seat number.
     * 
     * @param seatNumber The seat number to check.
     * @return The seat class (First Class, Economy Plus, Economy) or null if the seat number is invalid.
     */
    public static String getSeatClass(String seatNumber) {
        if (SeatLayout.firstClassSeats.contains(seatNumber)) {
            return "First Class";
        } else if (SeatLayout.economyPlusSeats.contains(seatNumber)) {
            return "Economy Plus";
        } else if (SeatLayout.economySeats.contains(seatNumber)) {
            return "Economy";
        }
        return null;  
    }

    /**
     * Retrieves the price for each seat class.
     * 
     * @param seatClass The seat class to get the price for.
     * @return The price for the seat class.
     */
    public static double getPrice(String seatClass) {
        return switch (seatClass) {
            case "First Class" -> 1000;
            case "Economy Plus" -> 500;
            case "Economy" -> 250;
            default -> 0;
        };
    }    

    /**
     * Displays the manifest of all reserved seats by class.
     */
    private static void showManifest() {
        System.out.println("First Class:");
        for (String seat : SeatLayout.firstClassSeats) {
            if (reservations.containsKey(seat)) {
                System.out.println(seat + ": " + reservations.get(seat).getUser().getName());
            }
        }
        System.out.println("\nEconomy Plus:");
        for (String seat : SeatLayout.economyPlusSeats) {
            if (reservations.containsKey(seat)) {
                System.out.println(seat + ": " + reservations.get(seat).getUser().getName());
            }
        }
        System.out.println("\nEconomy:");
        for (String seat : SeatLayout.economySeats) {
            if (reservations.containsKey(seat)) {
                System.out.println(seat + ": " + reservations.get(seat).getUser().getName());
            }
        }
    }

    
    
}
