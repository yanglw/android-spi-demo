package me.yanglw.android.spi.demo.biz4;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
public class Biz4HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.biz4_fragment_home, container, false);
        view.findViewById(R.id.biz4_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent("me.yanglw.android.spi.demo.biz3.action"));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "can not open Biz3Activity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final TextView textView = view.findViewById(R.id.biz4_text);
        Iterator<Biz3OrderService> iterator = ServiceLoader.load(Biz3OrderService.class).iterator();
        if (iterator.hasNext()) {
            Biz3OrderService service = iterator.next();
            service.syncGetOrderById("123456", new Biz3OrderService.Callback() {
                @Override
                public void onReposone(final Biz3Order biz3Order) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("load data from Biz3 Module:\n"
                                             + "client name" + biz3Order.clientName
                                             + "\n"
                                             + biz3Order.clientEmail);
                        }
                    });
                }
            });
        } else {
            textView.setText("can not find Biz3OrderService.");
        }

        return view;
    }
}
