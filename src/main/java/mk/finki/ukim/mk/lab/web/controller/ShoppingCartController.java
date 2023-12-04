package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final TicketOrderService ticketOrderService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(TicketOrderService ticketOrderService, ShoppingCartService shoppingCartService) {
        this.ticketOrderService = ticketOrderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(HttpServletRequest req,
                                      Model model
                                      )
    {
        User user = (User) req.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        List<TicketOrder> orders =  this.shoppingCartService.listAllOrdersInShoppingCart(shoppingCart.getId());
        model.addAttribute("orders", orders);
        return "shoppingCart";
    }
    @PostMapping("/add-product/{id}")
    public String addOrderToShoppingCart(@PathVariable Long id, HttpServletRequest req)
    {
        try {
            User user = (User) req.getSession().getAttribute("user");
            ShoppingCart shoppingCart = this.shoppingCartService.addOrderToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
    @GetMapping("/filter-orders-between")
    public String filterOrdersBetween( @RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                           LocalDateTime dateFrom,
                                       @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                       LocalDateTime dateTo,
                                       Model model,
                                       HttpServletRequest req)
    {
        User user = (User) req.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        List<TicketOrder> ordersInInterval = this.ticketOrderService.findOrdersInTimeInterval(dateFrom,dateTo);
        model.addAttribute("orders", ordersInInterval);
        return "shoppingCart";
    }

}
