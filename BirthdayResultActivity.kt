package com.example.brithdaycard

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class BirthdayResultActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get values from Intent
        val name = intent.getStringExtra("NAME") ?: "Unknown"
        val dob = intent.getStringExtra("DOB") ?: "Not provided"
        enableEdgeToEdge()
        setContent {
            BirthdayCardResultApp(name, dob)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BirthdayCardResultApp(name: String, dob: String) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dob = LocalDate.parse(dob, formatter)
    val today = LocalDate.now()
    val age = Period.between(dob, today).years
    val birthstone = getBirthstone(dob.monthValue)
    val chineseZodiac = getChineseZodiac(dob.year)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)) // Light Yellow Background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🎉 Birthday Card 🎂",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFE91E63) // Pink Color
                )
                Spacer(Modifier.height(16.dp))

                Text("Name: $name", style = MaterialTheme.typography.bodyLarge)
                Text("Date of Birth: $dob", style = MaterialTheme.typography.bodyLarge)
                Text("Age: $age years", style = MaterialTheme.typography.bodyLarge)

                Spacer(Modifier.height(16.dp))

                Text(
                    "🎁 Birthstone: $birthstone",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF2196F3) // Blue
                )
                Text(
                    "🐉 Chinese Zodiac: $chineseZodiac",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFFF9800) // Orange
                )
            }
        }
    }
}
private fun getBirthstone(month: Int): String {
    return when (month) {
        1 -> "Garnet"
        2 -> "Amethyst"
        3 -> "Aquamarine"
        4 -> "Diamond"
        5 -> "Emerald"
        6 -> "Pearl"
        7 -> "Ruby"
        8 -> "Peridot"
        9 -> "Sapphire"
        10 -> "Opal"
        11 -> "Topaz"
        12 -> "Turquoise"
        else -> "Unknown"
    }
}
private fun getChineseZodiac(year: Int): String {
    val zodiacs = arrayOf(
        "Monkey", "Rooster", "Dog", "Pig", "Rat", "Ox",
        "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Goat"
    )
    return zodiacs[year % 12]
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun BirthdayCardResultAppPreview() {
    BirthdayCardResultApp("John Doe", "1990-01-01")
}