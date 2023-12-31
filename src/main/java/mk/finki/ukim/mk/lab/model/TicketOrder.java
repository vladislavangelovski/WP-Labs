package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movieTitle;
    private Long numberOfTickets;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime dateCreated;

    public TicketOrder(String movieTitle, Long numberOfTickets, User user, LocalDateTime dateCreated) {
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
        this.user = user;
        this.dateCreated = dateCreated;
    }

    public TicketOrder() {
    }
}
