package com.example.weatherapp

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
        val background = findViewById<LinearLayout>(R.id.background)
        val cityName = findViewById<TextView>(R.id.cityName)
        val temperature = findViewById<TextView>(R.id.temperature)
        val humidityText = findViewById<TextView>(R.id.humidity)
        val humidityImg = findViewById<ImageView>(R.id.humidityImage)


        //Date and background
        val date = java.util.Date()
        val month = java.text.SimpleDateFormat("MMMM").format(date)

        fun seasonBackground(month: String) {
            when (month) {
                "January", "February", "December" -> background.setBackgroundResource(R.drawable.zima)
                "March", "April", "May" -> background.setBackgroundResource(R.drawable.wiosna)
                "June", "July", "August" -> background.setBackgroundResource(R.drawable.lato)
                "September", "October", "November" -> background.setBackgroundResource(R.drawable.jesien)
            }
        }
        seasonBackground(month)

        // instead of API
        data class location(
            val name: String,
            val temp: Double,
            val humidity: Double,


            )

        val warsaw = location("warsaw", 21.0, 10.0)
        val london = location("london", 16.0, 35.0)
        val paris = location("paris", 18.0, 60.0)
        val berlin = location("berlin", 20.0, 90.0)


        val locations = listOf(warsaw, london, paris, berlin)

// onClick search button
        searchBtn.setOnClickListener {
            var userValue = userInput.text.toString()
            userValue = userValue.lowercase()

            for (location in locations) {
                val name = location.name
                val humidity = location.humidity
                val temp = location.temp

                if (location.name == userValue) {

                    when (humidity) {
                        in 0.0..30.0 -> humidityImg.setImageResource(R.drawable.suncon)
                        in 31.0..60.0 -> humidityImg.setImageResource(R.drawable.cloudcon)
                        in 61.0..100.0 -> humidityImg.setImageResource(R.drawable.raincon)
                    }
                    cityName.text = name
                    temperature.text = temp.toString() + "°C"
                    humidityText.text = humidity.toString() + "%"
                    userInput.text = null

                }
            }
        }
    }
}

