package com.app.Controller;

import com.app.Entity.Cart;
import com.app.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(path = "/carts/{cartId}")
    public Cart getCartByCartId(@PathVariable int  cartId){
        return cartService.getCartByCartId(cartId);
    }
}
