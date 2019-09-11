package com.lucas.hometask.casa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapePathModel;
import com.lucas.hometask.R;

public class CadastroCasaActivity extends AppCompatActivity {

    private MaterialButton btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_casa);


        btnCadastrar = findViewById(R.id.btn_cadastrar);


    }
}
