package demo.demo.serviceImplementation;

import demo.demo.dto.orderDTO.OrderDTO;
import demo.demo.entities.Gig;
import demo.demo.entities.Order;
import demo.demo.entities.Users;
import demo.demo.repository.GigRepository;
import demo.demo.repository.OrderRepository;
import demo.demo.repository.UserRepository;
import demo.demo.services.GigsService;
import demo.demo.services.OrderServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImplementation extends OrderServices {

    @Autowired
    private GigsService gigsService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GigRepository gigRepository;

    @Transactional
    @Override
    public Order createOrder( OrderDTO payload, String buyerId) {
        Optional<Gig> gigData = gigRepository.findById(payload.getGigId());
        Order data = new Order();
        data.setGigId(gigData.get().getId());
        data.setBuyerId(UUID.fromString(String.valueOf(gigData.get().getUserId())));
        data.setImg(gigData.get().getCover());
        data.setTitle(gigData.get().getTitle());
        data.setPrice(gigData.get().getPrice());
        data.setSellerId(UUID.fromString(buyerId));
        data.setBuyerName(payload.getBuyerName());
        data.setSellerName(payload.getSellerName());
        data.setPaymentIntent(payload.getPaymentIntent());
        Optional<Users> userData = userRepository.findById(gigData.get().getUserId());
        Users dbData = userData.get();
        dbData.setOrders(dbData.getOrders()+1);
        userRepository.save(dbData);
        Gig savedData = gigData.get();
        savedData.setSales(gigData.get().getSales()+1);
        gigRepository.save(savedData);
        return orderRepository.save(data);
    }

    @Override
    public List<OrderDTO> getOrders(Boolean flag, String userId) {
        List<OrderDTO> response = new ArrayList<>();
        List<Order> data = new ArrayList<>();
        if(flag){
            data = orderRepository.findBySellerId(UUID.fromString(userId));
        }else{
            data = orderRepository.findByBuyerId(UUID.fromString(userId));
        }
        for(Order i:data){
            if(i.getIsCompleted()){
                OrderDTO obj =new OrderDTO();
                obj.setImg(i.getImg());
                obj.setId(i.getId());
                obj.setPrice(i.getPrice());
                obj.setTitle(i.getTitle());
                obj.setGigId(i.getGigId());
                obj.setBuyerId(String.valueOf(i.getBuyerId()));
                obj.setBuyerName(i.getBuyerName());
                obj.setIsCompleted(i.getIsCompleted());
                obj.setPaymentIntent(i.getPaymentIntent());
                obj.setSellerId(String.valueOf(i.getSellerId()));
                obj.setSellerName(i.getSellerName());
                response.add(obj);
            }
        }
        return response;
    }
}
