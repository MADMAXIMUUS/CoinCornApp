package ru.coincorn.app.featureAuth.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.coincorn.app.core.error.ErrorHandler
import ru.coincorn.app.featureAuth.data.datasource.AuthRemoteDataSource
import ru.coincorn.app.featureAuth.data.request.SignInRequestModel
import ru.coincorn.app.featureAuth.data.request.SignUpRequestModel
import ru.coincorn.app.featureAuth.data.response.AuthStep
import ru.coincorn.app.featureAuth.domain.repository.AuthRepository
import ru.coincorn.app.featureAuth.domain.repository.CredentialsRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteDataSource,
    private val credentialsRepository: CredentialsRepository,
    private val errorHandler: ErrorHandler
) : AuthRepository {

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ) {
        val signUpModel = SignUpRequestModel(name, email, password)
        authRemoteSource
            .signUp(signUpModel)
            .catch { e ->
                errorHandler.proceed(e)
            }
            .flowOn(Dispatchers.IO)
            .collect { sessionId ->
                credentialsRepository.saveSessionId(sessionId)
            }
    }

    override suspend fun signIn(email: String, password: String) {
        val signInModel = SignInRequestModel(email, password)
        authRemoteSource
            .signIn(signInModel)
            .catch { e ->
                errorHandler.proceed(e)
            }
            .flowOn(Dispatchers.IO)
            .collect { sessionId ->
                credentialsRepository.saveSessionId(sessionId)
            }
    }

    override suspend fun restorePassword(email: String) {
        /*return try {
            val response = authRemoteSource.restorePassword(email)
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.PlainText(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.unknown_error))
            }
            Resource.Success<Unit>()
        } catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(R.string.server_connection_error),
                500
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(R.string.something_went_wrong),
                600
            )
        }*/
    }

    override suspend fun checkCode(code: String) {
        /*return try {
            val response = authRemoteSource.checkCode(code)
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.PlainText(msg))
                } ?:Resource.Error(UiText.StringResource(R.string.unknown_error))
            }
            Resource.Success<Unit>()
        } catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(R.string.server_connection_error),
                500
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(R.string.something_went_wrong),
                600
            )
        }*/
    }

    override suspend fun fetchAuthStep(): Flow<AuthStep> = authRemoteSource
        .fetchStep()
        .catch { e ->
            errorHandler.proceed(e)
        }
        .flowOn(Dispatchers.IO)


    override suspend fun signOut() {
        try {
            credentialsRepository.deleteSessionId()
            //emit(true)
        } catch (e: Exception) {
            errorHandler.proceed(e)
        }


    }
}