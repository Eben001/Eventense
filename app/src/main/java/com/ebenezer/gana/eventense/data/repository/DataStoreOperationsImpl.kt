package com.ebenezer.gana.eventense.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.ebenezer.gana.eventense.domain.repository.DataStoreOperations
import com.ebenezer.gana.eventense.util.Constants.PREFERENCES_SIGNED_IN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreOperationsImpl @Inject constructor(
    private val datastore: DataStore<Preferences>
) : DataStoreOperations {

    private object preferencesKey{
        val signedInKey = booleanPreferencesKey(name =PREFERENCES_SIGNED_IN_KEY)
    }

    private object loggedInPrefKey{
        val loggedInKey = booleanPreferencesKey(name =PREFERENCES_SIGNED_IN_KEY)
    }

    override suspend fun saveSignedInState(signedIn: Boolean) {
        datastore.edit {preferences ->
            preferences[preferencesKey.signedInKey] = signedIn
        }
    }

    override fun readSignedInState(): Flow<Boolean> {
       return datastore.data
           .catch { exception ->
               if(exception is IOException){
                   emit(emptyPreferences())
               }else {
                   throw exception
               }
           }
           .map {preference ->
               val signedInState = preference[preferencesKey.signedInKey]?: false
               signedInState
           }
    }

    override suspend fun saveLoggedInState(signedIn: Boolean) {
        datastore.edit {preferences ->
            preferences[loggedInPrefKey.loggedInKey] = signedIn
        }
    }

    override fun readLoggedInState(): Flow<Boolean> {
        return datastore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else {
                    throw exception
                }
            }
            .map {preference ->
                val signedInState = preference[preferencesKey.signedInKey]?: false
                signedInState
            }
    }
}