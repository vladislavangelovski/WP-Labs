package mk.finki.ukim.mk.lab.repository.inMemoryRepository;

import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static mk.finki.ukim.mk.lab.bootstrap.DataHolder.productions;

@Repository
public class InMemoryProductionRepository {

    public List<Production> findAll() {
        return productions;
    }
    public Optional<Production> findById(Long productionId) {
        return productions.stream()
                .filter(i -> i.getId().equals(productionId))
                .findFirst();
    }

    public void deleteById(Long id) {
        productions.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Production> saveProduction(String productionName, String country, String address) {
        Production production = new Production(productionName, country, address);
        productions.removeIf(m -> m.getName().equals(production.getName()));
        productions.add(production);
        return Optional.of(production);
    }
}
