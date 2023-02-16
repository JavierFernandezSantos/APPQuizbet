package com.example.apiexterna.actividades

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apiexterna.R
import com.example.apiexterna.modelos.API.retrofitService
import com.example.apiexterna.modelos.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ActividadPrincipal : AppCompatActivity() {

    private lateinit var txtEmail: TextView
    private lateinit var txtPass: TextView
    private lateinit var btnEntrar: Button
    lateinit var usuarioInicio: Usuario
    lateinit var usuarioInicioPost: String
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar=findViewById(R.id.progressBar)
        txtEmail=findViewById(R.id.txtEmail)
        txtPass=findViewById(R.id.txtPass)
        btnEntrar=findViewById(R.id.btnEntrar)

        pulsarBoton2()


    }


    private fun mostrarExito() {
        runOnUiThread{
            Toast.makeText(this,"Bienvendido",Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarError() {

        runOnUiThread{
            Toast.makeText(this,"Usuario incorrecto",Toast.LENGTH_SHORT).show()
        }

    }

    private fun showLoading() {
        progressBar.visibility= View.VISIBLE
    }
    private fun hideLoading() {

        runOnUiThread{
            progressBar.visibility=View.GONE
        }

    }
    private fun pulsarBoton2(){
        btnEntrar.setOnClickListener(){
            showLoading()
            getInicioSesion()
        }
    }

    private fun getInicioSesion() {
        CoroutineScope(Dispatchers.IO).launch {
            val respuesta: Response<Usuario> = retrofitService.postInicioSesion(txtEmail.text.toString(), txtPass.text.toString())

            if (respuesta.isSuccessful) {
                //val datoID: String
                val datoID: String= respuesta.body()?.id.toString()
                val datoNombre: String= respuesta.body()?.nombre.toString()
                val datoApellidos: String= respuesta.body()?.apellidos.toString()
                val datoEmail: String= respuesta.body()?.email.toString()

                Log.d("DATO", datoID)
                Log.d("DATO", datoNombre)
                mostrarExito()
                usuarioInicioPost = respuesta.body().toString()
                Log.d("DATO", usuarioInicioPost)
                pasarActividad(datoID,datoNombre,datoApellidos,datoEmail)
            }
            else{
                Log.d("Error",respuesta.body().toString())
                mostrarError()
            }

        }
        hideLoading()
    }


    private fun pasarActividad(
        id: String,
        nombre: String,
        apellidos: String,
        email: String

    ){

        val intent=Intent(this,ActividadLista::class.java)
        val b: Bundle=Bundle()
        b.putString("id",id)
        b.putString("nombre",nombre)
        b.putString("apellidos",apellidos)
        b.putString("email",email)
        intent.putExtras(b)
        startActivity(intent)
    }


}