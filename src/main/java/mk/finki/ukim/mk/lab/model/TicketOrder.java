package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class TicketOrder {
    public String movieTitle;
    public String clientName;
    public String clientAddress;
    public Long numberOfTickets;

    public TicketOrder(String movieTitle, String clientName, String clientAddress, Long numberOfTickets) {
        this.movieTitle = movieTitle;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
