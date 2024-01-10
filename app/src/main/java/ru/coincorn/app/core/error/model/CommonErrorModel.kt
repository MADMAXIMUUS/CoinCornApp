package ru.coincorn.app.core.error.model

import android.net.Uri
import com.google.gson.Gson
import ru.coincorn.app.core.navigation.JsonNavType

data class CommonErrorModel(
    val message: String? = null,
    val errorCode: Int? = null,
    val statusCode: Int? = null
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class CommonErrorArgType : JsonNavType<CommonErrorModel>() {
    override fun fromJsonParse(value: String): CommonErrorModel {
        return Gson().fromJson(value, CommonErrorModel::class.java)
    }


    override fun CommonErrorModel.getJsonParse(): String {
        val a = this
        return Gson().toJson(a)
    }
}