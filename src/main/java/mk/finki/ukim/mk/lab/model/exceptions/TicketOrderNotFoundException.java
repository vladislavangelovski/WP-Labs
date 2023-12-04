package mk.finki.ukim.mk.lab.model.exceptions;

public class TicketOrderNotFoundException extends RuntimeException{
    public TicketOrderNotFoundException(Long orderId) {
        super(String.format("Order with id %d does not exist.", orderId));
    }
}
