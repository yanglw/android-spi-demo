package me.yanglw.android.spi.demo.biz3.service;

import android.app.Activity;

import androidx.fragment.app.Fragment;

/**
 * Created by yanglw on 2018-05-28.
 */
public interface Biz3Service {
    void openOrderDetailPage(Fragment fragment, String orderId);

    void openOrderDetailPage(Activity activity, String orderId);

    Order getOrderById(String orderId);

    void buyProduct(String productId, Callback callback);

    interface Callback {
        void onCallback(Order order);
    }
}
