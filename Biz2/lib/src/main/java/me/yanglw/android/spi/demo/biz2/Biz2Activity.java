package me.yanglw.android.spi.demo.biz2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;

import me.yanglw.android.spi.ServiceLoader;
import me.yanglw.android.spi.demo.biz2.service.Product;
import me.yanglw.android.spi.demo.biz3.service.Biz3Service;
import me.yanglw.android.spi.demo.biz3.service.Order;

public class Biz2Activity extends AppCompatActivity {
    public static final String ARG_ID = "arg_id";

    private Biz3Service service;

    public static Intent getIntent(Context context, String id) {
        Intent intent = new Intent(context, Biz2Activity.class);
        intent.putExtra(ARG_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz2_activity_biz2);


        ProductModel model = ProductModel.get();
        Product product = model.getProductById(getIntent().getStringExtra(ARG_ID));
        if (product == null) {
            finish();
            return;
        }

        TextView textView = findViewById(R.id.biz2_text1);
        textView.setText(product.name);
        TextView textView2 = findViewById(R.id.biz2_text2);
        textView2.setText(product.desp);
        View btn = findViewById(R.id.biz2_btn);

        service = getBiz3Service();
        if (service == null) {
            btn.setVisibility(View.GONE);
            return;
        }
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(v -> {
            v.setEnabled(false);
            service.buyProduct(product.id, this::onBuyResult);
        });
    }

    private void onBuyResult(Order order) {
        if (isDestroyed()) return;
        runOnUiThread(() -> {
            View btn = findViewById(R.id.biz2_btn);
            btn.setEnabled(true);
            if (order != null) {
                Toast.makeText(this, "信仰充值成功", Toast.LENGTH_SHORT).show();
                service.openOrderDetailPage(this, order.id);
            } else {
                Toast.makeText(this, "信仰充值失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Biz3Service getBiz3Service() {
        Iterator<Biz3Service> iterator = ServiceLoader.load(Biz3Service.class).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
}
