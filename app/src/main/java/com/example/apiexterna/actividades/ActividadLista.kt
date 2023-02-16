package com.example.apiexterna.actividades

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiexterna.R
import com.example.apiexterna.modelos.API
import com.example.apiexterna.modelos.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ActividadLista : AppCompatActivity() {

    private lateinit var binding:ActividadPrincipal
    private lateinit var adapter: UsuarioAdapter
    private lateinit var listaUsuarios: List<Usuario>
    private lateinit var btValorar: Button
    private lateinit var btPerfil: Button
    val listaUsu= mutableListOf<Usuario>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_lista)
        btValorar=findViewById(R.id.btnValorar)
        btPerfil=findViewById(R.id.btnPerfil)
        pulsarBoton()

        initReciclerView()
        getUsuarios()
        val bundle=intent.extras
        val datoNombre=bundle?.getString("nombre")
        val datoApellidos=bundle?.getString("apellidos")
        val datoEmail=bundle?.getString("email")

        btPerfil.setOnClickListener{
            val intent=Intent(this, ActividadPerfilUsuario::class.java)
            val b: Bundle=Bundle()
            b.putString("nombre",datoNombre)
            b.putString("apellidos",datoApellidos)
            b.putString(("email"),datoEmail)
            intent.putExtras(b)
            startActivity(intent)
        }
    }

    private fun pulsarBoton() {
        btValorar.setOnClickListener{
            val intent= Intent(this,ActividadValorar::class.java)
            startActivity(intent)
        }
    }

    private fun initReciclerView() {
        adapter = UsuarioAdapter(listaUsu)
        val recyclerView = findViewById<RecyclerView>(R.id.reciclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter=adapter

    }

    private fun getUsuarios()  {
        CoroutineScope(Dispatchers.IO).launch {
            val respuesta: Response<List<Usuario>> = API.retrofitService.listarPorPuntos()
            val lista: List<Usuario>? = respuesta.body()
            runOnUiThread{
                if (respuesta.isSuccessful) {
                    Log.d("BIEN","Funciona")
                    val listaFinal: List<Usuario>? = lista ?: emptyList()
                    listaUsu.clear()
                    if (listaFinal != null) {
                        listaUsu.addAll(listaFinal)
                    }
                    adapter.notifyDataSetChanged()
                }
                else
                    Log.d("Error",respuesta.body().toString())
            }

        }

    }

    private fun pasarActividadPerfilUsuario(
        id: String,
        nombre: String,
        apellidos: String,
        puntos: String,

    ){

        val intent=Intent(this,ActividadPerfilUsuario::class.java)
        val b: Bundle=Bundle()
        b.putString("id",id)
        b.putString("nombre",nombre)
        b.putString("apellidos",apellidos)
        b.putString("email",puntos)
        intent.putExtras(b)

    }
}