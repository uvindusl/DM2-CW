package com.app.Controller;

import com.app.Entity.Cart;
import com.app.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

//    @GetMapping(path = "/carts/{cartId}")
//    public Cart getCartByCartId(@PathVariable Integer  cartId){
//        return cartService.getCartByCartId(cartId);
//    }
//    //http://localhost:8080/carts/{cartId}

    @GetMapping(path = "/carts/{customerId}")
    public List<Cart> getByCustomerId(@PathVariable Integer customerId){
        return cartService.getByCustomerId(customerId);
    }

    @PostMapping(path = "/carts")
    public Cart addToCart(@RequestBody Cart cart){
        return cartService.addToCart(cart);
    }
    //http://localhost:8080/carts

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
    //if you want to delete using customerId use this http://localhost:8080/carts?customerId=1
    //if you want to delete using cartId use this http://localhost:8080/carts?cartId=1
}
