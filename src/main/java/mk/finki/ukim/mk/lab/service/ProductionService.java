package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService {
    public List<Production> findAll();
    Optional<Production> findById(Long id);
    void deleteById(Long id);
    Optional<Production> saveProduction(String productionName, String country, String address);
}
