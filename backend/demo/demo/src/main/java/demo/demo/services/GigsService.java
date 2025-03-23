package demo.demo.services;


import demo.demo.dto.gigDTO.GigPayload;
import demo.demo.dto.gigDTO.GigResponse;

import java.util.List;

public abstract class GigsService {

    public abstract GigResponse createGig(GigPayload payload);
    public abstract void deleteGig(String payload);
    public abstract GigResponse getGig(String payload);
    public abstract List<GigResponse> getFilteredGigs (String userId,String category, Integer minPrice, Integer maxPrice, String sortBy);
}
