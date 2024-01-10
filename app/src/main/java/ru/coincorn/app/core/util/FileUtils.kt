package ru.coincorn.app.core.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.os.Environment
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat

object FileUtils {

    @SuppressLint("SimpleDateFormat")
    fun createTempImageFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss")
            .format(Calendar.getInstance().timeInMillis)
        val directory = File(context.cacheDir, "images")
        directory.mkdirs()
        return File.createTempFile(
            "COINCORN_${timeStamp}",
            ".png",
            directory
        )
    }

    @SuppressLint("SimpleDateFormat")
    fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss")
            .format(Calendar.getInstance().timeInMillis)
        val directory = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "/CoinCorn/"
        )
        if (!directory.exists())
            directory.mkdirs()

        val file = File(directory, "RABBIT_${timeStamp}.png")
        file.createNewFile()
        return file
    }

    fun bitmapToFile(bitmap: Bitmap): File? {
        var file: File? = null
        return try {
            file = createImageFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val bitmapData = bos.toByteArray()
            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("BitmapToFile", it) }
            file
        }
    }
}