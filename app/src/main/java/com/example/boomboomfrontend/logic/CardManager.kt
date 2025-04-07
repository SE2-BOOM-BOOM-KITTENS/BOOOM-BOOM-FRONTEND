package com.example.boomboomfrontend.logic

import com.example.boomboomfrontend.model.Card
import com.example.boomboomfrontend.model.CardType
import com.example.boomboomfrontend.model.Player
import kotlin.random.Random

class CardManager {

    private val deck: MutableList<Card> = mutableListOf()
    private val discardPile: MutableList<Card> = mutableListOf()

    fun initializeDeck(players: List<Player>) {
        deck.clear()

        // Sprint 1: Nur drei Kartentypen
        repeat(players.size - 1) { deck.add(Card(CardType.EXPLODING_KITTEN)) }
        repeat(players.size) { deck.add(Card(CardType.DEFUSE)) }
        repeat(10) { deck.add(Card(CardType.BLANK)) }

        deck.shuffle()
    }

    fun drawCard(): Card? = if (deck.isNotEmpty()) deck.removeFirst() else null

    fun playCard(card: Card, player: Player): Boolean {
        return if (player.hand.remove(card)) {
            discardPile.add(card)

            // Neue Logik: Effekt aus Registry holen und ausführen
            val effect = CardEffectRegistry.getEffect(card.type)
            effect.apply(player, gameManager)

            true
        } else false
    }

    fun returnCardToDeckAt(card: Card, position: Int) {
        val safePosition = position.coerceIn(0, deck.size) // falls Spieler Unsinn eingibt
        deck.add(safePosition, card)
    }

    fun deckSize(): Int = deck.size
}

