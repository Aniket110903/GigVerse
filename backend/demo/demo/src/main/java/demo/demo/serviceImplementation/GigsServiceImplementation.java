package demo.demo.serviceImplementation;


import demo.demo.dto.gigDTO.GigPayload;
import demo.demo.dto.gigDTO.GigResponse;
import demo.demo.entities.Gig;
import demo.demo.repository.GigRepository;
import demo.demo.services.GigsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GigsServiceImplementation extends GigsService {

    private final GigRepository gigRepository;

    @Autowired
    public GigsServiceImplementation(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Transactional
    @Override
    public GigResponse createGig(GigPayload payload) {
        Gig gig = new Gig();
        if(payload.getId()!= null){
            gig.setId(payload.getId());
        }
        gig.setUserId(UUID.fromString(payload.getUserId()));
        gig.setCover(payload.getCover());
        gig.setCategory(payload.getCategory());
        gig.setFeatures(payload.getFeatures().toArray(new String[0]));
        gig.setPrice(payload.getPrice());
        gig.setImages(payload.getImages().toArray(new String[0]));
        gig.setDeliveryTime(payload.getDeliveryTime());
        gig.setRevision(payload.getRevision());
        gig.setShortTitle(payload.getShortTitle());
        gig.setTitle(payload.getTitle());
        gig.setShortDesc(payload.getShortDesc());
        gig.setDescription(payload.getDesc());
        Gig savedGig =gigRepository.save(gig);

        return new GigResponse(String.valueOf(savedGig.getId()),savedGig.getUserId().toString(),savedGig.getTitle(),savedGig.getDescription(),savedGig.getTotalStar(), savedGig.getStarNumber(), savedGig.getCategory(), savedGig.getPrice(),savedGig.getCover(), Arrays.stream(savedGig.getImages()).toList(),savedGig.getShortTitle(),savedGig.getShortDesc(),savedGig.getDeliveryTime(), savedGig.getRevision(), Arrays.stream(savedGig.getFeatures()).toList(), savedGig.getSales(), savedGig.getCreatedAt(),savedGig.getUpdatedAt());
    }

    @Override
    public void deleteGig(String id) {
        gigRepository.deleteById(Long.valueOf(id));
        return ;
    }

    @Override
    public GigResponse getGig(String id) {
        Optional<Gig> gigs = gigRepository.findById(Long.valueOf(id));
        GigResponse obj = new GigResponse();
        if(gigs.isPresent()) {
            obj.setId(String.valueOf(gigs.get().getId()));
            obj.setUserId(String.valueOf(gigs.get().getUserId()));
            obj.setDesc(gigs.get().getDescription());
            obj.setCreatedAt(gigs.get().getCreatedAt());
            obj.setCover(gigs.get().getCover());
            obj.setCategory(gigs.get().getCategory());
            obj.setFeatures(Arrays.asList(gigs.get().getFeatures()));
            obj.setImages(Arrays.asList(gigs.get().getImages()));
            obj.setPrice(gigs.get().getPrice());
            obj.setRevision(gigs.get().getRevision());
            obj.setSales(gigs.get().getSales());
            obj.setDeliveryTime(gigs.get().getDeliveryTime());
            obj.setStarNumber(gigs.get().getStarNumber());
            obj.setUpdatedAt(gigs.get().getUpdatedAt());
            obj.setTitle(gigs.get().getTitle());
            obj.setShortDesc(gigs.get().getShortDesc());
            obj.setShortTitle(gigs.get().getShortTitle());
            obj.setTotalStar(gigs.get().getTotalStar());
        }else {
            throw new RuntimeException("No gig Found");
        }
        return obj;
    }

    public List<GigResponse> getFilteredGigs(String userId,String category, Integer minPrice, Integer maxPrice,String sortBy) {
        List<Gig> data = new ArrayList<>();
        if(userId!=null){
            data =  gigRepository.findByUserId(UUID.fromString(userId));
        }else {
            data = gigRepository.findGigs(category, minPrice, maxPrice, sortBy);
        }
        List<GigResponse> response = new ArrayList<>();
        for(Gig gigs:data){
            GigResponse obj = new GigResponse();
            obj.setId(String.valueOf(gigs.getId()));
            obj.setUserId(String.valueOf(gigs.getUserId()));
            obj.setDesc(gigs.getDescription());
            obj.setCreatedAt(gigs.getCreatedAt());
            obj.setCover(gigs.getCover());
            obj.setCategory(gigs.getCategory());
            obj.setFeatures(Arrays.asList(gigs.getFeatures()));
            obj.setImages(Arrays.asList(gigs.getImages()));
            obj.setPrice(gigs.getPrice());
            obj.setRevision(gigs.getRevision());
            obj.setSales(gigs.getSales());
            obj.setDeliveryTime(gigs.getDeliveryTime());
            obj.setStarNumber(gigs.getStarNumber());
            obj.setUpdatedAt(gigs.getUpdatedAt());
            obj.setTitle(gigs.getTitle());
            obj.setShortDesc(gigs.getShortDesc());
            obj.setShortTitle(gigs.getShortTitle());
            obj.setTotalStar(gigs.getTotalStar());
            response.add(obj);
        }
        return response;
    }

    public void SaveReview(Integer star,Long gigId){
        GigResponse data = this.getGig(String.valueOf(gigId));
        Gig gigs = new Gig(Long.valueOf(data.getId()),UUID.fromString(data.getUserId()),data.getTitle(), data.getCategory(), data.getCover(), data.getDesc(), data.getTotalStar(), data.getStarNumber(), data.getSales(), data.getShortTitle(), data.getShortDesc(), data.getDeliveryTime(), data.getRevision(), data.getPrice(),data.getImages().toArray(new String[0]), data.getFeatures().toArray(new String[0]), data.getCreatedAt(),data.getUpdatedAt());
        if(gigs.getId() == null) {
            throw new RuntimeException("Gig not found with ID: " + gigId);
        }else{
            // Update rating values
            gigs.setTotalStar(gigs.getTotalStar() + star);
            gigs.setStarNumber(gigs.getStarNumber() + 1);

            // Save the updated gig
            gigRepository.save(gigs);
        }
    }
}
