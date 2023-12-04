package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.TicketOrderNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.UserNotFoundException;
import mk.finki.ukim.mk.lab.repository.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.TicketOrderRepository;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final TicketOrderRepository ticketOrderRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, TicketOrderRepository ticketOrderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public Optional<ShoppingCart> findByUser(User user) {
        return shoppingCartRepository.findShoppingCartByUser(user);
    }

    @Override
    public List<TicketOrder> listAllOrdersInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
        {
            throw new ShoppingCartNotFoundException(cartId);
        }
        return this.shoppingCartRepository.findById(cartId).get().getTicketOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return this.shoppingCartRepository
                .findShoppingCartByUser(user)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addOrderToShoppingCart(String username, Long orderId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        TicketOrder order = this.ticketOrderRepository.findById(orderId)
                .orElseThrow(() -> new TicketOrderNotFoundException(orderId));
        if(shoppingCart.getTicketOrders()
                .stream().filter(i -> i.getId().equals(orderId))
                .collect(Collectors.toList()).size() > 0){
            throw new RuntimeException();
        }
        shoppingCart.getTicketOrders().add(order);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
