package com.lucas.hometask.casa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.lucas.hometask.LiveDatabase;
import com.lucas.hometask.R;
import com.lucas.hometask.model.Casa;
import com.lucas.hometask.model.Usuario;

import java.util.ArrayList;
import java.util.Arrays;

public class CadastroCasaActivity extends AppCompatActivity implements CadastroCasaAdapter.OnItemClickListener {

    private MaterialButton btnCadastrar;
    private RecyclerView recyclerView;
    private EditText textNome;

    private String nomeCasa;
    private String imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_casa);

        btnCadastrar = findViewById(R.id.btn_cadastrar);
        textNome = findViewById(R.id.text_nome_casa);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

       // GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        CadastroCasaAdapter adapter = new CadastroCasaAdapter(null, this);//todo lista de imagens
        recyclerView.setAdapter(adapter);
    }

    public void onClickSave(View view){
        if(!textNome.getText().toString().equals("") && imagem != null){

            Usuario usuario = new Usuario(
                    FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                    FirebaseAuth.getInstance().getCurrentUser().getEmail());


            Casa casa = new Casa(textNome.getText().toString());
            ArrayList<Usuario> moradores = new ArrayList<>();
            moradores.add(usuario);
            casa.setMoradores(moradores);

            LiveDatabase database = new LiveDatabase();
            database.createNewCasa(casa);
        }
    }

    @Override
    public void onListClick(Integer integer) {
        //todo Item clickado
        imagem = integer.toString();
    }
}