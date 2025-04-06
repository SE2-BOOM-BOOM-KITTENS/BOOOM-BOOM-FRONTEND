package com.example.boomboomfrontend.activities

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boomboomfrontend.ui.theme.BoomBoomFrontendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoomBoomFrontendTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF924C49)
                ) {
                }
            }
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}

@Composable
fun Arrangement() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card()
            Deck()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        PlayerHands()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        OpponentHands()
    }
}

@Composable
fun Card(){
    Box(
        modifier = Modifier
            .size(110.dp, 150.dp)
            .background(Color(0xffb2766b))
    ){
        Text(
            text = "BLANK\nCARD",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun Deck(){
    Box(
        modifier = Modifier
            .size(110.dp, 150.dp)
            .background(Color(0xff1c0e0b))
    ){
        Text(
            text = "DECK",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PlayerHands() {
    Box(
        modifier = Modifier
            .size(250.dp, 90.dp)
            .background(Color(0xffb2766b))
    ){
        Text(
            text = "PLAYER\nHAND",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun OpponentHands() {
    Box(
        modifier = Modifier
            .size(250.dp, 90.dp)
            .background(Color(0xff1c0e0b))
    ){
        Text(
            text = "OPPONENT\nHAND",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true, device= "spec:orientation=landscape,width=411dp,height=891dp")
@Composable
fun GreetingPreview() {
    BoomBoomFrontendTheme {
        Arrangement()
    }
}