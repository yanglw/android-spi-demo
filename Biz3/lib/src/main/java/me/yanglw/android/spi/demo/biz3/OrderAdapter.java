package me.yanglw.android.spi.demo.biz3;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import me.yanglw.android.spi.demo.biz3.service.Order;
import me.yanglw.android.spi.demo.view.ItemClickListener;

public class OrderAdapter extends ListAdapter<Order, OrderAdapter.VH> {
    private final ItemClickListener listener;

    protected OrderAdapter(ItemClickListener listener) {
        super(new DiffUtil.ItemCallback<Order>() {
            @Override
            public boolean areItemsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
                return TextUtils.equals(oldItem.id, newItem.id);
            }

            @Override
            public boolean areContentsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
                return true;
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biz3_item_order, parent, false);
        VH holder = new VH(view);
        view.setOnClickListener(v -> listener.onClick(v, holder.getAdapterPosition()));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(getItem(position));
    }

    protected static class VH extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView name;
        private TextView email;

        public VH(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.biz3_text1);
            name = itemView.findViewById(R.id.biz3_text2);
            email = itemView.findViewById(R.id.biz3_text3);
        }

        protected void bind(Order order) {
            id.setText("订单号：" + order.id);
            name.setText("用户名称：" + order.clientName);
            email.setText("用户邮箱：" + order.clientEmail);
        }
    }
}

