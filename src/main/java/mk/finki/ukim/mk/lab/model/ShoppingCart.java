package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime dateCreated;
    @OneToMany
    private List<TicketOrder> ticketOrders;

    public ShoppingCart(User user) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.ticketOrders = new ArrayList<>();
    }

    public ShoppingCart() {
    }
}
