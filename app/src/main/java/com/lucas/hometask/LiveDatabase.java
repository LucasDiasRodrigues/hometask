package com.lucas.hometask;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lucas.hometask.model.Casa;
import com.lucas.hometask.model.Usuario;

import java.util.ArrayList;

import androidx.annotation.NonNull;

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

    private static DatabaseChangeListener databaseChangeListener;

    private Usuario usuario;
    private Casa casa;

    private static LiveDatabase liveDatabase;

    public static LiveDatabase getInstance() {
        if (liveDatabase == null)
            liveDatabase = new LiveDatabase();
        return liveDatabase;
    }

    public static LiveDatabase getInstance(DatabaseChangeListener databaseChangeListener) {
        LiveDatabase.databaseChangeListener = databaseChangeListener;
        if (liveDatabase == null)
            liveDatabase = new LiveDatabase();
        return liveDatabase;
    }

    public static void setDatabaseChangeListener(DatabaseChangeListener databaseChangeListener) {
        LiveDatabase.databaseChangeListener = databaseChangeListener;
    }

    private LiveDatabase() {
        getDatabaseInstance();
    }

    private void getDatabaseInstance() {
        database = FirebaseDatabase.getInstance();
    }

    public void createUserIfDontExists(final Usuario usuario) {
        database.getReference(PATH_USUARIOS).child(usuario.getId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            createUser(usuario);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void createUser(Usuario usuario) {
        DatabaseReference usuarioRef = database.getReference(PATH_USUARIOS).child(usuario.getId());
        usuarioRef.child(FIELD_USUARIO_NOME).setValue(usuario.getNome());
        //usuarioRef.child(FIELD_USUARIO_IMAGEM).setValue(usuario.getNome());
    }

    public void createNewCasa(Casa casa) {
        DatabaseReference casaRef = database.getReference(PATH_CASAS).push();
        casa.setId(casaRef.getKey());

        casaRef.child(FIELD_CASA_NOME).setValue(casa.getNome());
        casaRef.child(FIELD_CASA_IMAGEM).setValue(casa.getImagem());

        Usuario criador = casa.getMoradores().get(0);
        casaRef.child(FIELD_CASA_MORADORES).child(criador.getId()).setValue(criador.getNome());

        saveCasaOnUserData(criador, casa);
    }

    private void saveCasaOnUserData(Usuario usuario, Casa casa) {
        DatabaseReference usuarioRef = database.getReference(PATH_USUARIOS).child(usuario.getId());
        usuarioRef.child(FIELD_USUARIO_CASA).setValue(casa.getId());
    }

//    public void getUserData(Usuario usuario, ValueEventListener listener) {
//        database.getReference(PATH_USUARIOS).child(usuario.getId()).addListenerForSingleValueEvent(listener);
//    }


    private void updateUserData(final Usuario user) {
        if (getUsuario() == null) this.usuario = user;
        else getUsuario().setId(user.getId());

        database.getReference(PATH_USUARIOS).child(getUsuario().getId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //todo acrescentar outros dados do usuario além da casa
                        if (dataSnapshot.child("casa").getValue() != null) {
                            getUsuario().setIdCasa((String) dataSnapshot.child("casa").getValue());

                            getHouseById(getUsuario().getIdCasa());
                        }
                        if(databaseChangeListener != null) databaseChangeListener.onUserDataChanged(getUsuario());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void getHouseById(String houseId) {
        if(getCasa() == null) casa = new Casa();
        getCasa().setId(houseId);

        database.getReference(PATH_CASAS).child(houseId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            getCasa().setNome((String) dataSnapshot.child(FIELD_CASA_NOME).getValue());
                            getCasa().setImagem((String) dataSnapshot.child(FIELD_CASA_IMAGEM).getValue());
                            //moradores
                            if(dataSnapshot.child(FIELD_CASA_MORADORES).getChildrenCount() > 1){
                                ArrayList<Usuario> moradores = new ArrayList<>();
                                for(DataSnapshot morador : dataSnapshot.child(FIELD_CASA_MORADORES).getChildren()){
                                    Usuario user = new Usuario();
                                    user.setId(morador.getKey());
                                    user.setNome((String) morador.getValue());
                                    getCasa().addMorador(user);
                                }
                            }
                        }
                        if(databaseChangeListener != null) databaseChangeListener.onHouseDataChanged(getCasa());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void getPersistentUserData(Usuario usuario) {
        updateUserData(usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Casa getCasa() {
        return casa;
    }

    public interface DatabaseChangeListener {
        void onUserDataChanged(Usuario usuario);
        void onHouseDataChanged(Casa casa);
    }
}


