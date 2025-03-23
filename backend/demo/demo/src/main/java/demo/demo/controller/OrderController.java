package demo.demo.controller;

import demo.demo.constants.Constants;
import demo.demo.dto.orderDTO.OrderDTO;
import demo.demo.dto.ResponseGeneral;
import demo.demo.entities.Order;
import demo.demo.services.OrderServices;
import demo.demo.utilis.JwtUtilis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private JwtUtilis jwtUtilis;
    @Autowired
    private OrderServices orderServices;

    @PostMapping
    public ResponseEntity<ResponseGeneral<Order>> createOrder(@RequestBody OrderDTO payload){
        try {
            if(payload.getGigId() == null){
                throw new RuntimeException("Gig Id is Null");
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return ResponseEntity.ok(ResponseGeneral.success(orderServices.createOrder(payload, authentication.getName()), "Order Created Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseGeneral<List<OrderDTO>>> getOrders(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) authentication.getCredentials();
            return ResponseEntity.ok(ResponseGeneral.success(orderServices.getOrders( jwtUtilis.extractIsSeller(token),authentication.getName()), "Order Created Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

}
