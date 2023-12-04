package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findMoviesByTitleOrSummary(String title, String summary);
    List<Movie> findMoviesByRating(double rating);
    void deleteByTitle(String title);

}
