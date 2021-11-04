package me.yanglw.android.spi.demo.biz2.service;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import me.yanglw.android.spi.ServiceProvider;
import me.yanglw.android.spi.demo.biz2.Biz2Activity;
import me.yanglw.android.spi.demo.biz2.ProductModel;

/**
 * Created by yanglw on 2018-05-28.
 */
@ServiceProvider(services = {Biz2Service.class})
public class Biz2ServiceImpl implements Biz2Service {
    @Override
    public Product getRecommendProduct() {
        return ProductModel.get().getRecommendProduct();
    }

    @Override
    public Product getProductById(String id) {
        return ProductModel.get().getProductById(id);
    }

    @Override
    public void openProductDetailPage(Fragment fragment, String productId) {
        Intent intent = new Intent(fragment.getContext(), Biz2Activity.class);
        intent.putExtra(Biz2Activity.ARG_ID, productId);
        fragment.startActivity(intent);
    }
}
