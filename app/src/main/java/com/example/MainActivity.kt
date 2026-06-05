package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.data.AppDatabase
import com.example.data.PrestigeJetRepository
import com.example.ui.PrestigeJetApp
import com.example.ui.PrestigeJetViewModel
import com.example.ui.ViewModelFactory
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  private val database by lazy { AppDatabase.getDatabase(this) }
  private val repository by lazy { PrestigeJetRepository(database.prestigeJetDao()) }
  private val viewModel: PrestigeJetViewModel by viewModels {
    ViewModelFactory(repository)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        PrestigeJetApp(viewModel)
      }
    }
  }
}

