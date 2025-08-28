package com.aero.notesapp.presentation.components

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import java.io.IOException

@Composable
fun AssetImageView(imagePath: String, modifier:Modifier = Modifier) {
    val context = LocalContext.current

    val imageBitmap: ImageBitmap? =  try {
        val inputStream = context.assets.open(imagePath)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        bitmap?.asImageBitmap()
    } catch (e: IOException) {
        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        null
    }

    imageBitmap?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.FillBounds,
        )
    } ?: Text("Image not found")
}