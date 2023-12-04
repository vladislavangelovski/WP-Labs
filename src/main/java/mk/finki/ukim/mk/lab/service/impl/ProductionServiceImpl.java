package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.repository.inMemoryRepository.InMemoryProductionRepository;
import mk.finki.ukim.mk.lab.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;
    private final InMemoryProductionRepository inMemoryProductionRepository;


    public ProductionServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository, InMemoryProductionRepository inMemoryProductionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
        this.inMemoryProductionRepository = inMemoryProductionRepository;
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
    public Production saveProduction(String productionName, String country, String address) {
        return this.productionRepository.save(new Production(productionName, country, address));
    }

    @Override
    @Transactional
    public void transferDataToDatabase() {
        List<Production> inMemoryProductions = inMemoryProductionRepository.findAll();
        for(Production inMemoryProduction : inMemoryProductions) {
            Production production = new Production();
            production.setName(inMemoryProduction.getName());
            production.setCountry(inMemoryProduction.getCountry());
            production.setAddress(inMemoryProduction.getAddress());

            productionRepository.save(production);
        }
    }
}
