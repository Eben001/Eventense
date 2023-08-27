package com.ebenezer.gana.eventense.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.ebenezer.gana.eventense.data.remote.KtorApi
import com.ebenezer.gana.eventense.data.repository.DataStoreOperationsImpl
import com.ebenezer.gana.eventense.data.repository.RepositoryImpl
import com.ebenezer.gana.eventense.domain.repository.DataStoreOperations
import com.ebenezer.gana.eventense.domain.repository.Repository
import com.ebenezer.gana.eventense.util.Constants.PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStorePreference(@ApplicationContext context: Context)
            : DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_NAME) }
        )
    }


    @Provides
    @Singleton
    fun provideDataStoreOperations(
        dataStore: DataStore<Preferences>
    ): DataStoreOperations {
        return DataStoreOperationsImpl(datastore = dataStore)
    }


    @Provides
    @Singleton
    fun provideRepository(dataStoreOperations: DataStoreOperations, ktorApi: KtorApi): Repository {
        return RepositoryImpl(
            dataStoreOperations = dataStoreOperations,
            ktorApi = ktorApi
        )
    }
}