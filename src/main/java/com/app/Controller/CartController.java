package com.app.Controller;

import com.app.Entity.Cart;
import com.app.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(path = "/carts/{cartId}")
    public Cart getCartByCartId(@PathVariable Integer  cartId){
        return cartService.getCartByCartId(cartId);
    }

    @PostMapping(path = "/carts")
    public Cart addToCart(@RequestBody Cart cart){
        return cartService.addToCart(cart);
    }

    @DeleteMapping(path = "/carts")
    public Cart removeCart(@RequestParam(required = false) Integer cartId ,@RequestParam(required = false) Integer customerId){
        if(cartId != null){
            return cartService.deleteCartByCartId(cartId);
        } else if (customerId != null) {
            return cartService.deleteCartByCustomerId(customerId);
        }else{
            return null;
        }
    }
}
