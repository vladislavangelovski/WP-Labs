package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepository productionRepository;
    private final MovieRepository movieRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository, MovieRepository movieRepository) {
        this.productionRepository = productionRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Production> findAll() {
        return this.productionRepository.findAll();
    }

    @Override
    public Optional<Production> findById(Long id) {
        return this.productionRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        List<Movie> movies = movieRepository.findAll();

        for(int i=0; i<movies.size(); i++) {
            Movie movie = movies.get(i);
            if(movie.getProduction().getId().equals(id)) {
                this.movieRepository.deleteById(movie.getId());
            }
        }

        this.productionRepository.deleteById(id);
    }

    @Override
    public Optional<Production> saveProduction(String productionName, String country, String address) {
        return this.productionRepository.saveProduction(productionName, country, address);
    }
}
