package com.rizzard23.kuizu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rizzard23.kuizu.presentation.navigation.RootNavigationGraph
import com.rizzard23.kuizu.ui.theme.KuizuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KuizuTheme {
                RootNavigationGraph()
            }
        }
    }
}
