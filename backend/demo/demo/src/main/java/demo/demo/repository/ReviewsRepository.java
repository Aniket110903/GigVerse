package demo.demo.repository;

import demo.demo.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface ReviewsRepository extends JpaRepository<Reviews, UUID> {

    List<Reviews> findByGigId(Long Id);

}
