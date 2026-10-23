
package app;

import service.HomestayService;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static HomestayService service = new HomestayService();

    static String read(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static int number(String msg) {
        return Integer.parseInt(read(msg));
    }

    static LocalDate date(String msg) {
        return LocalDate.parse(read(msg + " (YYYY-MM-DD): "));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== HOMESTAY BOOKING & REVIEWS ===");
            System.out.println("1. Add Listing");
            System.out.println("2. Add Host");
            System.out.println("3. Add Guest");
            System.out.println("4. Create Booking");
            System.out.println("5. Record Payment");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Add Review");
            System.out.println("8. Display Availability");
            System.out.println("9. Display All Records");
            System.out.println("10. Exit");

            try {
                int choice = number("Enter choice: ");

                switch (choice) {
                    case 1:
                        service.addListing(read("Listing name: "),
                                read("Location: "),
                                Double.parseDouble(read("Price per night: ")),
                                number("Host ID: "));
                        break;
                    case 2:
                        service.addHost(read("Host name: "),
                                        read("Phone: "));
                        break;
                    case 3:
                        service.addGuest(read("Guest name: "),
                                         read("Phone: "));
                        break;
                    case 4:
                        service.createBooking(number("Listing ID: "),
                                number("Guest ID: "),
                                date("Check-in date"),
                                date("Check-out date"));
                        break;
                    case 5:
                        service.recordPayment(number("Booking ID: "),
                                number("1. Cash  2. UPI: "));
                        break;
                    case 6:
                        service.cancelBooking(number("Booking ID: "),
                                              read("Reason: "));
                        break;
                    case 7:
                        service.addReview(number("Booking ID: "),
                                number("Rating (1-5): "),
                                read("Comment: "));
                        break;
                    case 8:
                        service.displayAvailability(
                                date("Check-in date"),
                                date("Check-out date"));
                        break;
                    case 9:
                        service.displayRecords();
                        break;
                    case 10:
                        System.out.println("Thank you for using the system!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Choose a number from 1 to 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use YYYY-MM-DD.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
