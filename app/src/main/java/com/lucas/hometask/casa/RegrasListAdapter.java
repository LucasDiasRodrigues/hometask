package com.lucas.hometask.casa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucas.hometask.R;
import com.lucas.hometask.model.Regra;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RegrasListAdapter extends RecyclerView.Adapter<RegrasListAdapter.MyViewHolder> {

    ArrayList<Integer> list;
    Integer selectedPosition;

    RegrasListAdapter.OnItemClickListener callback;

    public RegrasListAdapter(ArrayList<Regra> list, RegrasListAdapter.OnItemClickListener callback) {
        this.callback = callback;
        //this.list = list;
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
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_rule_card, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        switch (position % 3) {
            case 0:
                holder.text.setText("texto ldjpewhoieu " +
                        "efouewhfweijfipoewjfpwo eofpjoepwfjeopw" +
                        " fiwehwpe fwhepo fhweopf pwhf");
                break;
            case 1:
                holder.text.setText("texto ldjpewhoieu " +
                        "efouewhfweijfipoewjfpwo eofpjoepwfjeopw" +
                        " fiwehwpe fwhepo fhweopf pwhf" +
                        "dhgeiwugdueogoifgeiofgewo" +
                        "egfuige wiufewguifgweuigfiwuegfiew" +
                        "ewgufiwgeiufg weiuufgwieugfiwe");
                break;
            case 2:
                holder.text.setText("texto ldjpewhoieu " +
                        "efouewhfweijfipoewjfpwo eofpjoepwfjeopw" +
                        " fiwehwpe fwhepo fhweopf pwhf" +
                        "dhgeiwugdueogoifgeiofgewo" +
                        "egfuige wiufewguifgweuigfiwuegfiew" +
                        "ewgufiwgeiufg weiuufgwieugfiwe" +
                        "fwegfiwgeiuf gweuigfiweu gfuiew" +
                        "dhgeiwugdueogoifgeiofgewo" +
                        "egfuige wiufewguifgweuigfiwuegfiew" +
                        "ewgufiwgeiufg weiuufgwieugfiwe" +
                        "fwegfiwgeiuf gweuigfiweu gfuiew" +
                        "dhgeiwugdueogoifgeiofgewo" +
                        "egfuige wiufewguifgweuigfiwuegfiew" +
                        "ewgufiwgeiufg weiuufgwieugfiwe" +
                        "fwegfiwgeiuf gweuigfiweu gfuiew" +
                        "gefuwigfuewigfuwiegfwu iegfiuwe" +
                        "gfuiwegufiwgeufi" +
                        "fhiewhfiowhofhwieofhiweofhwi oefwefhowoie");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        TextView text;

        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            text = v.findViewById(R.id.text);
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
