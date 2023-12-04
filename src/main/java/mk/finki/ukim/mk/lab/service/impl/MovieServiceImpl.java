package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.exceptions.ProductionNotFoundException;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.repository.inMemoryRepository.InMemoryMovieRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;
    private final InMemoryMovieRepository inMemoryMovieRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository, InMemoryMovieRepository inMemoryMovieRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
        this.inMemoryMovieRepository = inMemoryMovieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Optional<Movie> searchMovies(String text) {
        return this.movieRepository.findMoviesByTitleOrSummary(text, text);
    }

    @Override
    public List<Movie> searchMoviesByRating(double rating) {
        return this.movieRepository.findMoviesByRating(rating);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return this.movieRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Movie> saveMovie(String movieTitle, String summary, double rating, Long productionId) {
        Production production = this.productionRepository.findById(productionId)
                .orElseThrow(() -> new ProductionNotFoundException(productionId));
        this.movieRepository.deleteByTitle(movieTitle);
        return Optional.of(this.movieRepository.save(new Movie(movieTitle, summary, rating, production)));
    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void transferDataToDatabase() {
        List<Movie> inMemoryMovies = inMemoryMovieRepository.findAll();
        for(Movie inMemoryMovie : inMemoryMovies) {
            Movie movie = new Movie();
            movie.setTitle(inMemoryMovie.getTitle());
            movie.setSummary(inMemoryMovie.getSummary());
            movie.setRating(inMemoryMovie.getRating());
            movie.setProduction(inMemoryMovie.getProduction());

            movieRepository.save(movie);
        }
    }
}
