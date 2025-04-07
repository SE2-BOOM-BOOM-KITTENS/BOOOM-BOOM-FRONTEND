package com.example.boomboomfrontend.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.boomboomfrontend.R
import com.example.boomboomfrontend.logic.CardManager
import com.example.boomboomfrontend.logic.GameManager
import com.example.boomboomfrontend.model.Player
import com.example.boomboomfrontend.model.Card
import com.example.boomboomfrontend.model.CardType

class GameActivity : ComponentActivity() {

    private lateinit var drawButton: Button
    private lateinit var statusText: TextView

    private lateinit var gameManager: GameManager
    private lateinit var cardManager: CardManager
    private lateinit var players: MutableList<Player>
    private var currentPlayerIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // View-Verknüpfung
        drawButton = findViewById(R.id.button_draw)
        statusText = findViewById(R.id.text_status)

        // Spieler aus Lobby über Intent empfangen
        players = intent.getParcelableArrayListExtra("PLAYERS") ?: mutableListOf()

        // Wenn keine Spieler übergeben wurden, abbrechen
        if (players.isEmpty()) {
            statusText.text = "Keine Spieler übergeben!"
            drawButton.isEnabled = false
            return
        }

        // Beispielhafte Startkarten
        players.forEach { player ->
            player.hand.addAll(
                listOf(
                    Card(CardType.BLANK),
                    Card(CardType.DEFUSE),
                    Card(CardType.EXPLODING_KITTEN)
                )
            )
        }

        //Spiel initialisieren
        cardManager = CardManager()
        cardManager.initializeDeck(players)
        gameManager = GameManager(cardManager, players)

        // Button: Zug beenden & ziehen
        drawButton.setOnClickListener {
            gameManager.endTurn()
            nextPlayer()
        }

        //Start mit erstem Spieler
        startNextTurn()
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
    }

        private fun nextPlayer() {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size
            startNextTurn()
        }
}
