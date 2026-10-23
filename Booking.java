
package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private int id;
    private Listing listing;
    private Guest guest;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;
    private double amount;

    public Booking(int id, Listing listing, Guest guest,
                   LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.listing = listing;
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.amount = ChronoUnit.DAYS.between(checkIn, checkOut)
                * listing.getPrice();
        this.status = "PENDING_PAYMENT";
    }

    public int getId() { return id; }
    public Listing getListing() { return listing; }
    public Guest getGuest() { return guest; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public String getStatus() { return status; }
    public double getAmount() { return amount; }
    public void setStatus(String status) { this.status = status; }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return checkIn.isBefore(end) && start.isBefore(checkOut);
    }

    @Override
    public String toString() {
        return "Booking ID: " + id
                + ", Listing: " + listing.getName()
                + ", Guest: " + guest.getName()
                + ", Dates: " + checkIn + " to " + checkOut
                + ", Amount: Rs." + amount
                + ", Status: " + status;
    }
}
