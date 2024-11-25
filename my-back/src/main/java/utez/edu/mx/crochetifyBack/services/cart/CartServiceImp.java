package utez.edu.mx.crochetifyBack.services.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utez.edu.mx.crochetifyBack.dto.CartResponse;
import utez.edu.mx.crochetifyBack.dto.ProductDTO;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.StockDTO;
import utez.edu.mx.crochetifyBack.dto.requests.cart.CartCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.cart.CartUpdateRequest;
import utez.edu.mx.crochetifyBack.entities.Cart;
import utez.edu.mx.crochetifyBack.entities.CartProduct;
import utez.edu.mx.crochetifyBack.entities.CartProductId;
import utez.edu.mx.crochetifyBack.entities.Stock;
import utez.edu.mx.crochetifyBack.entities.User;
import utez.edu.mx.crochetifyBack.exceptions.CustomNotFoundException;
import utez.edu.mx.crochetifyBack.repositories.CartRepository;
import utez.edu.mx.crochetifyBack.repositories.StockRepository;
import utez.edu.mx.crochetifyBack.repositories.UserRepository;

@Service
public class CartServiceImp implements CartService {
        @Autowired
        private CartRepository cartRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private StockRepository stockRepository;

        @Override
        public ResponseObject getCartById(Long idCart) {
                Cart cart = cartRepository.findById(idCart)
                                .orElseThrow(() -> new CustomNotFoundException("Producto no encontrado"));
                CartResponse responseDTO = convertToCartResponseDTO(cart);
                return createResponseObject("Carrito recuperado con exito", responseDTO);
        }

        @Override
        public ResponseObject createCart(CartCreateRequest request) {

                try {

                        User currentUser = userRepository.findById(request.getIdUser())
                                        .orElseThrow(() -> new CustomNotFoundException(
                                                        "Usuario con ID " + request.getIdUser() + " no encontrado"));

                        Stock currentStock = stockRepository.findById(request.getIdStock())
                                        .orElseThrow(() -> new CustomNotFoundException("Stock no encontrado"));

                        Cart currentCart = Cart.builder()
                                        .user(currentUser)
                                        .total(currentStock.getPrice() * request.getQuantity())
                                        .build();

                        currentCart = cartRepository.save(currentCart);

                        CartProductId cartProductId = CartProductId.builder()
                                        .cartId(currentCart.getIdCart())
                                        .stockId(currentStock.getIdStock())
                                        .build();

                        CartProduct cartProduct = CartProduct.builder()
                                        .id(cartProductId)
                                        .cart(currentCart)
                                        .stock(currentStock)
                                        .quantity(request.getQuantity())
                                        .build();

                        Object ob = cartProduct;

                        System.out.println(ob);
                        List<CartProduct> prod = new ArrayList<>();
                        prod.add(cartProduct);
                        currentCart.setCartProducts(prod);

                        cartRepository.save(currentCart);
                        return createResponseObject("Carrito creado", null);

                } catch (Exception e) {
                        throw new CustomNotFoundException(e.getMessage());
                }
        }

        @Override
        public ResponseObject updateCart(CartUpdateRequest request) {
                try {
                        Cart currentCart = cartRepository.findById(request.getIdCart())
                                        .orElseThrow(() -> new CustomNotFoundException("Carrito no encontrado"));

                        Stock currentStock = stockRepository.findById(request.getIdStock())
                                        .orElseThrow(() -> new CustomNotFoundException("Stock no encontrado"));

                        CartProductId cartProductId = CartProductId.builder()
                                        .cartId(currentCart.getIdCart())
                                        .stockId(currentStock.getIdStock())
                                        .build();
                        Optional<CartProduct> existingCartProduct = currentCart.getCartProducts().stream()
                                        .filter(cp -> cp.getId().getCartId().equals(cartProductId.getCartId()) &&
                                                        cp.getId().getStockId().equals(cartProductId.getStockId()))
                                        .findFirst();

                        if (existingCartProduct.isPresent()) {
                                CartProduct cartProduct = existingCartProduct.get();
                                int newQuantity = cartProduct.getQuantity() + request.getQuantity();
                                cartProduct.setQuantity(newQuantity);

                                double updatedTotal = currentCart.getTotal()
                                                + (currentStock.getPrice() * request.getQuantity());
                                currentCart.setTotal(updatedTotal);
                        } else {
                                CartProduct newCartProduct = CartProduct.builder()
                                                .id(cartProductId)
                                                .cart(currentCart)
                                                .stock(currentStock)
                                                .quantity(request.getQuantity())
                                                .build();

                                currentCart.getCartProducts().add(newCartProduct);

                                double updatedTotal = currentCart.getTotal()
                                                + (currentStock.getPrice() * request.getQuantity());
                                currentCart.setTotal(updatedTotal);
                        }

                        cartRepository.save(currentCart);
                        return createResponseObject("Carrito acutalizado", null);

                } catch (Exception e) {
                        throw new CustomNotFoundException(e.getMessage());
                }
        }

        private ResponseObject createResponseObject(String message, CartResponse cart) {
                Map<String, Object> response = new HashMap<>();
                response.put("cart", cart);
                return new ResponseObject(true, message, response);
        }

        public CartResponse convertToCartResponseDTO(Cart cart) {
                List<StockDTO> stockDTOs = cart.getCartProducts().stream()
                                .map(cartProduct -> StockDTO.builder()
                                                .stockId(cartProduct.getStock().getIdStock())
                                                .color(cartProduct.getStock().getColor())
                                                .quantity(cartProduct.getQuantity())
                                                .product(ProductDTO.builder()
                                                                .idProduct(cartProduct.getStock().getProduct()
                                                                                .getIdProduct())
                                                                .name(cartProduct.getStock().getProduct().getName())
                                                                .description(cartProduct.getStock().getProduct()
                                                                                .getDescription())
                                                                .status(cartProduct.getStock().getProduct().isStatus())
                                                                .build())
                                                .build())
                                .collect(Collectors.toList());

                return CartResponse.builder()
                                .idCart(cart.getIdCart())
                                .total(cart.getTotal())
                                .cartProducts(stockDTOs)
                                .build();
        }

}
