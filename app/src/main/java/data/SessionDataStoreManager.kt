package data

import kotlinx.coroutines.flow.Flow

interface SessionDataStoreManager {
    suspend fun saveSessions(sessions: Sessions)
    fun getSessions(): Flow<Sessions>
}