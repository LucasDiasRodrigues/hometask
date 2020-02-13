package com.lucas.hometask.casa

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText

import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.lucas.hometask.LiveDatabase
import com.lucas.hometask.R
import com.lucas.hometask.model.Casa
import com.lucas.hometask.model.Usuario

import java.util.ArrayList

class CadastroCasaActivity : AppCompatActivity(), CadastroCasaAdapter.OnItemClickListener {


    private lateinit var btnCadastrar: MaterialButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var textNome: EditText
    private lateinit var nomeCasa: String
    private var imagem: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_casa)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        btnCadastrar = findViewById(R.id.btn_cadastrar)
        if (imagem != null) btnCadastrar.isEnabled = true

        textNome = findViewById(R.id.text_nome_casa)
        textNome.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                btnCadastrar.isEnabled = (s.toString() != "" && imagem != null)
            }
        })

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        // GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val adapter = CadastroCasaAdapter( this)//todo lista de imagens
        recyclerView.adapter = adapter
    }

    fun onClickSave(view: View) {
        if (textNome.text.toString() != "" && imagem != null) {

            val usuario = Usuario(
                    FirebaseAuth.getInstance().currentUser!!.displayName,
                    FirebaseAuth.getInstance().currentUser!!.email)
            usuario.id = FirebaseAuth.getInstance().currentUser!!.uid


            val casa = Casa(textNome.text.toString())
            casa.imagem = imagem
            val moradores = ArrayList<Usuario>()
            moradores.add(usuario)
            casa.moradores = moradores

            val database = LiveDatabase.getInstance()
            database.createNewCasa(casa)

            showDialogCasaSaved()
        }
    }

    private fun showDialogCasaSaved() {
        AlertDialog.Builder(this)
                .setMessage("Sua casa foi criada com sucesso!")
                .setCancelable(false)
                .setPositiveButton("Continuar") { dialog, which ->
                    //todo entrar na casa
                    finish()
                }.show()
    }


    override fun onListClick(integer: Int?) {
        imagem = integer.toString()
        if (textNome.text.toString() != "") btnCadastrar.isEnabled = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}