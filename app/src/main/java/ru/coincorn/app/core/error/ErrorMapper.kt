package ru.coincorn.app.core.error

import com.google.gson.Gson
import retrofit2.HttpException
import ru.coincorn.app.core.error.model.CommonErrorModel
import ru.coincorn.app.core.error.model.HttpErrorModel
import ru.coincorn.app.core.network.util.ErrorResponse
import javax.inject.Inject

class ErrorMapper @Inject constructor(
    private val gson: Gson
) {

    fun mapHttpExceptionToCommonError(
        exception: HttpException
    ): CommonErrorModel {
        val body = exception.response()?.errorBody()
        val errorResponse = parseErrorResponse(body?.string())

        val message: String = errorResponse?.localizedMessage ?: exception.message()
        val errorCode: Int? = errorResponse?.code
        val statusCode: Int = errorResponse?.status ?: exception.code()
        return CommonErrorModel(message, errorCode, statusCode)
    }

    fun mapErrorResponseToErrorModel(
        errorResponse: ErrorResponse
    ): HttpErrorModel = HttpErrorModel(
        title = errorResponse.title,
        message = errorResponse.localizedMessage,
        errorCode = errorResponse.code,
        statusCode = errorResponse.status
    )

    private fun parseErrorResponse(jsonString: String?): ErrorResponse? = try {
        jsonString?.let { js -> gson.fromJson(js, ErrorResponse::class.java) }
    } catch (e: Throwable) {
        ErrorHandler.logging(e)
        null
    }
}