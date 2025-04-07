package com.example.boomboomfrontend.model

data class Player(
    val name: String,
    val hand: MutableList<Card>
) {

     //Simuliert die Auswahl einer Position, wo eine Exploding Kitten-Karte ins Deck gelegt wird.
     //Aktuell über Konsole. Später wird das durch UI ersetzt.
    fun chooseDeckPosition(deckSize: Int): Int {
        println("Wähle eine Position für die Exploding Kitten Karte (0 = oben, $deckSize = unten):")
        return readLine()?.toIntOrNull()?.coerceIn(0, deckSize) ?: 0
    }
}
