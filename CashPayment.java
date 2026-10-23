
package payment;

public class CashPayment extends Payment {
    public CashPayment(int bookingId, double amount) {
        super(bookingId, amount);
    }

    @Override
    public String getMethod() {
        return "Cash";
    }
}
