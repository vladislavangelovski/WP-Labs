package mk.finki.ukim.mk.lab.model.exceptions;

public class ProductionNotFoundException extends RuntimeException{
    public ProductionNotFoundException(Long productionId) {
        super(String.format("Production with id %d does not exist.", productionId));
    }
}
