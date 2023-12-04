package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    Optional<ShoppingCart> findByUser(User user);
    List<TicketOrder> listAllOrdersInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addOrderToShoppingCart(String username, Long orderId);
}
