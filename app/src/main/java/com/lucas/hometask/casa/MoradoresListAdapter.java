package com.lucas.hometask.casa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucas.hometask.R;
import com.lucas.hometask.model.Regra;
import com.lucas.hometask.model.Usuario;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MoradoresListAdapter extends RecyclerView.Adapter<MoradoresListAdapter.MyViewHolder> {

    ArrayList<Usuario> list;
    MoradoresListAdapter.OnItemClickListener callback;

    public MoradoresListAdapter(ArrayList<Usuario> list, MoradoresListAdapter.OnItemClickListener callback) {
        this.callback = callback;
        this.list = list;
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
        holder.name.setText(list.get(position).getNome());
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
        void onListClick(Usuario morador);
    }
}
