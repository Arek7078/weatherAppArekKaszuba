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
        var container = findViewById<CardView>(R.id.container)
        var background = findViewById<LinearLayout>(R.id.background)
        var cityName = findViewById<TextView>(R.id.cityName)
        var temperature = findViewById<TextView>(R.id.temperature)
        var humidityText = findViewById<TextView>(R.id.humidity)
        var humidityImg = findViewById<ImageView>(R.id.humidityImage)


        container.visibility = View.GONE


        // zamiast API
        data class location(
            val name: String,
            val temp: Double,
            val humidity: Double,
            val season: String,
            val time: String
        )

        val warsaw = location("warsaw", 21.0, 10.0, "lato", "12:00")
        val london = location("london", 16.0, 35.0, "zima", "12:00")
        val paris = location("paris", 18.0, 60.0, "wiosna", "12:00")
        val berlin = location("berlin", 20.0, 90.0, "jesien", "12:00")


        val locations = listOf(warsaw, london, paris, berlin)




        searchBtn.setOnClickListener {
            var userValue = userInput.text.toString()
            userValue = userValue.lowercase()

            for (location in locations) {
                val name = location.name
                val season = location.season
                val humidity = location.humidity
                val temp = location.temp
                val time = location.time

                if (location.name == userValue) {
                    when (season) {
                        "lato" -> background.setBackgroundResource(R.drawable.lato)
                        "zima" -> background.setBackgroundResource(R.drawable.zima)
                        "wiosna" -> background.setBackgroundResource(R.drawable.wiosna)
                        "jesien" -> background.setBackgroundResource(R.drawable.jesien)
                        else -> background.setBackgroundResource(R.drawable.day)
                    }
                    when(humidity){
                        in 0.0..30.0 -> humidityImg.setImageResource(R.drawable.suncon)
                        in 31.0..60.0 -> humidityImg.setImageResource(R.drawable.cloudcon)
                        in 61.0..100.0 -> humidityImg.setImageResource(R.drawable.raincon)
                    }
                    cityName.text = name
                    temperature.text = temp.toString() + "°C"
                    humidityText.text = humidity.toString() + "%"
                    userInput.text = null
                    container.visibility = View.VISIBLE
                }
            }
        }
    }
}
