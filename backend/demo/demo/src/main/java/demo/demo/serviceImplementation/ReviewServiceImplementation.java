package demo.demo.serviceImplementation;

import demo.demo.dto.reviewDTO.ReviewDTO;
import demo.demo.entities.Reviews;
import demo.demo.repository.GigRepository;
import demo.demo.repository.ReviewsRepository;
import demo.demo.services.ReviewServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImplementation extends ReviewServices {
    @Autowired
    private  GigRepository gigRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Transactional
    @Override
    public void addReview(ReviewDTO payload) {
        Reviews data = new Reviews();
        data.setComment(payload.getDesc());
        data.setRating(payload.getStar());
        data.setGigId(payload.getGigId());
        data.setUserId(payload.getUserId());
        GigsServiceImplementation Star = new GigsServiceImplementation(gigRepository);
        Star.SaveReview(payload.getStar(),payload.getGigId());
        reviewsRepository.save(data);
    }

    @Override
    public List<ReviewDTO> fetchReview(String payload) {
        List<Reviews> data = reviewsRepository.findByGigId(Long.valueOf(payload));
        List<ReviewDTO> response = new ArrayList<>();
        for(Reviews rev:data){
            ReviewDTO obj = new ReviewDTO();
            obj.setDesc(rev.getComment());
            obj.setUserId(rev.getUserId());
            LocalDateTime localDateTime = rev.getCreated_at().atZone(ZoneId.systemDefault()).toLocalDateTime();
            obj.setUpdatedAt(localDateTime);
            obj.setCreatedAt(localDateTime);
            obj.setStar(rev.getRating());
            obj.set_id(rev.getId());
            obj.setGigId(rev.getGigId());
            obj.set__v(0);
            response.add(obj);
        }
        return response;
    }
}
