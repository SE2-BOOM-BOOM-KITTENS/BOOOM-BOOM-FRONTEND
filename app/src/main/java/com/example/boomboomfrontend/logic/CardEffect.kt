package com.example.boomboomfrontend.logic

import com.example.boomboomfrontend.model.Player
import com.example.boomboomfrontend.logic.GameManager

interface CardEffect {
    fun apply (player: Player, gameManager: GameManager)
}