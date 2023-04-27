package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Item;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Item> items;
    private ItemListener itemListener;

    public RecycleViewAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name.setText(item.getName());
        holder.kh.setText(item.getKh());
        holder.date.setText(item.getDate());
        holder.hl.setText(item.getHl());
        holder.service.setText(item.getService());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Item getItem(int position){
        return items.get(position);
    }

    public ItemListener getItemListener() {
        return itemListener;
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, kh, date, hl, service;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.tvName);
            kh = view.findViewById(R.id.tvKh);
            date = view.findViewById(R.id.tvDate);
            hl = view.findViewById(R.id.tvHl);
            service = view.findViewById(R.id.tvService);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view, int position);
    }


}
