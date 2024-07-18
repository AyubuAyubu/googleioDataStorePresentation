package model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.Sessions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import service.SessionService
import javax.inject.Inject



@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionService: SessionService
) : ViewModel() {

    private val _savedSessions = MutableStateFlow<List<String>>(emptyList())
    val savedSessions: StateFlow<List<String>> = _savedSessions

    fun saveSessionData(sessions: Sessions) {
        viewModelScope.launch {
            sessionService.addSessions(sessions)
            _savedSessions.value = listOf(sessions.alphaSession, sessions.charlieSession, sessions.bravoSession)
        }
    }
}
