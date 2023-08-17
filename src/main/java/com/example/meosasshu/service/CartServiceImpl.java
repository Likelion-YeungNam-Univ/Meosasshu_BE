package com.example.meosasshu.service;

import com.example.meosasshu.dto.request.CartItemReqDTO;
import com.example.meosasshu.dto.response.CartItemResDTO;
import com.example.meosasshu.dto.response.OrderFormResDTO;
import com.example.meosasshu.dto.response.OrderProductDTO;
import com.example.meosasshu.entity.Account;
import com.example.meosasshu.entity.Cart;
import com.example.meosasshu.entity.CartProduct;
import com.example.meosasshu.entity.Product;
import com.example.meosasshu.exception.CartProductNotFoundException;
import com.example.meosasshu.exception.ProductNotFoundException;
import com.example.meosasshu.exception.UserNotFoundException;
import com.example.meosasshu.repository.AccountRepository;
import com.example.meosasshu.repository.CartProductRepository;
import com.example.meosasshu.repository.CartRepository;
import com.example.meosasshu.repository.ProductRepository;
import com.example.meosasshu.common.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final JwtUtil jwtUtil;
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Override
    public void addToCart(Account account, CartItemReqDTO cartItemRequest) {
        Cart cart = cartRepository.findByAccount(account).orElseGet(() -> createCart(account));

        Product product = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        CartProduct cartProduct = cartProductRepository.findByCartAndProduct(cart, product)
                .orElseGet(() -> createCartProduct(cart, product));

        cartProduct.setQuantity(cartProduct.getQuantity() + cartItemRequest.getQuantity());
    }

    @Override
    public List<CartItemResDTO> getCartItems(Account account) {
        Cart cart = cartRepository.findByAccount(account).orElseGet(() -> createCart(account));

        List<CartProduct> cartProducts = cartProductRepository.findByCart(cart);
        List<CartItemResDTO> cartItemResDTOs = new ArrayList<>();

        for (CartProduct cartProduct : cartProducts) {
            cartItemResDTOs.add(CartItemResDTO.createDTO(cartProduct));
        }

        return cartItemResDTOs;
    }

    @Override
    public CartItemResDTO updateCartItemQuantity(Account account, Long productId, int quantity) {
        Cart cart = cartRepository.findByAccount(account).orElseGet(() -> createCart(account));

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        CartProduct cartProduct = cartProductRepository.findByCartAndProduct(cart, product)
                .orElseGet(() -> createCartProduct(cart, product));

        cartProduct.setQuantity(quantity);

        return CartItemResDTO.createDTO(cartProduct);
    }

    @Override
    public void removeCartItem(Account account, Long productId) {
        Cart cart = cartRepository.findByAccount(account)
                .orElseGet(() -> createCart(account));

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        CartProduct cartProduct = cartProductRepository.findByCartAndProduct(cart, product)
                .orElseThrow(CartProductNotFoundException::new);

        cartProductRepository.delete(cartProduct);
    }

    @Override
    public OrderFormResDTO getOrderForm(Account account) {
        Cart cart = cartRepository.findByAccount(account)
                .orElseGet(() -> createCart(account));

        List<CartProduct> cartProducts = cartProductRepository.findByCart(cart);
        List<OrderProductDTO> orderProductDTOs = new ArrayList<>();
        double totalPrice = 0;

        for (CartProduct cartProduct : cartProducts) {
            Product product = cartProduct.getProduct();
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setProductId(product.getId());
            orderProductDTO.setProductName(product.getName());
            orderProductDTO.setBrand(product.getBrand());
            orderProductDTO.setQuantity(cartProduct.getQuantity());
            orderProductDTO.setTotalPrice(cartProduct.getQuantity() * product.getPrice());
            orderProductDTO.setImageUrl(product.getThumbnail());

            orderProductDTOs.add(orderProductDTO);
            totalPrice += orderProductDTO.getTotalPrice();
        }

        OrderFormResDTO orderFormResDTO = new OrderFormResDTO();
        orderFormResDTO.setOrderProducts(orderProductDTOs);
        orderFormResDTO.setTotalPrice(totalPrice);

        return orderFormResDTO;
    }


    private Cart createCart(Account account) {
        Cart cart = new Cart();
        cart.setAccount(account);
        return cartRepository.save(cart);
    }

    private CartProduct createCartProduct(Cart cart, Product product) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(0);
        return cartProductRepository.save(cartProduct);
    }
}