package com.lucas.hometask.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.lucas.hometask.LiveDatabase;
import com.lucas.hometask.R;
import com.lucas.hometask.model.Usuario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class CadastroUsuarioFragment extends Fragment {

    private View containerHasName;
    private View containerHasNotName;
    private TextInputEditText textName;
    private RecyclerView recyclerView;
    private MaterialButton btnContinue;

    private Usuario usuario = new Usuario();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            usuario = (Usuario) getArguments().getSerializable("usuario");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_user, container, false);
        setupInterface(view);

        return view;
    }

    private void setupInterface(View view){
        containerHasName = view.findViewById(R.id.hasNameLayout);
        containerHasNotName = view.findViewById(R.id.hasNotNameLayout);
        textName = view.findViewById(R.id.editTextNome);
        recyclerView = view.findViewById(R.id.recycler_view);
        btnContinue.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(onClickContinue());
    }

    private View.OnClickListener onClickContinue(){
        return  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDatabase.getInstance().createUser(usuario);
            }
        };
    }
}
