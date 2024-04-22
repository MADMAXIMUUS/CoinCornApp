package ru.coincorn.app.featureAuth.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.coincorn.app.core.network.util.NetworkResponse
import ru.coincorn.app.featureAuth.data.request.AuthRequestModel
import ru.coincorn.app.featureAuth.data.request.CodeRequestModel
import ru.coincorn.app.featureAuth.data.response.AuthStepResponse

interface AuthApi {

    @POST("api/v1/auth/sign-up")
    suspend fun signUp(
        @Body signUpModel: AuthRequestModel
    ): NetworkResponse<Unit>

    @POST("api/v1/auth/code")
    suspend fun verify(
        @Body codeModel: CodeRequestModel
    ): NetworkResponse<Unit>

    @POST("api/v1/auth/resend")
    suspend fun resendEmail(): NetworkResponse<Unit>

    @GET("api/v1/auth/step")
    suspend fun fetchStep(): NetworkResponse<AuthStepResponse>

}