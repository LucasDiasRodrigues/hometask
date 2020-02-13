package com.lucas.hometask.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lucas.hometask.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CadastroUsuarioAdapter extends RecyclerView.Adapter<CadastroUsuarioAdapter.MyViewHolder> {

    ArrayList<Integer> list;
    Integer selectedPosition;

    OnItemClickListener callback;
    Context context;

    public CadastroUsuarioAdapter(OnItemClickListener callback, Context context) {
        this.callback = callback;
        this.context = context;

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
        this.list.add(11);
        this.list.add(12);
        this.list.add(13);
        this.list.add(14);
        this.list.add(15);
        this.list.add(16);
        this.list.add(17);
        this.list.add(18);
        this.list.add(19);
        this.list.add(20);
        this.list.add(21);
        this.list.add(22);
        this.list.add(23);
        this.list.add(24);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_avatar, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.selected.setVisibility(selectedPosition == position ?
                View.VISIBLE :
                View.GONE);

        holder.imageView.setImageResource(
                context.getResources().getIdentifier("avatar_" + (position + 1), "drawable", context.getPackageName()));
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

    public void changeSelectedItem(int adapterposition) {
        int unselectedPosition = -1;
        if (selectedPosition != -1)
            unselectedPosition = selectedPosition;
        selectedPosition = adapterposition;

        if (unselectedPosition != -1)
            this.notifyItemChanged(unselectedPosition);
    }

    public interface OnItemClickListener {
        void onListClick(Integer integer);
    }

}
