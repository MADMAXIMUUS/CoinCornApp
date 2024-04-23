package ru.coincorn.app.featureProfile.data.dataSource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.coincorn.app.core.network.util.onError
import ru.coincorn.app.core.network.util.onSuccess
import ru.coincorn.app.featureProfile.data.api.UserApi
import ru.coincorn.app.featureProfile.data.request.UpdateNameRequestModel
import ru.coincorn.app.featureProfile.data.request.UpdateTokenRequestModel
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val api: UserApi
) {

    fun updateNane(updateNameRequestModel: UpdateNameRequestModel): Flow<Unit> = flow {
        api.updateName(updateNameRequestModel)
            .onSuccess { _, _ ->
                emit(Unit)
            }
            .onError { throw it }
    }

    fun updateToken(updateTokenRequestModel: UpdateTokenRequestModel): Flow<Unit> = flow {
        api.updateToken(updateTokenRequestModel)
            .onSuccess { _, _ ->
                emit(Unit)
            }
            .onError { throw it }
    }

}