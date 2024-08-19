package com.example.realidad_aumentadad

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realidad_aumentadad.ui.theme.Realidad_aumentadadTheme

class MainActivity : ComponentActivity() {

    // Register the camera activity result launcher
    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            // Handle the captured image here
            if (bitmap != null) {
                capturedImageBitmap.value = bitmap
            }
        }

    private val capturedImageBitmap = mutableStateOf<android.graphics.Bitmap?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Realidad_aumentadadTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFE0F7FA)) // Pastel background color
                ) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        onStartCamera = { cameraLauncher.launch(null) },
                        capturedImageBitmap = capturedImageBitmap.value
                    )
                }
            }
        }
    }
}

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    onStartCamera: () -> Unit,
    capturedImageBitmap: android.graphics.Bitmap?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        val inputStream = context.assets.open("bomba-playstore.png")
        val imageBitmap = BitmapFactory.decodeStream(inputStream)

        // Title at the top
        Text(
            text = "Aplicación Realidad Aumentada",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 48.dp)
        )

        // Display captured image or default image
        val displayImageBitmap = capturedImageBitmap ?: imageBitmap

        // Image in the middle
        Image(
            bitmap = displayImageBitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
        )

        // Button at the bottom
        Button(
            onClick = onStartCamera,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Text(text = "Iniciar")
        }

        // Small text directly below the button
        Text(
            text = "desarrollado por el semillero sima",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    Realidad_aumentadadTheme {
        MainView(
            onStartCamera = {},
            capturedImageBitmap = null
        )
    }
}
