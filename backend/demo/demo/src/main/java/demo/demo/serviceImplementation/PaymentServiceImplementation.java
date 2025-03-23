package demo.demo.serviceImplementation;

import demo.demo.entities.Payment;
import demo.demo.repository.PaymentRepository;
import demo.demo.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Service
public class PaymentServiceImplementation extends PaymentServices {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    @Autowired
    private PaymentRepository paymentRepository;

    public String createOrder(double amount) {
        try {
            RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);
            JSONObject options = new JSONObject();
            options.put("amount", (int) (amount * 100));  // Convert to paise
            options.put("currency", "INR");
            options.put("payment_capture", 1); // Auto-capture

            Order order = razorpay.orders.create(options);
            return order.toString();
        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to create order", e);
        }
    }



    public boolean verifyPayment(String orderId, String paymentId, String signature) {
        try {
            String payload = orderId + "|" + paymentId;
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(keySecret.getBytes(), "HmacSHA256");
            sha256Hmac.init(secretKey);
            byte[] hash = sha256Hmac.doFinal(payload.getBytes());
            String expectedSignature = Base64.getEncoder().encodeToString(hash);

            boolean isAuthentic = expectedSignature.equals(signature);
            if (isAuthentic) {
                Payment payment = new Payment();
                payment.setRazorpayOrderId(orderId);
                payment.setRazorpayPaymentId(paymentId);
                payment.setRazorpaySignature(signature);
                paymentRepository.save(payment);
            }
            return isAuthentic;
        } catch (Exception e) {
            return false;
        }
    }
}
