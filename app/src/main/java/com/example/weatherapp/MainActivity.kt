package com.example.weatherapp

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.combineTransform
import java.util.Objects

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userInput = findViewById<TextInputEditText>(R.id.userInput)
        val searchBtn = findViewById<Button>(R.id.searchBtn)
        var container = findViewById<CardView>(R.id.container)
        var background = findViewById<LinearLayout>(R.id.background)





        searchBtn.setOnClickListener {
            var userValue = userInput.text.toString()
            when(userValue){
                "summer" -> background.setBackgroundResource(R.drawable.lato)
                "winter" -> background.setBackgroundResource(R.drawable.zima)
                "spring" -> background.setBackgroundResource(R.drawable.wiosna)
                "autumn" -> background.setBackgroundResource(R.drawable.jesien)
                else -> background.setBackgroundResource(R.drawable.day)
            }
            userInput.text = null

            container.visibility = View.VISIBLE

        }
    }
}