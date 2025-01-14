package di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SessionDataStoreModule {
    private val Context.tasksPreferenceStore: DataStore<Preferences> by preferencesDataStore(name = "store_sessions")

    @Singleton
    @Provides
    fun provideSessionsPreferenceDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.tasksPreferenceStore
}
