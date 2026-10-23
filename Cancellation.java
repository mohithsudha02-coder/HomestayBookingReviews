
package service;

public class Cancellation {
    private int bookingId;
    private double fee;
    private String reason;

    public Cancellation(int bookingId, double fee, String reason) {
        this.bookingId = bookingId;
        this.fee = fee;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId
                + ", Fee: Rs." + fee
                + ", Reason: " + reason;
    }
}
