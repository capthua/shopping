package com.shooping.cart.service;

import com.shooping.cart.param.CheckoutParam;
import com.shopping.common.response.ObjectResponse;

public interface CartService {

    ObjectResponse checkout(CheckoutParam checkoutParam);

    ObjectResponse getGoodsById(Long id);

}
