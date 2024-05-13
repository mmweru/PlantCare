package com.example.plantcareai.AiCamera

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.example.plantcareai.R

@Composable
fun CustomCameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    // Define your custom camera shape here
    val cameraShape = CutCornerShape(16.dp) // Example: Cut corner shape

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = rememberImagePainter(R.drawable.plant),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        val lifecycleOwner = LocalLifecycleOwner.current
        AndroidView(
            factory = {
                PreviewView(it).apply {
                    this.controller = controller
                    controller.bindToLifecycle(lifecycleOwner)
                }
            },
            modifier = modifier.clip(cameraShape) // Apply the custom shape
        )
    }
}

