package com.example.parcialtp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistorialActivity : AppCompatActivity() {

    private lateinit var textViewHistorial: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        textViewHistorial = findViewById(R.id.textViewHistorial)

        val sharedPreferences = getSharedPreferences("Inversiones", MODE_PRIVATE)
        val inversiones = sharedPreferences.getStringSet("historial", mutableSetOf())

        val historialTexto = inversiones?.joinToString("\n") ?: "No hay inversiones guardadas."
        textViewHistorial.text = historialTexto
    }
}
