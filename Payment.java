
package payment;

public abstract class Payment {
    private int bookingId;
    private double amount;

    public Payment(int bookingId, double amount) {
        this.bookingId = bookingId;
        this.amount = amount;
    }

    public int getBookingId() { return bookingId; }
    public double getAmount() { return amount; }

    public abstract String getMethod();

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Amount: Rs."
                + amount + ", Method: " + getMethod();
    }
}

