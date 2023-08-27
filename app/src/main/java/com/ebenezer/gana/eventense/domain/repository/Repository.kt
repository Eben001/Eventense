package com.ebenezer.gana.eventense.domain.repository

import com.ebenezer.gana.eventense.domain.model.ApiRequest
import com.ebenezer.gana.eventense.domain.model.ApiResponse
import com.ebenezer.gana.eventense.domain.model.UserUpdate
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveSignedInState(signedIn:Boolean)
    fun readSignedInState():Flow<Boolean>
    suspend fun verifyTokenOnBackend(request: ApiRequest): ApiResponse
    suspend fun getUserInfo():ApiResponse
    suspend fun updateUser(userUpdate: UserUpdate):ApiResponse
    suspend fun deleteUser():ApiResponse
    suspend fun getEvents():ApiResponse
    suspend fun clearSession():ApiResponse


}