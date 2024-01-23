package ru.coincorn.app.featureVerification.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.coincorn.app.core.network.util.NetworkResponse
import ru.coincorn.app.featureVerification.data.request.VerifyRequestModel

interface VerificationApi {

    @POST("api/v1/email/resend")
    suspend fun resendEmail(): NetworkResponse<Unit>

    @POST("api/v1/email/verify")
    suspend fun verify(
        @Body verifyModel: VerifyRequestModel
    ): NetworkResponse<Unit>

}