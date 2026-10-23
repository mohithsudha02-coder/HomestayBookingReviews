
package service;

import model.*;
import payment.*;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class HomestayService {
    private ArrayList<Host> hosts = new ArrayList<>();
    private ArrayList<Guest> guests = new ArrayList<>();
    private ArrayList<Listing> listings = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();
    private ArrayList<Cancellation> cancellations = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();

    private int hostId = 1, guestId = 1;
    private int listingId = 1, bookingId = 1;

    public void addHost(String name, String phone) {
        hosts.add(new Host(hostId++, name, phone));
        System.out.println("Host added successfully.");
    }

    public void addGuest(String name, String phone) {
        guests.add(new Guest(guestId++, name, phone));
        System.out.println("Guest added successfully.");
    }

    public void addListing(String name, String location,
                           double price, int hId) {
        Host host = null;
        for (Host h : hosts)
            if (h.getId() == hId) host = h;

        if (host == null || price <= 0) {
            System.out.println("Invalid host ID or price.");
            return;
        }

        listings.add(new Listing(listingId++, name,
                                 location, price, host));
        System.out.println("Listing added successfully.");
    }

    private Listing findListing(int id) {
        for (Listing l : listings)
            if (l.getId() == id) return l;
        return null;
    }

    private Guest findGuest(int id) {
        for (Guest g : guests)
            if (g.getId() == id) return g;
        return null;
    }

    private Booking findBooking(int id) {
        for (Booking b : bookings)
            if (b.getId() == id) return b;
        return null;
    }

    public boolean isAvailable(int id, LocalDate in,
                               LocalDate out) {
        if (findListing(id) == null || !in.isBefore(out)
                || in.isBefore(LocalDate.now())) return false;

        for (Booking b : bookings)
            if (b.getListing().getId() == id
                    && (b.getStatus().equals("PENDING_PAYMENT")
                    || b.getStatus().equals("CONFIRMED"))
                    && b.overlaps(in, out)) return false;
        return true;
    }

    public void createBooking(int lId, int gId,
                              LocalDate in, LocalDate out) {
        Listing listing = findListing(lId);
        Guest guest = findGuest(gId);

        if (listing == null || guest == null
                || !isAvailable(lId, in, out)) {
            System.out.println("Invalid details or unavailable dates.");
            return;
        }

        Booking b = new Booking(bookingId++, listing, guest, in, out);
        bookings.add(b);
        System.out.println("Booking created: " + b);
        System.out.println("Record payment to confirm the booking.");
    }

    public void recordPayment(int id, int method) {
        Booking b = findBooking(id);

        if (b == null || !b.getStatus().equals("PENDING_PAYMENT")) {
            System.out.println("No pending booking found.");
            return;
        }

        Payment p;
        if (method == 1)
            p = new CashPayment(id, b.getAmount());
        else if (method == 2)
            p = new UpiPayment(id, b.getAmount());
        else {
            System.out.println("Invalid payment method.");
            return;
        }

        payments.add(p);
        b.setStatus("CONFIRMED");
        System.out.println("Payment successful: " + p);
    }

    public void cancelBooking(int id, String reason) {
        Booking b = findBooking(id);

        if (b == null || !(b.getStatus().equals("CONFIRMED")
                || b.getStatus().equals("PENDING_PAYMENT"))) {
            System.out.println("Booking cannot be cancelled.");
            return;
        }

        long days = ChronoUnit.DAYS.between(
                LocalDate.now(), b.getCheckIn());
        double rate = days >= 7 ? 0.10 : days >= 2 ? 0.30 : 0.80;
        double fee = b.getStatus().equals("CONFIRMED")
                ? b.getAmount() * rate : 0;

        b.setStatus("CANCELLED");
        cancellations.add(new Cancellation(id, fee, reason));
        System.out.println("Booking cancelled. Fee: Rs." + fee);
    }

    public void addReview(int id, int rating, String comment) {
        Booking b = findBooking(id);

        if (b == null || !b.getStatus().equals("CONFIRMED")
                || LocalDate.now().isBefore(b.getCheckOut())) {
            System.out.println("Review allowed only after completed stays.");
            return;
        }

        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }

        for (Review r : reviews)
            if (r.getBooking() == b) {
                System.out.println("Review already submitted.");
                return;
            }

        b.setStatus("COMPLETED");
        reviews.add(new Review(b, rating, comment));
        System.out.println("Review added successfully.");
    }

    public void displayAvailability(LocalDate in, LocalDate out) {
        for (Listing l : listings)
            System.out.println(l + " | "
                    + (isAvailable(l.getId(), in, out)
                    ? "AVAILABLE" : "UNAVAILABLE"));
    }

    public void displayRecords() {
        System.out.println("\n--- HOSTS ---");
        for (Host h : hosts) System.out.println(h);
        System.out.println("\n--- GUESTS ---");
        for (Guest g : guests) System.out.println(g);
        System.out.println("\n--- LISTINGS ---");
        for (Listing l : listings) System.out.println(l);
        System.out.println("\n--- BOOKINGS ---");
        for (Booking b : bookings) System.out.println(b);
        System.out.println("\n--- PAYMENTS ---");
        for (Payment p : payments) System.out.println(p);
        System.out.println("\n--- CANCELLATIONS ---");
        for (Cancellation c : cancellations) System.out.println(c);
        System.out.println("\n--- REVIEWS ---");
        for (Review r : reviews) System.out.println(r);
    }
}
