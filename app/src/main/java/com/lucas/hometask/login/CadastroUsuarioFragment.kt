package com.lucas.hometask.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.lucas.hometask.LiveDatabase
import com.lucas.hometask.R
import com.lucas.hometask.model.Usuario
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView

class CadastroUsuarioFragment : Fragment(), CadastroUsuarioAdapter.OnItemClickListener {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var containerHasName: View
    private lateinit var containerHasNotName: View
    private lateinit var textName: TextInputEditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnContinue: MaterialButton

    private var usuario = Usuario()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            usuario = arguments!!.getSerializable("usuario") as Usuario
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cadastro_user, container, false)
        setupInterface(view)

        return view
    }

    private fun setupInterface(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { if (activity != null) activity!!.supportFragmentManager.popBackStack() }
        containerHasName = view.findViewById(R.id.hasNameLayout)
        containerHasNotName = view.findViewById(R.id.hasNotNameLayout)
        textName = view.findViewById(R.id.editTextNome)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.adapter = CadastroUsuarioAdapter(this, context)
        btnContinue = view.findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener(onClickContinue())
    }

    private fun onClickContinue(): View.OnClickListener {
        return View.OnClickListener { LiveDatabase.getInstance().createUser(usuario) }
    }

    override fun onListClick(integer: Int?) {

    }

    companion object {
        val TAG_ON_BACKSTACK = "CadastroUsuario"
    }
}
