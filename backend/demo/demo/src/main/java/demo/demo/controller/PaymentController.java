package demo.demo.controller;

import demo.demo.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private  PaymentServices paymentServices;



    @PostMapping("/create-order")
    public String createOrder(@RequestParam double amount) {
        return paymentServices.createOrder(amount);
    }

    @PostMapping("/verify-payment")
    public String verifyPayment(@RequestBody Map<String, String> payload) {
        boolean isAuthentic = paymentServices.verifyPayment(
                payload.get("razorpay_order_id"),
                payload.get("razorpay_payment_id"),
                payload.get("razorpay_signature")
        );

        if (isAuthentic) {
            return "Payment verified successfully!";
        } else {
            return "Payment verification failed!";
        }
    }
}
