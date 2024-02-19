package com.rasel.counselling.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rasel.counselling.screens.ScreenItem

@Composable
fun GridItemView(
    gridItem: ScreenItem,
    navHostController: NavHostController
) {
    ElevatedCard(
        modifier = Modifier
            //.padding(5.dp)
            .clickable {
                Log.d("Screen Clicked", "Screen name ${gridItem.route}")
                navHostController.navigate(gridItem.route)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 20.dp,
            focusedElevation = 20.dp
        )
    ) {
        Image(
            painter = painterResource(id = gridItem.imageIcon),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 5.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = gridItem.title,
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center
        )
    }
}