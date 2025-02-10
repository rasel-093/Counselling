package com.rasel.counselling.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
      //  modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(2.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            timeList.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedOption = item
                    onValueChange(selectedOption.toInt())
                    expanded = false
                },
                    text = {
                        Text(
                            text = "$item minutes"
                        )
                    })
                Divider(modifier = Modifier.height(1.dp))
            }
        }
        TextButton(
            onClick = { expanded = true},
            colors = ButtonDefaults.buttonColors(
                containerColor = LocalSetting.primaryColor(),
                //disabledContainerColor = Color(0xFF19526D)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ){
            Text(
                text = "$selectedOption Minutes",
                color = LocalSetting.textColor()
            )
        }
    }
}
