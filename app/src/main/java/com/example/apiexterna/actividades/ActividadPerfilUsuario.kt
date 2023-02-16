package com.example.apiexterna.actividades

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.apiexterna.R

class ActividadPerfilUsuario : AppCompatActivity(){

    private lateinit var txtNombre: TextView
    private lateinit var txtApellidos: TextView
    private lateinit var txtEmail: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_persona_inicio)
        txtNombre=findViewById(R.id.nombrePerfil)
        txtApellidos=findViewById(R.id.apePerfil)
        txtEmail=findViewById(R.id.emailPerfil)


    val bundle=intent.extras
    val datoNombre=bundle?.getString("nombre")
    val datoApellidos=bundle?.getString("apellidos")
    val datoEmail=bundle?.getString("email")

    txtNombre.text=datoNombre.toString()
    txtApellidos.text=datoApellidos.toString()
    txtEmail.text=datoEmail.toString()

    }
}