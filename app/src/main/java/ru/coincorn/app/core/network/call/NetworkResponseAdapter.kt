package ru.coincorn.app.core.network.call

import retrofit2.Call
import retrofit2.CallAdapter
import ru.coincorn.app.core.network.util.NetworkResponse
import java.lang.reflect.Type

class NetworkResponseAdapter<S : Any>(
    private val successType: Type
) : CallAdapter<S, Call<NetworkResponse<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S>> {
        return NetworkResponseCall(call)
    }
}