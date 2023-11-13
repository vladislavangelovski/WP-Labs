package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductionRepository {
    List<Production> productions;

    public ProductionRepository(List<Production> productions) {
        initializeProductionList();
    }

    private void initializeProductionList() {
        productions = new ArrayList<>();
        productions.add(new Production("Warner Bros.", "United States", "Burbank, California"));
        productions.add(new Production("Paramount Pictures", "United States", "Hollywood, Los Angeles, California"));
        productions.add(new Production("Universal Pictures", "United States", "Universal City, California"));
        productions.add(new Production("20th Century Studios", "United States", " Los Angeles, California"));
        productions.add(new Production("Disney", "United States", " Los Angeles, California"));
    }
    public List<Production> findAll() {
        return productions;
    }
    public Optional<Production> findById(Long productionId) {
        return productions.stream()
                .filter(i -> i.getId().equals(productionId))
                .findFirst();
    }
}
