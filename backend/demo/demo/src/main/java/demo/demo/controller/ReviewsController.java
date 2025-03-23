package demo.demo.controller;

import demo.demo.constants.Constants;
import demo.demo.dto.ResponseGeneral;
import demo.demo.dto.reviewDTO.ReviewDTO;
import demo.demo.services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    private ReviewServices reviewServices;

    @PostMapping
    public ResponseEntity<ResponseGeneral<ReviewDTO>> addReview(@RequestBody ReviewDTO payload){
        try {
            reviewServices.addReview(payload);
            return ResponseEntity.ok(ResponseGeneral.success(null, "Review added Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseGeneral<List<ReviewDTO>>> addReview(@RequestParam String Id){
        try {

            return ResponseEntity.ok(ResponseGeneral.success(reviewServices.fetchReview(Id), "Review fetched Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }
}
