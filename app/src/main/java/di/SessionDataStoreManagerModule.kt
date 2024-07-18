package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.SessionDataStoreManager
import data.SessionDataStoreManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionsDataStoreManagerModule {
    @Singleton
    @Binds
    abstract fun bindDataStoreRepository(
        sessionDataStoreManagerImpl: SessionDataStoreManagerImpl
    ): SessionDataStoreManager
}
