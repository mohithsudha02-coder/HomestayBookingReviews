
package payment;

public class UpiPayment extends Payment {
    public UpiPayment(int bookingId, double amount) {
        super(bookingId, amount);
    }

    @Override
    public String getMethod() {
        return "UPI";
    }
}
