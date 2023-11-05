package com.artist.wea.util

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@Suppress("DEPRECATION")
class PhotoSelector {

    companion object{
        val artistProfile = mutableStateOf<Bitmap?>(null)
        val artistBackground = mutableStateOf<Bitmap?>(null)
        val userProfile = mutableStateOf<Bitmap?>(null)
    }

    // 이미지 저장
    fun saveBitmapInStorage(
        header:String = "wea",
        fileName:String,
        bitmap: Bitmap, context: Context) {
        val filename = "${header}_$fileName.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
    }

    // Uri -> Bitmap Parser
    fun getBitmap(contentResolver: ContentResolver, fileUri: Uri?): Bitmap? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, fileUri!!))
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            }
        } catch (e: Exception){
            null
        }
    }

    // 전달받은 Uri soruce로 Bitmap 변수에 담아주는 함수
    fun setImageToVariable(
        context: Context,
        uri: Uri?,
        fileName:String,
        imageSource: MutableState<Bitmap?>
    ){
        // 사진 불러오기 기능
        val photoSelector = PhotoSelector()
        val imageBitmap = photoSelector.getBitmap(contentResolver = context.contentResolver, fileUri = uri )
        imageSource.value = imageBitmap

        // 이미지를 휴대폰 내 저장소 (갤러리)에 저장하는 메서드
//        if(imageBitmap != null){
//            saveBitmapInStorage(
//                header = "img_",
//                bitmap = imageBitmap,
//                fileName = fileName,
//                context = context
//            )
//        }
    }

    // 이미지 불러오기
    val takePhotoFromAlbumIntent =
        Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            putExtra(
                Intent.EXTRA_MIME_TYPES,
                arrayOf("image/jpeg", "image/png", "image/bmp", "image/webp")
            )
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        }

}

