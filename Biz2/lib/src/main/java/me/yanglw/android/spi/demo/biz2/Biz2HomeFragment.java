package me.yanglw.android.spi.demo.biz2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.yanglw.android.spi.demo.biz2.service.Product;
import me.yanglw.android.spi.demo.biz3.service.Order;

/**
 * Created by yanglw on 2018-05-28.
 */
public class Biz2HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.biz2_fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.biz2_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Product> list = ProductModel.MODEL.getAllProduct();
        recyclerView.setAdapter(new ProductAdapter(list, (v, position) -> {
            Product product = list.get(position);
            startActivity(Biz2Activity.getIntent(getContext(), product.id));
        }));

        return view;
    }
}
