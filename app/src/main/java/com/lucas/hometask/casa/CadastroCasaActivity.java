package com.lucas.hometask.casa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.lucas.hometask.LiveDatabase;
import com.lucas.hometask.R;
import com.lucas.hometask.model.Casa;
import com.lucas.hometask.model.Usuario;

import java.util.ArrayList;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        btnCadastrar = findViewById(R.id.btn_cadastrar);
        if(imagem != null) btnCadastrar.setEnabled(true);


        textNome = findViewById(R.id.text_nome_casa);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        CadastroCasaAdapter adapter = new CadastroCasaAdapter(null, this);//todo lista de imagens
        recyclerView.setAdapter(adapter);
    }

    public void onClickSave(View view) {
        if (!textNome.getText().toString().equals("") && imagem != null) {

            Usuario usuario = new Usuario(
                    FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                    FirebaseAuth.getInstance().getCurrentUser().getEmail());
            usuario.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());


            Casa casa = new Casa(textNome.getText().toString());
            casa.setImagem(imagem);
            ArrayList<Usuario> moradores = new ArrayList<>();
            moradores.add(usuario);
            casa.setMoradores(moradores);

            LiveDatabase database = new LiveDatabase();
            database.createNewCasa(casa);

            showDialogCasaSaved();
        }
    }

    public void showDialogCasaSaved() {
        new AlertDialog.Builder(this)
                .setMessage("Sua casa foi criada com sucesso!")
                .setCancelable(false)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo entrar na casa
                        finish();
                    }
                }).show();
    }

    @Override
    public void onListClick(Integer integer) {
        //todo Item clickado
        imagem = integer.toString();
        btnCadastrar.setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}