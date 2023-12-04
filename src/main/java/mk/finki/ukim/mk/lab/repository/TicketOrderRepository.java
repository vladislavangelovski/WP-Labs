package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    List<TicketOrder> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
