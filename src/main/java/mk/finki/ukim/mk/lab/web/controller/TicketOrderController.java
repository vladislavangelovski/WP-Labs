package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;

    public TicketOrderController(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @PostMapping
    public String submitOrder(@RequestParam String movieTitle,
                              @RequestParam Long numTickets,
                              @RequestParam("localDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                  LocalDateTime localDateTime,
                              Model model,
                              HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        TicketOrder order = ticketOrderService.placeOrder(movieTitle,numTickets,new User(user.getUserFullname()), localDateTime);
        model.addAttribute("movieTitle", movieTitle);
        model.addAttribute("numTickets", numTickets);
        model.addAttribute("fullName", user.getUserFullname());
        model.addAttribute("localDateTime", localDateTime);
        model.addAttribute("orderId", order.getId());
        return "orderConfirmation";
    }
}
