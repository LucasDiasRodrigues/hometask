package com.lucas.hometask;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.lucas.hometask.casa.CadastroCasaActivity;
import com.lucas.hometask.casa.CasaActivity;
import com.lucas.hometask.login.LoginActivity;
import com.lucas.hometask.model.Usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static boolean hasHouse;
    private LiveDatabase database = new LiveDatabase();
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usuario = new Usuario(
                FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                FirebaseAuth.getInstance().getCurrentUser().getEmail());
        usuario.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());

     //   FloatingActionButton fab = findViewById(R.id.fab);

        getUserData();

        findViewById(R.id.btnCadastroCasa).setEnabled(!hasHouse);
        findViewById(R.id.btnHomecasa).setEnabled(hasHouse);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sair){
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    });
        }
        return true;
    }

    public void getUserData(){
        database.getUserData(usuario, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("casa").getValue() != null) {
                    usuario.setIdCasa((String) dataSnapshot.child("casa").getValue());
                    hasHouse = true;
                } else {
                    hasHouse = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                hasHouse = false;
            }
        });
    }

    public void onClickCadastrarCasa(View view){
        startActivity(new Intent(MainActivity.this, CadastroCasaActivity.class));
    }

    public void onClickHomeCasa(View view){
        startActivity(new Intent(MainActivity.this, CasaActivity.class));
    }
}
