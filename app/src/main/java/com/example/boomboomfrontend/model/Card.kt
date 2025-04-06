package com.example.boomboomfrontend.model

import com.example.boomboomfrontend.logic.GameManager

abstract class Card(val name: String) {
    abstract fun play(gameManager: GameManager, player: Player)
}
