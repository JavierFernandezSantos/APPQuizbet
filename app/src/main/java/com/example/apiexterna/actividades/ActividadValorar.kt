package com.example.apiexterna.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import com.example.apiexterna.R

class ActividadValorar : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var btSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_valorar)

        ratingBar=findViewById(R.id.ratingBar)
        ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            Toast.makeText(this, "Valoracion:" +fl, Toast.LENGTH_SHORT).show()
        }

        btSalir=findViewById(R.id.btnSalir)
        btSalir.setOnClickListener{
            val intent=Intent(this,ActividadPrincipal::class.java)
            startActivity(intent)
        }
    }


}