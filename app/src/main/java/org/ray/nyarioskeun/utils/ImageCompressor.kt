package org.ray.nyarioskeun.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ImageCompressor {
    fun addTempFile(requireContext: Context, bitmap: Bitmap): File {
        val file = File(
            requireContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            System.currentTimeMillis().toString() + "_image.png"
        )
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
        val bitmapdata: ByteArray = bos.toByteArray()

        // Write the bytes in file
        try {
            val outputStream = FileOutputStream(file)

            outputStream.apply {
                write(bitmapdata)
                flush()
                close()
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return file
    }
}