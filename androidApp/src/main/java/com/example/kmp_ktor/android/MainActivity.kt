package com.example.kmp_ktor.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmp_ktor.Service
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    var text by remember { mutableStateOf("Loading") }

                    LaunchedEffect(true) {
                        scope.launch {
                            text = try  {
                                Service().getDoc()
                            } catch (e: Exception) {
                                e.localizedMessage ?: "error"
                            }
                        }
                    }

                    GreetingView(
                        text
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
