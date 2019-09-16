package com.lucas.hometask.casa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.lucas.hometask.R;

public class CadastroCasaActivity extends AppCompatActivity implements CadastroCasaAdapter.OnItemClickListener {

    private MaterialButton btnCadastrar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_casa);

        btnCadastrar = findViewById(R.id.btn_cadastrar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

       // GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        CadastroCasaAdapter adapter = new CadastroCasaAdapter(null, this);//todo lista de imagens
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onListClick(Integer integer) {
        //todo Item clickado
    }
}