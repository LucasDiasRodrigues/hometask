package com.lucas.hometask.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.lucas.hometask.MainActivity;
import com.lucas.hometask.R;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements EntrarFragment.EntrarFragInterface {

    private static final int RC_SIGN_IN = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (checkLogin()) onLoginSuccess();
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof EntrarFragment) {
            ((EntrarFragment) fragment).setFragInterface(this);
        }
    }

    public void onClickEntrar(View view) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build()
                        ))
                        .build(),
                RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                onLoginSuccess();
            } else {
                if (response == null) {
                    // User pressed back button
                    Log.i("FIREBASE", "R.string.sign_in_cancelled");
                    // showSnackbar(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.i("FIREBASE", "R.string.no_internet_connection");
                    // showSnackbar(R.string.no_internet_connection);
                    return;
                }

                // showSnackbar(R.string.unknown_error);
                Log.e("FIREBASE", "Sign-in error: ", response.getError());
            }
        }
    }

//    public void onClickCadastrar(View view) {
//        openFragment(new CadastroUsuarioFragment());
//    }

//    public void openFragment(Fragment fragment) {
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frame_layout, fragment);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        ft.addToBackStack(null);
//        ft.commitAllowingStateLoss();
//    }

    public boolean checkLogin() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRecupSenha() {
   //     openFragment(new RecuperarSenhaFragment());
    }
}
