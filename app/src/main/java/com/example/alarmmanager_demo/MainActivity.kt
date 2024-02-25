package com.example.alarmmanager_demo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions.Companion.Default
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alarmmanager_demo.ui.theme.AlarmManagerDemoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmManagerDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var alarmItem by remember {
                        mutableStateOf(AlarmItem())
                    }
                    var context = LocalContext.current
                    Column(
                        Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = alarmItem.timeToString(),
                            onValueChange = { newTime ->
                                alarmItem = alarmItem.copy().apply {
                                    stringToTime(newTime)
                                }
                            },
                            label = { Text(text = "Time in minutes") },
                            placeholder = { Text(text = "Enter time in minutes") },
                            keyboardOptions = Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        TextField(
                            value = alarmItem.message,
                            onValueChange = { alarmItem = alarmItem.copy(message = it) },
                            label = { Text(text = "Message") },
                            placeholder = { Text(text = "Enter your message") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { startAlarm(alarmItem, context) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Click to Start Alarm")
                        }
                    }
                }
            }
        }
    }

    private fun startAlarm(alarmItem: AlarmItem, context: Context) {
        val myAlarmManager = MyAlarmManagerImpl(context = context)
        myAlarmManager.scheduleRepeatingAlarm(alarmItem)

    }

}
