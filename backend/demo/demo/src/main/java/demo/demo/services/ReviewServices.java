package demo.demo.services;

import demo.demo.dto.reviewDTO.ReviewDTO;

import java.util.List;

public abstract class ReviewServices {

    public abstract void addReview(ReviewDTO payload);

    public abstract List<ReviewDTO> fetchReview(String payload);
}
