package me.yanglw.android.spi.demo.biz3.service.order;

/**
 * Created by yanglw on 2018-05-28.
 */
public interface Biz3OrderService {
    Biz3Order getOrderById(String id);

    void syncGetOrderById(String id, Callback callback);

    public interface Callback {
        void onReposone(Biz3Order order);
    }
}
