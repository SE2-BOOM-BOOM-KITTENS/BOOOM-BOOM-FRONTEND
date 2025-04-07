package com.example.boomboomfrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import com.example.boomboomfrontend.logic.Lobby
import com.example.boomboomfrontend.logic.Player
import com.example.boomboomfrontend.model.ConnectionStatus
import com.example.boomboomfrontend.ui.theme.BoomBoomFrontendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoomBoomFrontendTheme {
                LobbyScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen() {
    var name by remember { mutableStateOf("") }
    var lobby: Lobby? by remember { mutableStateOf(null) }
    var joined by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Dein Name:")
        TextField(value = name, onValueChange = {name = it})

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if(name.isNotBlank()){
                val host = Player(name, com.example.boomboomfrontend.logic.ConnectionStatus.JOINED)
                lobby = Lobby(host, maxPlayers = 4)
            }
        }) {
            Text("Lobby erstellen")
        }

        Spacer(Modifier.height(8.dp))

        lobby?.let {
            Text("Lobby-Code: ${it.getRoomCode()}")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            val p = Player(name = "Player", com.example.boomboomfrontend.logic.ConnectionStatus.NOT_CONNECTED)
            if (lobby?.joinLobby(p) == true) {
                joined = true
            }
        }) {
            Text("Lobby beitreten")
        }

        Spacer(Modifier.height(16.dp))
        Text("Status: ${if (joined) "Beigetreten" else "Nicht verbunden"}")
    }
}
