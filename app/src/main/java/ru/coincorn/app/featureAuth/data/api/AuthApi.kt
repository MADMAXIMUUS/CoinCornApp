package ru.coincorn.app.featureAuth.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.coincorn.app.featureAuth.data.request.SignInRequestModel
import ru.coincorn.app.featureAuth.data.request.SignUpRequestModel
import ru.coincorn.app.featureAuth.data.response.AuthStepResponse
import ru.coincorn.app.core.network.util.NetworkResponse

interface AuthApi {

    @POST("api/v1/auth/sign-up")
    suspend fun signUp(
        @Body signUpModel: SignUpRequestModel
    ): NetworkResponse<Unit>

    @POST("api/v1/auth/sign-in")
    suspend fun signIn(
        @Body signInModel: SignInRequestModel
    ): NetworkResponse<Unit>

    @GET("api/v1/auth/step")
    suspend fun fetchStep(): NetworkResponse<AuthStepResponse>

    @POST("api/v1/auth/reset-password")
    suspend fun restorePassword(email: String): NetworkResponse<Unit>

    @POST("api/v1/auth/code")
    suspend fun checkCode(code: String): NetworkResponse<Unit>

}