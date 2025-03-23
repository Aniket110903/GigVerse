package demo.demo.controller;

import demo.demo.constants.Constants;
import demo.demo.dto.ResponseGeneral;
import demo.demo.dto.gigDTO.GigPayload;
import demo.demo.dto.gigDTO.GigResponse;
import demo.demo.services.GigsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gig")
public class GigsController {

    @Autowired
    GigsService gigsService;

    @PostMapping
    public ResponseEntity<ResponseGeneral<GigResponse>> createGig(@RequestBody GigPayload payload){
        try {
            return ResponseEntity.ok(ResponseGeneral.success(gigsService.createGig(payload), "Gig created Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseGeneral<GigResponse>> deleteGig(@RequestParam String id){
        try {
            gigsService.deleteGig(id);
            return ResponseEntity.ok(ResponseGeneral.success(null, "Gig deleted Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }
    @GetMapping
    public ResponseEntity<ResponseGeneral<GigResponse>> getGig(@RequestParam String id){
        try {
            return ResponseEntity.ok(ResponseGeneral.success(gigsService.getGig(id), "Gig Found Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseGeneral<List<GigResponse>>> searchGigs(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(defaultValue = "newest") String sortBy
    ){
        try{
            return ResponseEntity.ok(ResponseGeneral.success(gigsService.getFilteredGigs(userId,category,minPrice,maxPrice,sortBy),"Gigs Fetched Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));

        }

    }
}
