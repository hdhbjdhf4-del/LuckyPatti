package com.aedev.zooroulettepredictor

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var resultText: TextView
    private lateinit var historyText: TextView
    private val prefs by lazy { getSharedPreferences("zoo_predictor", MODE_PRIVATE) }
    private val outcomes = listOf("🦁 शेर (Lion)", "🐘 हाथी (Elephant)", "🐆 चीता (Cheetah)", "🐒 बंदर (Monkey)")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultText = findViewById(R.id.tvResult)
        historyText = findViewById(R.id.tvHistory)

        findViewById<Button>(R.id.btnPredict).setOnClickListener { generatePrediction() }
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            prefs.edit().remove("history").apply()
            showHistory()
            resultText.text = getString(R.string.result_cleared)
        }
        showHistory()
    }

    private fun generatePrediction() {
        val selected = outcomes[Random.nextInt(outcomes.size)]
        resultText.text = getString(R.string.next_prediction, selected)
        val previous = prefs.getString("history", "").orEmpty()
        val updated = (listOf(selected) + previous.split("\n").filter { it.isNotBlank() })
            .take(20).joinToString("\n")
        prefs.edit().putString("history", updated).apply()
        showHistory()
    }

    private fun showHistory() {
        val entries = prefs.getString("history", "").orEmpty()
        historyText.text = if (entries.isBlank()) getString(R.string.no_history)
        else entries.split("\n").mapIndexed { index, item -> "${index + 1}. $item" }.joinToString("\n")
    }
}
