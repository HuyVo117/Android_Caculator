package com.example.lab_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab_3.ui.theme.Lab_3Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_3Theme {
                CalculatorUI()
            }
        }
    }
}

@Composable
fun CalculatorUI() {
    var inputExpression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val buttons = listOf(
        listOf("7", "8", "9", "/"),
        listOf("4", "5", "6", "*"),
        listOf("1", "2", "3", "-"),
        listOf("0", ".", "=", "+"),
        listOf("C", "DEL")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Máy tính", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = inputExpression,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        )

        Text(
            text = result,
            fontSize = 24.sp,
            color = Color.Red,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    Button(
                        onClick = {
                            when (label) {
                                "C" -> {
                                    inputExpression = ""
                                    result = ""
                                }
                                "DEL" -> {
                                    if (inputExpression.isNotEmpty()) {
                                        inputExpression = inputExpression.dropLast(1)
                                    }
                                }
                                "=" -> {
                                    result = evaluateExpression(inputExpression)
                                }
                                else -> {
                                    inputExpression += label
                                }
                            }
                        },
                        modifier = Modifier
                            .size(70.dp)
                    ) {
                        Text(text = label, fontSize = 20.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

fun evaluateExpression(expression: String): String {
    return try {
        val result = expression.replace("÷", "/").replace("×", "*")
        val value = result.toDoubleOrNull()
        value?.toString() ?: "Lỗi"
    } catch (e: Exception) {
        "Lỗi"
    }
}
