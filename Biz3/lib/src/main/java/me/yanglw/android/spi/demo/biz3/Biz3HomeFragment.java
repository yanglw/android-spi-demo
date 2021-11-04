package me.yanglw.android.spi.demo.biz3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Iterator;
import java.util.List;

import me.yanglw.android.spi.ServiceLoader;
import me.yanglw.android.spi.demo.biz3.service.Order;
import me.yanglw.android.spi.demo.biz2.service.Biz2Service;
import me.yanglw.android.spi.demo.biz2.service.Product;

/**
 * Created by yanglw on 2018-05-28.
 */
public class Biz3HomeFragment extends Fragment {
    private Biz2Service service;
    private OrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.biz3_fragment_home, container, false);

        Iterator<Biz2Service> iterator = ServiceLoader.load(Biz2Service.class).iterator();
        if (iterator.hasNext()) {
            service = iterator.next();
        } else {
            service = null;
        }

        RecyclerView recyclerView = view.findViewById(R.id.biz3_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderAdapter((v, position) -> {
            Order order = adapter.getCurrentList().get(position);
            startActivity(Biz3Activity.getIntent(getContext(), order.id));
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateOrderList();
    }

    private void updateOrderList() {
        if (!isAdded()) return;
        View view = getView();
        if (view == null) return;

        TextView textView1 = view.findViewById(R.id.biz3_text1);
        TextView textView2 = view.findViewById(R.id.biz3_text2);
        View btn = view.findViewById(R.id.biz3_btn);
        RecyclerView recyclerView = view.findViewById(R.id.biz3_recycler);

        List<Order> list = OrderModel.get().getAllOrder();
        if (list.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            textView1.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);

            if (service != null) {
                Product product = service.getRecommendProduct();

                textView2.setVisibility(View.VISIBLE);
                textView2.setText(getString(R.string.biz3_recommend, product.name, product.desp));
                textView2.setOnClickListener(v -> {
                    service.openProductDetailPage(this, product.id);
                });

                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(v -> {
                    OrderModel.get().buy(product.id);
                    Toast.makeText(getContext(), "购买成功", Toast.LENGTH_SHORT).show();
                    updateOrderList();
                });
            } else {
                textView2.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
            }
            return;
        }

        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.submitList(list);
    }
}
