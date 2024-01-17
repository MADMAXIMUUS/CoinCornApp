package ru.coincorn.app.core.error

import android.util.Log
import retrofit2.HttpException
import ru.coincorn.app.core.error.model.CommonErrorModel
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

    private fun handleHttpException(
        exception: HttpException,
        httpErrorHandler: (CommonErrorModel) -> Unit
    ) {
        val errorModel = mapper.mapHttpExceptionToCommonError(exception)
        when {
            exception.isAuthError() -> errorResolver.resolveAuthError()
            else -> httpErrorHandler(errorModel)
        }
    }

    private fun handleCommonErrors(
        exception: Throwable,
        messageListener: (String) -> Unit = { _ -> }
    ) {
        when (exception) {
            is UnknownHostException,
            is ConnectException -> errorResolver.resolveConnectionError()
        }
    }

    companion object {
        fun logging(exception: Throwable) {
            Log.e("Error", exception.toString())
        }
    }
}

private fun HttpException.isAuthError(): Boolean =
    code() == 401 || code() == 403 || code() == 406 || code() == 407