package com.bazuma.sphdatastore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bazuma.sphdatastore.ui.theme.SphDataStoreTheme
import dagger.hilt.android.AndroidEntryPoint
import data.Sessions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import model.SessionViewModel
import service.SessionService

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SphDataStoreTheme {
                SessionEntryScreen(viewModel)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionEntryScreen(viewModel: SessionViewModel) {
    var firstSession by remember { mutableStateOf("") }
    var secondSession by remember { mutableStateOf("") }
    var thirdSession by remember { mutableStateOf("") }
    val savedSessions by viewModel.savedSessions.collectAsState(initial = listOf())

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Google I/O Extended Pwani") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TaskInputFields(
                firstRoom = firstSession,
                onFirstTaskChange = { firstSession = it },
                secondRoom = secondSession,
                onSecondTaskChange = { secondSession = it },
                thirdRoom = thirdSession,
                onThirdTaskChange = { thirdSession = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SaveButton(
                onSaveClick = {
                    viewModel.saveSessionData(Sessions(firstSession, secondSession, thirdSession))
                    firstSession = ""
                    secondSession = ""
                    thirdSession = ""
                }
            )

            savedSessions.forEach { session ->
                Text(session)
            }
        }
    }
}

@Composable
fun TaskInputFields(
    firstRoom: String,
    onFirstTaskChange: (String) -> Unit,
    secondRoom: String,
    onSecondTaskChange: (String) -> Unit,
    thirdRoom: String,
    onThirdTaskChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = firstRoom,
            onValueChange = onFirstTaskChange,
            label = { Text("Alpha Sessions Room") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = secondRoom,
            onValueChange = onSecondTaskChange,
            label = { Text("Charlie Sessions Room") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = thirdRoom,
            onValueChange = onThirdTaskChange,
            label = { Text("Bravo Session Room") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SaveButton(onSaveClick: () -> Unit) {
    Button(
        onClick = onSaveClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text("Save Sessions")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSessionEntryScreen() {
    SphDataStoreTheme {
        val viewModel = SessionViewModel(object : SessionService {
            override fun getSessionsFromPrefDataStore(): Flow<Sessions> = flowOf(Sessions("Alpha", "Charlie", "Bravo"))
            override suspend fun addSessions(sessions: Sessions) {}
        })
        SessionEntryScreen(viewModel)
    }
}

