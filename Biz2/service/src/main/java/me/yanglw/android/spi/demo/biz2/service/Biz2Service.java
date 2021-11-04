package me.yanglw.android.spi.demo.biz2.service;

import androidx.fragment.app.Fragment;

/**
 * Created by yanglw on 2018-05-28.
 */
public interface Biz2Service {
    Product getRecommendProduct();

    Product getProductById(String id);

    void openProductDetailPage(Fragment fragment, String productId);
}
