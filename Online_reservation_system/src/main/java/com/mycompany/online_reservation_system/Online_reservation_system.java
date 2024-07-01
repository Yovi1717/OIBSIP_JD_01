package com.mycompany.online_reservation_system;
import java.util.*;

public class Online_reservation_system {
    private static Map<String, String> users = new HashMap<>();
    private static Map<Integer, Reservation> reservations = new HashMap<>();
    private static int reservationIdCounter = 1;

    public static void main(String[] args) {
        // Sample user
        users.put("newuser", "password");
        
        Scanner scanner = new Scanner(System.in);

        if (!login(scanner)) {
            System.out.println("Invalid login credentials.");
            return;
        }

        while (true) {
            System.out.println("Online Reservation System");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeReservation(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return users.containsKey(loginId) && users.get(loginId).equals(password);
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter from (place): ");
        String from = scanner.nextLine();
        System.out.print("Enter to (destination): ");
        String to = scanner.nextLine();

        Reservation reservation = new Reservation(reservationIdCounter++, name, trainNumber, trainName, classType, dateOfJourney, from, to);
        reservations.put(reservation.getId(), reservation);

        System.out.println("Reservation successful. Your PNR number is " + reservation.getId());
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR number: ");
        int pnrNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Reservation reservation = reservations.get(pnrNumber);
        if (reservation == null) {
            System.out.println("No reservation found with PNR number " + pnrNumber);
            return;
        }

        System.out.println("Reservation details: " + reservation);
        System.out.print("Do you want to cancel this reservation? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            reservations.remove(pnrNumber);
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }

    static class Reservation {
        private int id;
        private String name;
        private String trainNumber;
        private String trainName;
        private String classType;
        private String dateOfJourney;
        private String from;
        private String to;

        public Reservation(int id, String name, String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
            this.id = id;
            this.name = name;
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.from = from;
            this.to = to;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "PNR: " + id + ", Name: " + name + ", Train: " + trainNumber + " (" + trainName + "), Class: " + classType +
                    ", Date: " + dateOfJourney + ", From: " + from + ", To: " + to;
        }
    }
}

