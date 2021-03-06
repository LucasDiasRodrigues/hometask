package com.lucas.hometask.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lucas.hometask.MainActivity;
import com.lucas.hometask.R;

public class LoginActivity extends AppCompatActivity implements EntrarFragment.EntrarFragInterface {

    private Button btnEntrar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = (Button) findViewById(R.id.btn_entrar);
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        if(fragment instanceof EntrarFragment){
            ((EntrarFragment) fragment).setFragInterface(this);
        }
    }

    public void onClickEntrar(View view) {
        openFragment(new EntrarFragment());
    }

    public void onClickCadastrar(View view){ openFragment(new CadastroUsuarioFragment()); }

    public void openFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRecupSenha() {
        openFragment(new RecuperarSenhaFragment());
    }
}
