package demo.demo.services;

public abstract class PaymentServices {
    public abstract String createOrder(double amount);
    public abstract boolean verifyPayment(String orderId, String paymentId, String signature );
}
