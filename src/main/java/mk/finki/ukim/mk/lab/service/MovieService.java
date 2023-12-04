package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    Optional<Movie> searchMovies(String text);
    List<Movie> searchMoviesByRating(double rating);
    Optional<Movie> findById(Long id);

    Optional<Movie> saveMovie(String movieTitle, String summary, double rating, Long productionId);

    void deleteById(Long id);
    void transferDataToDatabase();
}
