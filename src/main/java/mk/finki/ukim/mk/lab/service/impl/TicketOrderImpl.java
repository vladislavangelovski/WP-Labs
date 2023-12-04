package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.TicketOrderRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketOrderImpl implements TicketOrderService {
    private final TicketOrderRepository ticketOrderRepository;

    public TicketOrderImpl(TicketOrderRepository ticketOrderRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, Long numberOfTickets, User user, LocalDateTime dateCreated) {
        TicketOrder order = new TicketOrder(movieTitle, numberOfTickets, user, dateCreated);
        return ticketOrderRepository.save(order);
    }

    @Override
    public List<TicketOrder> findOrdersInTimeInterval(LocalDateTime from, LocalDateTime to) {
        return ticketOrderRepository.findByDateCreatedBetween(from, to);
    }
}
