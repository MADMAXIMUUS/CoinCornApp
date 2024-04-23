package ru.coincorn.app.featureProfile.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.coincorn.app.core.network.util.NetworkResponse
import ru.coincorn.app.featureProfile.data.request.UpdateNameRequestModel
import ru.coincorn.app.featureProfile.data.request.UpdateTokenRequestModel

interface UserApi {

    @POST("/api/v1/user/update-name")
    suspend fun updateName(
        @Body updateNameRequestModel: UpdateNameRequestModel
    ): NetworkResponse<Unit>

    @POST("/api/v1/user/update-token")
    suspend fun updateToken(
        @Body body: UpdateTokenRequestModel
    ): NetworkResponse<Unit>
}