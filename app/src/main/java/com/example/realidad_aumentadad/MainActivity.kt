package com.example.realidad_aumentadad

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Realidad_aumentadadTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFE0F7FA)) // Pastel background color
                ) { innerPadding ->
                    MainView(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        val input_stream = context.assets.open("bomba-playstore.png")
        val image_bitmap = BitmapFactory.decodeStream(input_stream)

        // Title at the top
        Text(
            text = "Aplicación Realidad Aumentada",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 48.dp)
        )

        // Image in the middle
        Image(
            bitmap = image_bitmap.asImageBitmap(), // Replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.8f) // Adjust this to control the horizontal tolerance
                .aspectRatio(1f) // Maintain the aspect ratio of the image
        )

        // Button at the bottom
        Button(
            onClick = { /* Handle button click */ },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp) // Adjusted padding to fit the small text below
        ) {
            Text(text = "Iniciar")
        }

        // Small text below the button
        Text(
            text = "desarrollado por el semillero sima",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 2.dp, top = 1.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    Realidad_aumentadadTheme {
        MainView()
    }
}
