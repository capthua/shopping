package com.shooping.cart.controller;

import com.shooping.cart.param.CheckoutParam;
import com.shooping.cart.service.CartService;
import com.shopping.common.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("checkout")
    public ObjectResponse checkout(@RequestBody CheckoutParam param) {
        cartService.checkout(param);
        return new ObjectResponse();
    }

    @GetMapping("goods/{id}")
    public ObjectResponse getGoodsById(@PathVariable("id") Long id) {
        return new ObjectResponse(cartService.getGoodsById(id));
    }


}
