package me.yanglw.android.spi.demo.biz3;

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
import me.yanglw.android.spi.demo.biz4.service.commodity.Biz4Commodity;
import me.yanglw.android.spi.demo.biz4.service.commodity.Biz4CommodityService;

/**
 * Created by yanglw on 2018-05-28.
 */
public class Biz3HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.biz3_fragment_home, container, false);
        view.findViewById(R.id.biz3_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent("me.yanglw.android.spi.demo.biz4.action"));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "can not open Biz4Activity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textView = view.findViewById(R.id.biz3_text);
        Iterator<Biz4CommodityService> iterator = ServiceLoader.load(Biz4CommodityService.class).iterator();
        if (iterator.hasNext()) {
            Biz4CommodityService service = iterator.next();
            Biz4Commodity commodity = service.getComodityById("123456");
            textView.setText("load data from Biz4 Module:\n"
                             + "commodity name:" + commodity.name
                             + "\n"
                             + "commodity desp:" + commodity.desp);
        } else {
            textView.setText("can not find Biz4CommodityService.");
        }
        return view;
    }
}
