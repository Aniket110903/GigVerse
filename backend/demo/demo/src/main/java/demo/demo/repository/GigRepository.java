package demo.demo.repository;

import demo.demo.entities.Gig;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource
public interface GigRepository extends JpaRepository<Gig,Long> {

    @Query("SELECT g FROM Gig g WHERE " +
            "(:category IS NULL OR SUBSTRING(g.category, 1, 5) = :category) AND " +
            "(:minPrice IS NULL OR g.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR g.price <= :maxPrice) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'newest' THEN g.createdAt END DESC, " +
            "CASE WHEN :sortBy = 'price' THEN g.price END ASC, " +
            "CASE WHEN :sortBy = 'rating' THEN g.totalStar END DESC")
    List<Gig> findGigs(
            @Param("category") String category,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("sortBy") String sortBy
    );
    List<Gig> findByUserId(UUID userId);

    Optional<Gig> findById(@NonNull Long id);



}
