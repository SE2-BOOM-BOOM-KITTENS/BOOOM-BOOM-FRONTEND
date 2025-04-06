package com.example.boomboomfrontend.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.boomboomfrontend.logic.CardManager
import com.example.boomboomfrontend.logic.GameManager
import com.example.boomboomfrontend.model.Player
import com.example.boomboomfrontend.model.basic.*

class GameActivity : AppCompatActivity() {

    private lateinit var gameManager: GameManager
    private lateinit var cardManager: CardManager
    private lateinit var players: MutableList<Player>
    private var currentPlayerIndex = 0

    // UI-Komponenten - Idee, check dann mit UI Implementierung
    private lateinit var drawButton: Button
    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Views
        drawButton = findViewById(R.id.button_draw)
        statusText = findViewById(R.id.text_status)

        // Initialisiere Spieler und Karten - passiert über die Lobby

        cardManager = CardManager()
        cardManager.initializeDeck(players)

        gameManager = GameManager(cardManager, players)

        startNextTurn()

        drawButton.setOnClickListener {
            gameManager.endTurn()
            startNextTurn()
        }
    }

    private fun startNextTurn() {
        if (players.isEmpty()) {
            statusText.text = "Spiel vorbei!"
            drawButton.isEnabled = false
            return
        }

        val player = players[currentPlayerIndex]
        gameManager.startTurn(player)
        statusText.text = "${player.name} ist dran"

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
    }
}
