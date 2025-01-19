package com.example.porjetmobile2025.View

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.porjetmobile2025.R

class CalculatortwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val heightInput = findViewById<EditText>(R.id.height_input)
        val weightInput = findViewById<EditText>(R.id.weight_input)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val bmiResult = findViewById<TextView>(R.id.bmi_result)
        val caloriesResult = findViewById<TextView>(R.id.calories_result)

        calculateButton.setOnClickListener {
            val height = heightInput.text.toString().toFloatOrNull()
            val weight = weightInput.text.toString().toFloatOrNull()

            if (height != null && height > 0 && weight != null && weight > 0) {
                val bmi = calculateBMI(height, weight)
                val calories = calculateCalories(weight)

                bmiResult.text = "IMC : %.2f".format(bmi)
                caloriesResult.text = "Calories nécessaires : $calories kcal/jour"
            } else {
                bmiResult.text = "Veuillez entrer des valeurs valides"
                caloriesResult.text = ""
            }
        }
    }

    private fun calculateBMI(height: Float, weight: Float): Float {
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

    private fun calculateCalories(weight: Float): Int {
        return (weight * 24).toInt()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
