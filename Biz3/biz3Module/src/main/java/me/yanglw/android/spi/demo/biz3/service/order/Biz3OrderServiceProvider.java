package me.yanglw.android.spi.demo.biz3.service.order;

import android.os.SystemClock;

import me.yanglw.android.spi.ServiceProvider;

/**
 * Created by yanglw on 2018-05-28.
 */
@ServiceProvider(services = {Biz3OrderService.class})
public class Biz3OrderServiceProvider implements Biz3OrderService {
    @Override
    public Biz3Order getOrderById(String s) {
        Biz3Order order = new Biz3Order();
        order.id = s;
        order.clientName = "yanglw";
        order.clientEmail = "weigeku8@gmail.com";
        return order;
    }

    @Override
    public void syncGetOrderById(final String s, final Callback callback) {
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                callback.onReposone(getOrderById(s));
            }
        }.start();
    }
}
