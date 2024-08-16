package com.example.realidad_aumentadad

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.realidad_aumentadad.ui.theme.Realidad_aumentadadTheme

class CameraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraView()
        }
    }
}

@Composable
fun CameraView() {
    val context = LocalContext.current

    // This will trigger the camera intent
    val takePictureIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)

    if (takePictureIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(takePictureIntent)
    }
}

@Preview(showBackground = true)
@Composable
fun CameraViewPreview() {
    Realidad_aumentadadTheme {
        CameraView()
    }
}
