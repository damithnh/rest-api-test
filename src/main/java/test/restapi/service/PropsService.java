package test.restapi.service;

import test.restapi.dto.ProductDto;
import test.restapi.dto.RegisterRequest;
import test.restapi.model.Order;
import test.restapi.model.Product;
import test.restapi.model.User;
import test.restapi.repository.OrderRepository;
import test.restapi.repository.ProductRepository;
import test.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropsService {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private AuthService authService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PropsService(UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository,
                        AuthService authService,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    public String editUser(RegisterRequest registerRequest) {
        User user = mapEditingUser(registerRequest);
        userRepository.save(user);
        return "User has been updated!";
    }

    private User mapEditingUser(RegisterRequest registerRequest) {
        org.springframework.security.core.userdetails.User currUser = authService.getCurrUser();
        String currUsername = currUser.getUsername();
        User user = userRepository.findUserByUsername(currUsername);
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setEnabled(true);
        return user;
    }

    public String editProduct(ProductDto productDto, long id) {
        Product product = mapEditingProduct(productDto, id);
        productRepository.save(product);
        return "Product has been updated!";
    }

    private Product mapEditingProduct(ProductDto productDto, long id) {
        Product product = productRepository.findProductById(id);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        editOrdersRelatedToProduct(product);
        return product;
    }

    private void editOrdersRelatedToProduct(Product product) {
        long id = product.getId();
        List<Order> orders = orderRepository.findOrderByProductId(id);
        for(int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            order.setTotalPrice(product.getPrice());
        }
    }

    public String cancelOrder(long id) {
        Order order = orderRepository.findOrderById(id);
        orderRepository.delete(order);
        return "Order has been canceled!";
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
