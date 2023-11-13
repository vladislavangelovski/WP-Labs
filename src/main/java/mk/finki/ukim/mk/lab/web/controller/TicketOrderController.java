package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    @PostMapping
    public String submitOrder(@RequestParam String movieTitle,
                              @RequestParam int numTickets,
                              Model model) {
        model.addAttribute("movieTitle", movieTitle);
        model.addAttribute("numTickets", numTickets);

        return "orderConfirmation";
    }
}
