package com.lucas.hometask.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lucas.hometask.R;

public class CadastroUsuarioFragment extends Fragment {
    private Button btnEntrar;
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtSenha;
    private EditText txtConfSenha;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

       btnEntrar = view.findViewById(R.id.btn_entrar);
       txtNome = view.findViewById(R.id.txtNome);
       txtEmail = view.findViewById(R.id.txtEmail);
       txtSenha = view.findViewById(R.id.txtSenha);
       txtConfSenha = view.findViewById(R.id.txtConfSenha);


       return view;
    }
}
