import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.example.boomboomfrontend.logic.*
import com.example.boomboomfrontend.model.*
import com.example.boomboomfrontend.model.basic.*

class GameManagerTest {

    private lateinit var gameManager: GameManager
    private lateinit var cardManager: CardManager
    private lateinit var players: MutableList<Player>
    private lateinit var testPlayer: Player

    @BeforeEach
    fun setup() {
        testPlayer = Player("Test", mutableListOf(BlankCard()))
        players = mutableListOf(testPlayer)
        cardManager = CardManager()
        cardManager.initializeDeck(players)
        gameManager = GameManager(cardManager, players)
    }

    @Test
    fun `Spielzug spielt Karte und zieht neue Karte`() {
        val initialDeckSize = cardManager.deckSize()
        val initialHandSize = testPlayer.hand.size

        gameManager.startTurn(testPlayer)
        gameManager.endTurn()

        // Handgröße bleibt gleich (1 gespielt, 1 gezogen)
        assertEquals(initialHandSize, testPlayer.hand.size)

        // Deck wurde um 1 verkleinert
        assertEquals(initialDeckSize - 1, cardManager.deckSize())
    }

    /*@Test
    fun `Exploding Kitten mit Defuse wird zurückgelegt`() {
        //wird noch hinzugefügt
    }*/
}
