package com.example.parcialtp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calculo : AppCompatActivity() {

    companion object {
        val historialComparaciones = mutableListOf<String>()
    }

    private lateinit var textoResultado: TextView
    private lateinit var botonVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo)

        textoResultado = findViewById(R.id.textViewResultado)
        botonVolver = findViewById(R.id.buttonVolver)

        val monto1 = intent.getStringExtra("monto1")?.toDoubleOrNull() ?: 0.0
        val tasaInteres1 = intent.getStringExtra("tasaInteres1")?.toDoubleOrNull() ?: 0.0
        val monto2 = intent.getStringExtra("monto2")?.toDoubleOrNull() ?: 0.0
        val tasaInteres2 = intent.getStringExtra("tasaInteres2")?.toDoubleOrNull() ?: 0.0

        val rendimiento1 = calcularRendimiento(monto1, tasaInteres1)
        val rendimiento2 = calcularRendimiento(monto2, tasaInteres2)

        val comparacion = "Inversión 1: $rendimiento1, Inversión 2: $rendimiento2 - ${recomendarInversion(rendimiento1, rendimiento2)}"
        historialComparaciones.add(comparacion)

        val resultado = "Inversión 1: Rendimiento = $rendimiento1\nInversión 2: Rendimiento = $rendimiento2\n" +
                "Recomendación: ${recomendarInversion(rendimiento1, rendimiento2)}"
        textoResultado.text = resultado

        botonVolver.setOnClickListener {
            finish()
        }
    }

    private fun calcularRendimiento(monto: Double, tasaInteres: Double): Double {
        return (monto * tasaInteres) / 100
    }

    private fun recomendarInversion(rendimiento1: Double, rendimiento2: Double): String {
        return when {
            rendimiento1 > rendimiento2 -> "Mejor opción: Inversión 1"
            rendimiento2 > rendimiento1 -> "Mejor opción: Inversión 2"
            else -> "Ambas inversiones tienen el mismo rendimiento"
        }
    }
}


