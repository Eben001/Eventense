package com.ebenezer.gana.eventense.data.remote

import com.ebenezer.gana.eventense.domain.model.ApiRequest
import com.ebenezer.gana.eventense.domain.model.ApiResponse
import com.ebenezer.gana.eventense.domain.model.UserUpdate
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface KtorApi {

    @POST("/token_verification")
    suspend fun verifyTokenOnBackend(
        @Body request: ApiRequest
    ): ApiResponse

    @GET("/get_user")
    suspend fun getUserInfo(): ApiResponse

    @PUT("/update_user")
    suspend fun updateUser(@Body userUpdate: UserUpdate): ApiResponse

    @DELETE("/delete_user")
    suspend fun deleteUser(): ApiResponse

    @GET("/events")
    suspend fun getEvents():ApiResponse

    @GET("/sign_out")
    suspend fun clearSession(): ApiResponse


}