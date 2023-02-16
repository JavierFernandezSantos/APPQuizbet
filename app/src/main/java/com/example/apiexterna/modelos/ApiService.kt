package com.example.apiexterna.modelos

import com.example.apiexterna.modelos.Usuario
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("iniciarsesion1/{email}/{pass}")
    suspend fun getInicioSesion(@Path("email") email:String,@Path("pass") pass:String): Response<Usuario>

    @FormUrlEncoded
    @POST("iniciarsesion3")
    suspend fun postInicioSesion(@Field("email") email:String,@Field("pass") pass: String): Response<Usuario>

    @GET("listarjugadores")
    suspend fun listarPorPuntos(): Response<List<Usuario>>



}