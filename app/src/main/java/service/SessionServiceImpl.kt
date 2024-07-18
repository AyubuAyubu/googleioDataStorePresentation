package service


import data.SessionDataStoreManager
import data.Sessions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionServiceImpl @Inject constructor(
    private val sessionDataStoreManager: SessionDataStoreManager
) : SessionService {

    override fun getSessionsFromPrefDataStore(): Flow<Sessions> = sessionDataStoreManager.getSessions()

    override suspend fun addSessions(sessions: Sessions) {
        sessionDataStoreManager.saveSessions(sessions)
    }
}
