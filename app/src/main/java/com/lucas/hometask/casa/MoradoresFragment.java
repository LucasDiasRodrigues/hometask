package com.lucas.hometask.casa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucas.hometask.LiveDatabase;
import com.lucas.hometask.R;
import com.lucas.hometask.model.Usuario;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MoradoresFragment extends Fragment implements MoradoresListAdapter.OnItemClickListener{

    RecyclerView recyclerView;
    MoradoresListAdapter adapter;
    ArrayList<Usuario> moradores;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moradores, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));

        getMoradores();



        return view;
    }

    private void getMoradores() {
        moradores = LiveDatabase.getInstance().getCasa().getMoradores();
        Log.i("casa", LiveDatabase.getInstance().getCasa().toString());


        adapter = new MoradoresListAdapter(moradores, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onListClick(Usuario morador) {

    }
}
