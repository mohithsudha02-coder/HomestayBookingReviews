
package model;

public class Review {
    private Booking booking;
    private int rating;
    private String comment;

    public Review(Booking booking, int rating, String comment) {
        this.booking = booking;
        this.rating = rating;
        this.comment = comment;
    }

    public Booking getBooking() { return booking; }

    @Override
    public String toString() {
        return "Listing: " + booking.getListing().getName()
                + ", Guest: " + booking.getGuest().getName()
                + ", Rating: " + rating + "/5"
                + ", Review: " + comment;
    }
}
