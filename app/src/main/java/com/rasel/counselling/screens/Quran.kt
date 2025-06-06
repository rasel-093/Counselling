package com.rasel.counselling.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rasel.counselling.R
import com.rasel.counselling.components.ParagraphCard
import com.rasel.counselling.navigation.Screen
import com.rasel.counselling.navigation.navigateTo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Quran(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Quran") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF01678b)
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
        }
    ) { innerPadding ->
        val ayat: Array<String> = stringArrayResource(id = R.array.quran_ayat)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
//            Text(text = "Hadith Screen")
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {
                for (i in ayat){
                    ParagraphCard(paragraph = i)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}