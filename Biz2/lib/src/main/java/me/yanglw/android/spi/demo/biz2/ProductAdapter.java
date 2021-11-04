package me.yanglw.android.spi.demo.biz2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.yanglw.android.spi.demo.biz2.service.Product;
import me.yanglw.android.spi.demo.view.ItemClickListener;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {
    private ItemClickListener listener;
    private List<Product> list;

    protected ProductAdapter(List<Product> list, ItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biz2_item_product, parent, false);
        VH holder = new VH(view);
        view.setOnClickListener(v -> listener.onClick(v, holder.getAdapterPosition()));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    protected static class VH extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView desp;

        public VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.biz2_text1);
            desp = itemView.findViewById(R.id.biz2_text2);
        }

        protected void bind(Product product) {
            name.setText(product.name);
            desp.setText(product.desp);
        }
    }
}

