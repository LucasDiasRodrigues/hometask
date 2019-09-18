package com.lucas.hometask.casa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lucas.hometask.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CadastroCasaAdapter extends RecyclerView.Adapter<CadastroCasaAdapter.MyViewHolder> {

    ArrayList<Integer> list;
    Integer selectedPosition;

    OnItemClickListener callback;

    public CadastroCasaAdapter(ArrayList<Integer> list, OnItemClickListener callback) {
        this.callback = callback;
        //this.list = list;
        selectedPosition = -1;
        this.list = new ArrayList<>();
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
        this.list.add(4);
        this.list.add(5);
        this.list.add(6);
        this.list.add(7);
        this.list.add(8);
        this.list.add(9);
        this.list.add(10);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.casa_item_list, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.selected.setVisibility(selectedPosition == position ?
                View.VISIBLE :
                View.GONE);


        switch (list.get(position)) {
            case 1:
                holder.imageView.setImageResource(R.drawable.house_1);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.house_2);
                break;
            case 3:
                holder.imageView.setImageResource(R.drawable.house_3);
                break;
            case 4:
                holder.imageView.setImageResource(R.drawable.house_4);
                break;
            case 5:
                holder.imageView.setImageResource(R.drawable.house_1);
                break;
            case 6:
                holder.imageView.setImageResource(R.drawable.house_2);
                break;
            case 7:
                holder.imageView.setImageResource(R.drawable.house_3);
                break;
            case 8:
                holder.imageView.setImageResource(R.drawable.house_4);
                break;
            case 9:
                holder.imageView.setImageResource(R.drawable.house_1);
                break;
            case 10:
                holder.imageView.setImageResource(R.drawable.house_2);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;
        View selected;

        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.image);
            selected = v.findViewById(R.id.selected);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected.setVisibility(View.VISIBLE);
                    changeSelectedItem(getAdapterPosition());
                    callback.onListClick(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public void changeSelectedItem(int adapterposition){
        int unselectedPosition = -1;
        if (selectedPosition != -1)
            unselectedPosition = selectedPosition;
        selectedPosition = adapterposition;

        if (unselectedPosition != -1)
            CadastroCasaAdapter.this.notifyItemChanged(unselectedPosition);
    }

    public interface OnItemClickListener {
        void onListClick(Integer integer);
    }
}


