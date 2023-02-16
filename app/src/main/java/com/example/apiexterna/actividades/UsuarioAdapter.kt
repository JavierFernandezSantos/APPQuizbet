package com.example.apiexterna.actividades

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiexterna.R
import com.example.apiexterna.modelos.Usuario

class UsuarioAdapter(val dataSet: List<Usuario>) :
    RecyclerView.Adapter<UsuarioAdapter.ViewHolder>()  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Usuario =dataSet[position]
        holder.itemPosicion.text= (position+1).toString()
        holder.itemNombre.text = item.nombre
        holder.itemApellidos.text= item.apellidos
        holder.itemPuntos.text= item.puntos.toString()
        holder.onClick((position+1).toString())

    }

    override fun getItemCount() =dataSet.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var context: Context
        var itemPosicion: TextView
        var itemNombre: TextView
        var itemApellidos: TextView
        var itemPuntos: TextView
        var btInfo: Button
        init {
            itemPosicion=itemView.findViewById(R.id.txtPuesto)
            itemNombre=itemView.findViewById(R.id.nombrePerfil)
            itemApellidos=itemView.findViewById(R.id.txtApellidos)
            itemPuntos=itemView.findViewById(R.id.txtPuntos)
            btInfo=itemView.findViewById(R.id.btInfo)
            context=itemView.context

        }

        fun onClick(posicion: String) {
            btInfo.setOnClickListener {
                val intent= Intent(context,ActividadPerfil::class.java)
                intent.putExtra("Nombre",itemNombre.text)
                intent.putExtra("Apellidos",itemApellidos.text)
                intent.putExtra("Puntos",itemPuntos.text)
                intent.putExtra("Posicion",posicion)

                context.startActivity(intent)
            }
        }

    }

}

