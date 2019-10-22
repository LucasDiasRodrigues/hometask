package com.lucas.hometask.casa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucas.hometask.R;
import com.lucas.hometask.model.Regra;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MoradoresListAdapter extends RecyclerView.Adapter<MoradoresListAdapter.MyViewHolder> {

    ArrayList<Integer> list;
    Integer selectedPosition;

    MoradoresListAdapter.OnItemClickListener callback;

    public MoradoresListAdapter(ArrayList<Regra> list, MoradoresListAdapter.OnItemClickListener callback) {
        this.callback = callback;
        //this.list = list;
        this.list = new ArrayList<>();
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
        this.list.add(4);
        this.list.add(5);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_residents_card, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //todo
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CircleImageView imageView;
        TextView name;

        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.profile_image);
            name = v.findViewById(R.id.name);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onListClick(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onListClick(Integer integer);
    }
}
