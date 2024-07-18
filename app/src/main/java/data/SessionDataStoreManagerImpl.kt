package data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionDataStoreManagerImpl @Inject constructor (
    private val tasksPreferenceStore: DataStore<Preferences>
): SessionDataStoreManager {
    private val ALPHA_SESSION = stringPreferencesKey("alpha_session")
    private val CHARLIE_SESSION = stringPreferencesKey("charlie_session")
    private val BRAVO_SESSION = stringPreferencesKey("bravo_session")

    override suspend fun saveSessions(sessions: Sessions) {
        tasksPreferenceStore.edit { taskPreferenceStore ->
            taskPreferenceStore[ALPHA_SESSION] = sessions.alphaSession
            taskPreferenceStore[CHARLIE_SESSION] = sessions.charlieSession
            taskPreferenceStore[BRAVO_SESSION ] = sessions.bravoSession
        }
    }
        override fun getSessions(): Flow<Sessions> = tasksPreferenceStore.data.map { taskPreference ->
            Sessions(
                alphaSession = taskPreference[ALPHA_SESSION] ?: "",
                charlieSession = taskPreference[CHARLIE_SESSION] ?: "",
                bravoSession = taskPreference[BRAVO_SESSION] ?: ""
            )
        }
    }

