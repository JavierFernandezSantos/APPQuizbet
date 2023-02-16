package com.example.apiexterna.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.apiexterna.R

class ActividadPerfil : AppCompatActivity() {

    private lateinit var txtNombre: TextView
    private lateinit var txtApellidos: TextView
    private lateinit var txtRanking: TextView
    private lateinit var txtPuntos: TextView
    private lateinit var btVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_perfil)

        txtNombre=findViewById(R.id.txtPerfilNombre)
        txtApellidos=findViewById(R.id.txtPerfilApell)
        txtRanking=findViewById(R.id.txtPerfilRanking)
        txtPuntos=findViewById(R.id.txtPerfilPuntos)
        btVolver=findViewById(R.id.btVolver)

        val bundle=intent.extras
        val datoNombre=bundle?.getString("Nombre")
        val datoApellidos=bundle?.getString("Apellidos")
        val datoRanking=bundle?.getString("Posicion")
        val datoPuntos=bundle?.getString("Puntos")

        txtNombre.text=datoNombre
        txtApellidos.text=datoApellidos
        txtRanking.text=datoRanking
        txtPuntos.text=datoPuntos

        btVolver.setOnClickListener {
            val intent=Intent(this,ActividadLista::class.java)
            startActivity(intent)
        }
    }
}