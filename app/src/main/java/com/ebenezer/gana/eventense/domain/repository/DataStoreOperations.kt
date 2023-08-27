package com.ebenezer.gana.eventense.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveSignedInState(signedIn:Boolean)
    fun readSignedInState():Flow<Boolean>


    suspend fun saveLoggedInState(signedIn:Boolean)
    fun readLoggedInState():Flow<Boolean>
}