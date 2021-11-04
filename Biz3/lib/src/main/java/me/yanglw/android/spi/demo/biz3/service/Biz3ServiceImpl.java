package me.yanglw.android.spi.demo.biz3.service;

import android.app.Activity;
import android.os.SystemClock;

import androidx.fragment.app.Fragment;

import me.yanglw.android.spi.ServiceProvider;
import me.yanglw.android.spi.demo.biz3.Biz3Activity;
import me.yanglw.android.spi.demo.biz3.OrderModel;

/**
 * Created by yanglw on 2018-05-28.
 */
@ServiceProvider(services = {Biz3Service.class})
public class Biz3ServiceImpl implements Biz3Service {
    @Override
    public void openOrderDetailPage(Fragment fragment, String orderId) {
        fragment.startActivity(Biz3Activity.getIntent(fragment.getContext(), orderId));
    }

    @Override
    public void openOrderDetailPage(Activity activity, String orderId) {
        activity.startActivity(Biz3Activity.getIntent(activity, orderId));
    }

    @Override
    public Order getOrderById(String orderId) {
        return OrderModel.get().getOrderById(orderId);
    }

    @Override
    public void buyProduct(final String productId, final Callback callback) {
        new Thread(() -> {
            SystemClock.sleep(3000);
            callback.onCallback(OrderModel.get().buy(productId));
        }).start();
    }
}
