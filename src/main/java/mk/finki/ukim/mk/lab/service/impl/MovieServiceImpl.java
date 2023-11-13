package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.exceptions.ProductionNotFoundException;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return this.movieRepository.searchMovies(text);
    }

    @Override
    public List<Movie> searchMoviesByRating(int rating) {
        return this.movieRepository.searchMoviesByRating(rating);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> saveMovie(String movieTitle, String summary, double rating, Long productionId) {
        Production production = this.productionRepository.findById(productionId)
                .orElseThrow(() -> new ProductionNotFoundException(productionId));
        return this.movieRepository.saveMovie(movieTitle, summary, rating, production);
    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }
}
