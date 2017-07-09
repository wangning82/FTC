package com.thinkgem.jeesite.modules.ftc.convert.order;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.convert.customer.AddressConverter;
import com.thinkgem.jeesite.modules.ftc.dto.order.OrderDto;
import com.thinkgem.jeesite.modules.ftc.dto.order.ShoppingCartDto;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderProduct;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyi on 2017/7/8 0008.
 */
@Component
public class OrderConverter extends BaseConverter<Order, OrderDto> {

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private ShoppingCartConverter shoppingCartConverter;

    @Autowired
    private OrderService orderService;

    @Override
    public Order convertDtoToModel(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setOrderStatus(dto.getType());
        order.setAddress(addressConverter.convertDtoToModel(dto.getAddress()));
        List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
        for(ShoppingCartDto shoppingCartDto : dto.getBags()){
            ShoppingCart shoppingCart = shoppingCartConverter.convertDtoToModel(shoppingCartDto);
            OrderProduct orderProduct = orderService.cart2OrderProduct(order, shoppingCart);
            orderProductList.add(orderProduct);
        }
        order.setOrderProductList(orderProductList);
        return order;
    }

    @Override
    public OrderDto convertModelToDto(Order model) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(model.getId());
        orderDto.setType(model.getOrderStatus());
        orderDto.setAddress(addressConverter.convertModelToDto(model.getAddress()));

        List<ShoppingCartDto> shoppingCartDtoList = new ArrayList<ShoppingCartDto>();
        for(OrderProduct orderProduct : model.getOrderProductList()){
            ShoppingCart shoppingCart = orderService.orderProduct2Cart(orderProduct);
            ShoppingCartDto shoppingCartDto = shoppingCartConverter.convertModelToDto(shoppingCart);
            shoppingCartDtoList.add(shoppingCartDto);
        }
        orderDto.setBags(shoppingCartDtoList);
        return orderDto;
    }
}
