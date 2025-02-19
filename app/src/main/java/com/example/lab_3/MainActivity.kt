// File: app/src/main/java/com/example/lab_3/MainActivity.kt
package com.example.lab_3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.lab_3.ui.theme.Lab_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab_3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GetLayout(innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GetLayout(innerPadding: PaddingValues? = PaddingValues(0.dp)) {
    val context = LocalContext.current // Lấy context từ Composable

    var a by remember { mutableStateOf("") }
    var b by remember { mutableStateOf("") }
    var c by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding ?: PaddingValues(0.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tính toán phương trình bậc 2",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        )

        TextField(
            value = a,
            onValueChange = { a = it },
            label = { Text("Nhập a") },
            modifier = Modifier.padding(16.dp)
        )

        TextField(
            value = b,
            onValueChange = { b = it },
            label = { Text("Nhập b") },
            modifier = Modifier.padding(16.dp)
        )

        TextField(
            value = c,
            onValueChange = { c = it },
            label = { Text("Nhập c") },
            modifier = Modifier.padding(16.dp)
        )

        GetTextTitle(result)

        Button(
            onClick = {
                result = try {
                    val aValue = a.toFloat()
                    val bValue = b.toFloat()
                    val cValue = c.toFloat()
                    tinhtoan(aValue, bValue, cValue)
                } catch (e: NumberFormatException) {
                    "Vui lòng nhập số hợp lệ!"
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Tính")
        }

        // Nút chuyển sang SecondActivity
        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Chuyển sang SecondActivity")
        }
    }
}


// Hàm tính toán phương trình bậc 2
fun tinhtoan(a: Float, b: Float, c: Float): String {
    val delta = b * b - 4 * a * c
    return when {
        delta > 0 -> {
            val x1 = (-b + Math.sqrt(delta.toDouble())) / (2 * a)
            val x2 = (-b - Math.sqrt(delta.toDouble())) / (2 * a)
            "x1 = $x1\n x2 = $x2"
        }
        delta == 0.0f -> {
            val x = -b / (2 * a)
            "x = $x"
        }
        else -> "Phương trình vô nghiệm"
    }
}

// Hiển thị kết quả
@Composable
fun GetTextTitle(title: String) {
    Text(
        text = "Kết quả: $title",
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        color = Color.Red,
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
    )
}