package mk.finki.ukim.mk.lab.repository.inMemoryRepository;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static mk.finki.ukim.mk.lab.bootstrap.DataHolder.movies;

@Repository
public class InMemoryMovieRepository {



    public List<Movie> findAll() {
        return movies;
    }
    public List<Movie> searchMovies(String text) {
        return movies.stream()
                .filter(movie -> movie.title.contains(text) || movie.summary.contains(text))
                .collect(Collectors.toList());
    }
    public List<Movie> searchMoviesByRating(int rating) {
        return movies.stream()
                .filter(movie -> movie.getRating() >= rating)
                .collect(Collectors.toList());
    }
    public Optional<Movie> saveMovie(String movieTitle, String summary, double rating, Production production) {
        if(production == null) {
            throw new IllegalArgumentException();
        }
        Movie movie = new Movie(movieTitle, summary, rating, production);
        movies.removeIf(m -> m.getTitle().equals(movie.getTitle()));
        movies.add(movie);
        return Optional.of(movie);
    }

    public Optional<Movie> findById(Long id) {
        return movies.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        movies.removeIf(i -> i.getId().equals(id));
    }
}
