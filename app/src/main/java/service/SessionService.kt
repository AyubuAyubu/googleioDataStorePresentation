package service

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Binds
import dagger.Module
import data.SessionDataStoreManager
import data.Sessions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

interface SessionService {
    fun getSessionsFromPrefDataStore(): Flow<Sessions>
    suspend fun addSessions(sessions: Sessions)
}

