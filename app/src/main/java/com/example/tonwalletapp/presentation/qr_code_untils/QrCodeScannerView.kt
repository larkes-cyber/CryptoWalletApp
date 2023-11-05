package com.example.tonwalletapp.presentation.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.tonwalletapp.until.Constants.SCAN_QR_CODE_TITLE
import com.example.tonwalletapp.presentation.qr_code_untils.QrCodeAnalyzer

@SuppressLint("RestrictedApi")
@Composable
fun QrCodeScannerView(
    onBack:() -> Unit,
    onScanDone:(String) -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)


    var hasCamPermission by rememberSaveable {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }


    val stroke = Stroke(width = 5f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(70f, 20f), 0f)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasCamPermission) {
            AndroidView(
                factory = { context ->
                    val previewView = PreviewView(context)
                    val preview = Preview.Builder().build()
                    val selector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setTargetResolution(
                            Size(
                                previewView.width,
                                previewView.height
                            )
                        )
                        .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer { result ->
                            cameraProviderFuture.get().shutdown()
                            onScanDone(result)
                        }
                    )
                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        val color =  Color.White
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Black.copy(alpha = 0.6f))){
                Text(
                    text = SCAN_QR_CODE_TITLE,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 28.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f)) {
                Box(modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(Color.Black.copy(alpha = 0.6f)))
                Box(
                    Modifier
                        .weight(3f)
                        .fillMaxHeight()
                        .padding(end = 2.dp, bottom = 2.dp)
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .drawBehind {
                                drawRoundRect(color = color, style = stroke)
                            }
                    )
                }
                Box(modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(Color.Black.copy(alpha = 0.6f)))
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Black.copy(alpha = 0.6f)))
        }

        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            IconButton(onClick = {
                cameraProviderFuture.get().shutdown()
                onBack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )
            }
        }

    }
}