package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderService {
    TicketOrder placeOrder(String movieTitle, Long numberOfTickets, User user, LocalDateTime dateCreated);
    List<TicketOrder> findOrdersInTimeInterval(LocalDateTime from, LocalDateTime to);
}
