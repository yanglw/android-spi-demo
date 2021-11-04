package me.yanglw.android.spi.demo.biz3;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.yanglw.android.spi.demo.biz3.service.Order;
import me.yanglw.android.spi.demo.user.UserInfo;
import me.yanglw.android.spi.demo.user.UserManager;

public class OrderModel {
    private static final OrderModel MANAGER = new OrderModel();
    private final List<Order> list = new ArrayList<>();

    private OrderModel() {
    }

    public static OrderModel get() {
        return MANAGER;
    }

    public Order getOrderById(String orderId) {
        for (Order order : list) {
            if (TextUtils.equals(orderId, order.id)) {
                return order;
            }
        }
        return null;
    }

    public Order buy(String productId) {
        Order order = new Order();
        order.id = UUID.randomUUID().toString();
        order.productId = productId;
        UserInfo user = UserManager.get().getCurrentUserInfo();
        order.clientName = user.name;
        order.clientEmail = user.email;
        list.add(order);
        return order;
    }

    public List<Order> getAllOrder() {
        return list;
    }
}
