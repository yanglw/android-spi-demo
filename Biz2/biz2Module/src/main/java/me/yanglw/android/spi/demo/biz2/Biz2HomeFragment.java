package me.yanglw.android.spi.demo.biz2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

import me.yanglw.android.spi.ServiceLoader;
import me.yanglw.android.spi.demo.biz3.service.order.Biz3Order;
import me.yanglw.android.spi.demo.biz3.service.order.Biz3OrderService;

/**
 * Created by yanglw on 2018-05-28.
 */
public class Biz2HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.biz2_fragment_home, container, false);

        view.findViewById(R.id.biz2_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent("me.yanglw.android.spi.demo.biz3.action"));
                } catch (Exception e) {
                    Toast.makeText(getActivity(),"can not open Biz3Activity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textView = view.findViewById(R.id.biz2_text);
        Iterator<Biz3OrderService> iterator = ServiceLoader.load(Biz3OrderService.class).iterator();
        if (iterator.hasNext()) {
            Biz3Order order = iterator.next().getOrderById("01234567");
            textView.setText("load data from Biz3 Module:\n"
                             + "client name:" + order.clientName
                             + "\n"
                             + "client email:" + order.clientEmail);
        } else {
            textView.setText("can not find Biz3OrderService.");
        }
        return view;
    }
}
