package ru.coincorn.app.core.error

import retrofit2.HttpException
import ru.coincorn.app.core.error.model.CommonErrorModel
import ru.coincorn.app.core.network.util.ErrorResponse
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val mapper: ErrorMapper,
    private val errorResolver: ErrorResolver
) {

    fun proceed(
        exception: Throwable,
    ) {
        logging(exception)
        when (exception) {
            is HttpException -> handleHttpException(
                exception,
                errorResolver::resolveCommonHttpError
            )

            else -> handleCommonErrors(exception)
        }
    }

    fun proceed(
        body: ErrorResponse
    ) {
        val errorModel = mapper.mapErrorResponseToErrorModel(body)
    }

    private fun handleHttpException(
        exception: HttpException,
        httpErrorHandler: (CommonErrorModel) -> Unit
    ) {
        val errorModel = mapper.mapHttpExceptionToCommonError(exception)
        when {
            else -> httpErrorHandler(errorModel)
        }
    }

    private fun handleCommonErrors(
        exception: Throwable,
        messageListener: (String) -> Unit = { _ -> }
    ) {
        when (exception) {
            is UnknownHostException,
            is ConnectException -> messageListener(
                ""//rm.getString(R.string.error_msg_connect_to_server)
            )
            //is ApiErrorException ->
        }
    }

    companion object {
        fun logging(exception: Throwable) {

        }
    }
}