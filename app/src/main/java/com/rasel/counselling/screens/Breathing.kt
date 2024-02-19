package com.rasel.counselling.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rasel.counselling.components.CustomDropdownMenu
import com.rasel.counselling.navigation.Screen
import com.rasel.counselling.navigation.navigateTo
import com.rasel.counselling.objects.LocalSetting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Breathing(navHostController: NavHostController) {
    var status by rememberSaveable {
        mutableStateOf(false)
    }
    var timeLimit by rememberSaveable {
        mutableIntStateOf(1)
    }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Breathing", color = LocalSetting.textColor()) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3)
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        //Handle back button option
                        navHostController.navigateTo(Screen.HomeScreen.route)
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = LocalSetting.containerColor()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            CircleAnimation(
                maxRadius = 400f,
                minRadius = 150f,
                status = status,
                onStatusChange = {
                    status = false
                },
                timeLimit = timeLimit
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomDropdownMenu { timeLimit = it }
            Button(onClick = { status = !status }) {
                Text(
                    text = if (status) "Stop" else "Start Now",
                    color =  LocalSetting.textColor()
                )
            }
        }
    }
}

@Composable
fun CircleAnimation(
    maxRadius: Float,
    minRadius: Float,
    animationDuration: Int = 4000, // In milliseconds
    status: Boolean,
    onStatusChange: () -> Unit,
    timeLimit: Int
) {
    var animatedRadius by remember { mutableFloatStateOf(minRadius) }
    var textInsideCircle by remember { mutableStateOf("Be Ready") }
    val context = LocalContext.current
    val duration = timeLimit * 60 * 1000


    if (status) {
        val startTime = System.currentTimeMillis()
        LaunchedEffect(Unit) {


            // Increase radius for 4 seconds
            do {
                val elapsedTime = System.currentTimeMillis() - startTime
                if (elapsedTime >= duration) {
                    onStatusChange()
                }

                with(Dispatchers.Main) {
                    animate(
                        initialValue = minRadius,
                        targetValue = maxRadius,
                        animationSpec = tween(
                            durationMillis = animationDuration,
                            easing = LinearEasing
                        )
                    ) { value, _ ->
                        animatedRadius = value
                        textInsideCircle = "Breath In"
                    }
                }
                // Pause for 4 seconds
                textInsideCircle = "Hold"
                LocalSetting.sound(context)
                LocalSetting.vibration(context)
                delay(2000)
                LocalSetting.sound(context)
                LocalSetting.vibration(context)
                // Decrease radius for 4 seconds
                with(Dispatchers.Main) {
                    animate(
                        initialValue = maxRadius,
                        targetValue = minRadius,
                        animationSpec = tween(
                            durationMillis = animationDuration,
                            easing = LinearEasing
                        )
                    ) { value, _ ->
                        animatedRadius = value
                        textInsideCircle = "Breath Out"
                    }
                }
                textInsideCircle = "Hold"
                LocalSetting.sound(context)
                LocalSetting.vibration(context)
                delay(2000)
                LocalSetting.sound(context)
                LocalSetting.vibration(context)
            } while (elapsedTime <= duration)
        }
    }
    Box(
        modifier = Modifier
            .padding(10.dp)
            .height(400.dp)
            .border(border = BorderStroke(2.dp, Color(0xFF03A9F4))),
        contentAlignment = Alignment.Center
    ) {
        val circleColor = LocalSetting.circleColor()
        Canvas(modifier = Modifier.fillMaxWidth()) {
            drawCircle(
                color = circleColor ,
                radius = animatedRadius
            )
            drawCircle(
                color = Color.Green ,
                radius = 150f
            )
            drawCircle(
                color = Color.Cyan ,
                radius = 400f,
                style = Stroke(5.dp.toPx()),
            )
            drawCircle(
                color = Color.Cyan ,
                radius = 150f,
                style = Stroke(5.dp.toPx()),
            )
        }
        Text(
            text = textInsideCircle,
            color = LocalSetting.textColor()
        )
    }
}
