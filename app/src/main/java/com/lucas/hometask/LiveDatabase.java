package com.lucas.hometask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lucas.hometask.model.Casa;
import com.lucas.hometask.model.Usuario;

public class LiveDatabase {

    private static final String PATH_CASAS = "casas";
    private static final String PATH_USUARIOS = "usuarios";

    private static final String FIELD_USUARIO_NOME = "nome";
    private static final String FIELD_USUARIO_CASA = "casa";
    private static final String FIELD_USUARIO_IMAGEM = "imagem";

    private static final String FIELD_CASA_NOME = "nome";
    private static final String FIELD_CASA_IMAGEM = "imagem";
    private static final String FIELD_CASA_MORADORES = "moradores";


    private FirebaseDatabase database;

    public LiveDatabase(){
        getDatabaseInstance();
    }

    private void getDatabaseInstance(){
        database = FirebaseDatabase.getInstance();
    }

    public void createUser(Usuario usuario){
        DatabaseReference usuarioRef = database.getReference(PATH_USUARIOS).child(usuario.getEmail());
        usuarioRef.child(FIELD_USUARIO_NOME).setValue(usuario.getNome());
        //usuarioRef.child(FIELD_USUARIO_IMAGEM).setValue(usuario.getNome());
    }

    public void createNewCasa(Casa casa){
        DatabaseReference casaRef = database.getReference(PATH_CASAS).push();
        casa.setId(casaRef.getKey());

        casaRef.child(FIELD_CASA_NOME).setValue(casa.getNome());
        casaRef.child(FIELD_CASA_IMAGEM).setValue(casa.getNome());

        Usuario criador = casa.getMoradores().get(0);
        casaRef.child(FIELD_CASA_MORADORES).child(criador.getEmail()).setValue(criador.getNome());

        saveCasaOnUserData(criador, casa);
    }

    private void saveCasaOnUserData(Usuario usuario, Casa casa){
        DatabaseReference usuarioRef = database.getReference(PATH_USUARIOS).child(usuario.getEmail());
        usuarioRef.child(FIELD_USUARIO_CASA).setValue(casa.getId());
    }
}
