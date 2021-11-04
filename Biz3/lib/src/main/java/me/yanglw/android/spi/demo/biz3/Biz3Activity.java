package me.yanglw.android.spi.demo.biz3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;

import me.yanglw.android.spi.ServiceLoader;
import me.yanglw.android.spi.demo.biz2.service.Biz2Service;
import me.yanglw.android.spi.demo.biz2.service.Product;
import me.yanglw.android.spi.demo.biz3.service.Order;

public class Biz3Activity extends AppCompatActivity {
    public static final String ARG_ID = "arg_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz3_activity_biz3);

        String id = getIntent().getStringExtra(ARG_ID);
        TextView textView = findViewById(R.id.biz3_text);

        if (!TextUtils.isEmpty(id)) {
            Order order = OrderModel.get().getOrderById(id);
            if (order == null) {
                return;
            }
            textView.append("\nid = " + order.id);
            textView.append("\nclientName = " + order.clientName);
            textView.append("\nclientEmail = " + order.clientEmail);
            Biz2Service service = getBiz2Service();
            if (service != null) {
                Product product = service.getProductById(order.productId);
                textView.append("\nproductName = " + product.name);
                textView.append("\nproductDesp = " + product.desp);
            }
        }
    }

    public static Intent getIntent(Context context, String orderId) {
        Intent intent = new Intent(context, Biz3Activity.class);
        intent.putExtra(ARG_ID, orderId);
        return intent;
    }

    private Biz2Service getBiz2Service() {
        Iterator<Biz2Service> iterator = ServiceLoader.load(Biz2Service.class).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
}
