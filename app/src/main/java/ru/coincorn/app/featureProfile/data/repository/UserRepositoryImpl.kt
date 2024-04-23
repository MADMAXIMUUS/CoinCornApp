package ru.coincorn.app.featureProfile.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import ru.coincorn.app.core.error.ErrorHandler
import ru.coincorn.app.featureProfile.data.dataSource.UserRemoteDataSource
import ru.coincorn.app.featureProfile.data.request.UpdateNameRequestModel
import ru.coincorn.app.featureProfile.data.request.UpdateTokenRequestModel
import ru.coincorn.app.featureProfile.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val errorHandler: ErrorHandler
) : UserRepository {

    override fun updateName(name: String): Flow<Unit> {
        val updateNameRequestModel = UpdateNameRequestModel(name)
        return userRemoteDataSource
            .updateNane(updateNameRequestModel)
            .catch { errorHandler.proceed(it) }
            .flowOn(Dispatchers.IO)
    }

    override fun updateToken(token: String): Flow<Unit> {
        val updateTokenRequestModel = UpdateTokenRequestModel(token)
        return userRemoteDataSource
            .updateToken(updateTokenRequestModel)
            .catch { errorHandler.proceed(it) }
            .flowOn(Dispatchers.IO)
    }
}