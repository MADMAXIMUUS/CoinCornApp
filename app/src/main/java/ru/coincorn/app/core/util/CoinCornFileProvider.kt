package ru.coincorn.app.core.util

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import ru.coincorn.app.R

class CoinCornFileProvider: FileProvider(R.xml.file_paths){

    companion object {
        fun getImageUri(context: Context): Uri {
            val file = FileUtils.createTempImageFile(context)
            val authority = "ru.coincorn.app.util.CoinCornFileProvider"
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }

}