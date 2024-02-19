package com.rasel.counselling.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rasel.counselling.objects.LocalSetting

@Composable
fun CustomDropdownMenu(onValueChange: (Int) -> Unit) {
    val context = LocalContext.current
    val timeList = listOf("1", "2", "3", "4", "5", "6", "8", "10")
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(timeList.first()) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$selectedOption Minutes",
            color = LocalSetting.textColor()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(5.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            timeList.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedOption = item
                    onValueChange(selectedOption.toInt())
                    expanded = false
                },
                    text = { Text(text = item) })
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { expanded = true }) {
            Text(
                text = "Select Time",
                color = LocalSetting.textColor()
            )
        }
    }
}
