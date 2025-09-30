package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    val primaryColor = Color(0xFF6200EE) // Фиолетовый

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF9D93A9) // Светло-серый
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Заголовок
            Text(
                text = "Вход в систему",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color(0xFF812000),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Поле для email
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = "" // Сбрасываем ошибку при изменении текста
                },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = primaryColor,
//                    focusedLabelColor = primaryColor,
//                    cursorColor = primaryColor
//                )
            )

            // Поле для пароля
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = "" // Сбрасываем ошибку при изменении текста
                },
                label = { Text("Пароль") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )

            // Кнопка входа
            Button(
                onClick = {
                    if (isValidCredentials(email, password)) {
                        // Переход на SecondActivity
                        val intent = Intent(context, SecondActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        errorMessage = "Неверный email или пароль"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Войти", style = MaterialTheme.typography.bodyLarge)
            }

            // Сообщение об ошибке
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

private fun isValidCredentials(email: String, password: String): Boolean {
    val validEmail = "admin@example.com"
    val validPassword = "123456"
    return email == validEmail && password == validPassword
}

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}