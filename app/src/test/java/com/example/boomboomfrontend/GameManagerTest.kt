import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.example.boomboomfrontend.logic.*
import com.example.boomboomfrontend.model.*

class GameManagerTest {

    private lateinit var gameManager: GameManager
    private lateinit var cardManager: CardManager
    private lateinit var players: MutableList<Player>
    private lateinit var testPlayer: Player

    @BeforeEach
    fun setup() {
        testPlayer = Player(
            id = "1",
            name = "Test",
            status = ConnectionStatus.JOINED,
            hand = mutableListOf()
        )
        players = mutableListOf(testPlayer)
        cardManager = CardManager()
        cardManager.initializeDeck(players)
        gameManager = GameManager(cardManager, players)
    }

    @Test
    fun `Spielzug spielt Karte und zieht neue Karte`() {
        val initialDeckSize = cardManager.deckSize()
        val initialHandSize = testPlayer.hand.size

        // Spieler spielt die erste Karte aus der Hand
        gameManager.startTurn(testPlayer)
        // Spieler beendet den Zug (zieht Karte)
        gameManager.endTurn()

        // Erwartung: 1 Karte gespielt, 1 neue gezogen → Handgröße bleibt gleich
        assertEquals(initialHandSize, testPlayer.hand.size)

        // Erwartung: 1 Karte wurde gezogen → Deckgröße reduziert sich um 1
        assertEquals(initialDeckSize - 1, cardManager.deckSize())
    }

    @Test
    fun `Zug ohne Karte spielen funktioniert ebenfalls`() {
        // Leere Hand → Spieler kann nichts spielen
        testPlayer.hand.clear()

        val initialDeckSize = cardManager.deckSize()
        val initialHandSize = testPlayer.hand.size

        gameManager.startTurn(testPlayer) // sollte nichts tun
        gameManager.endTurn()             // Spieler zieht nur eine Karte

        // Erwartung: Handgröße +1 (nur Karte gezogen)
        assertEquals(initialHandSize + 1, testPlayer.hand.size)

        // Erwartung: Deckgröße -1
        assertEquals(initialDeckSize - 1, cardManager.deckSize())
    }

    /*@Test
    fun `Exploding Kitten mit Defuse wird zurückgelegt`() {
        //wird noch hinzugefügt
    }*/
}
