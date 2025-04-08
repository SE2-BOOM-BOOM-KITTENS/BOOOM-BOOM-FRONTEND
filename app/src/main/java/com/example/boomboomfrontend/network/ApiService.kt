package com.example.boomboomfrontend.network

import com.example.boomboomfrontend.model.Player
import com.example.boomboomfrontend.model.PlayerResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/players")
    suspend fun addPlayer(@Body player: Player): Response<ResponseBody>

    @GET("/players/allPlayers")
    suspend fun getAllPlayers(): Response<List<PlayerResponse>>
}
