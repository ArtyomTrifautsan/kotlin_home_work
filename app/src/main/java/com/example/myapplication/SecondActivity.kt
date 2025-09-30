package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Находим элементы по ID
        val textView = findViewById<TextView>(R.id.textView2)
        val button = findViewById<Button>(R.id.button2)
        val backButton = findViewById<Button>(R.id.btnBack)

        // Устанавливаем обработчик нажатия на кнопку
        button.setOnClickListener {
            textView.text = "Кнопка нажата!" // меняем текст
        }

        // Обработчик для кнопки назад
        backButton.setOnClickListener {
            finish() // Закрываем текущую активити и возвращаемся назад
        }
    }
}