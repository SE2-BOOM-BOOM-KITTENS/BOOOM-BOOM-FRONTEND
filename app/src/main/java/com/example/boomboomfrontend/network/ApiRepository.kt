package com.example.boomboomfrontend.network

import com.example.boomboomfrontend.model.Player

class ApiRepository {
    suspend fun addPlayer(player: Player) = RetrofitInstance.api.addPlayer(player)
    suspend fun getAllPlayers() = RetrofitInstance.api.getAllPlayers()
}
