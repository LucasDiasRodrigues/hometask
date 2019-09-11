package com.lucas.hometask.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lucas.hometask.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EntrarFragment extends Fragment {

    private EditText email;
    private EditText senha;
    private Button btnRecupSenha;
    private Button btnContinuar;

    private EntrarFragInterface fragInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrar, container, false);

        email = view.findViewById(R.id.et_email);
        senha = view.findViewById(R.id.et_senha);
        btnRecupSenha = view.findViewById(R.id.btn_esqueciSenha);
        btnContinuar = view.findViewById(R.id.btn_entrar);

        btnRecupSenha.setOnClickListener(onClickRecupSenha());
        btnContinuar.setOnClickListener(onClickContinuar());

        return view;
    }

    private View.OnClickListener onClickRecupSenha(){
        return  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragInterface.onRecupSenha();
            }
        };
    }

    private View.OnClickListener onClickContinuar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo chamar login
                fragInterface.onLoginSuccess();
            }
        };
    }


    public interface EntrarFragInterface {
        void onLoginSuccess();

        void onRecupSenha();
    }

    public void setFragInterface(EntrarFragInterface fragInterface) {
        this.fragInterface = fragInterface;
    }
}


