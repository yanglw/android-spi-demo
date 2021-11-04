package me.yanglw.android.spi.demo.biz2;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import me.yanglw.android.spi.demo.biz2.service.Product;

public class ProductModel {
    public static final ProductModel MODEL = new ProductModel();
    private List<Product> list = new ArrayList<>(1);

    private ProductModel() {
        Product product = new Product();
        product.id = "1";
        product.name = "PS5";
        product.desp = "SONY 大法好！！！";
        list.add(product);
        list.add(product);
        list.add(product);
        list.add(product);
        list.add(product);
        list.add(product);
        list.add(product);
        list.add(product);
        list.add(product);
        list.add(product);
        list.add(product);
    }

    public static ProductModel get() {
        return MODEL;
    }


    public List<Product> getAllProduct() {
        return list;
    }

    public Product getRecommendProduct() {
        return list.get(0);
    }

    public Product getProductById(String id) {
        for (Product product : list) {
            if (TextUtils.equals(id, product.id)) {
                return product;
            }
        }
        return null;
    }
}
