package com.example.parcialtp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var editTextMonto: EditText
    private lateinit var editTextTasaInteres: EditText
    private lateinit var editTextPlazo: EditText
    private lateinit var editTextEntidad: EditText
    private lateinit var buttonGuardar: Button
    private lateinit var buttonHistorial: Button
    private lateinit var buttonTerminos: Button
    private lateinit var buttonComparar: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editTextMonto = findViewById(R.id.editTextMonto)
        editTextTasaInteres = findViewById(R.id.editTextTasaInteres)
        editTextPlazo = findViewById(R.id.editTextPlazo)
        editTextEntidad = findViewById(R.id.editTextEntidad)
        buttonGuardar = findViewById(R.id.buttonGuardar)
        buttonHistorial = findViewById(R.id.buttonHistorial)
        buttonTerminos = findViewById(R.id.buttonTerminos)


        buttonComparar = findViewById(R.id.buttonComparar)

        buttonComparar.setOnClickListener {
            compararInversiones()
        }

        buttonGuardar.setOnClickListener {
            guardarInversion()
        }

        buttonHistorial.setOnClickListener {
            val intent = Intent(this, HistorialActivity::class.java)
            startActivity(intent)
        }

        buttonTerminos.setOnClickListener {
            val intent = Intent(this, TerminosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun guardarInversion() {
        val monto = editTextMonto.text.toString()
        val tasaInteres = editTextTasaInteres.text.toString()
        val plazo = editTextPlazo.text.toString()
        val entidad = editTextEntidad.text.toString()

        val sharedPreferences = getSharedPreferences("Inversiones", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val inversiones = sharedPreferences.getStringSet("historial", mutableSetOf()) ?: mutableSetOf()

        inversiones.add("$monto, $tasaInteres, $plazo, $entidad")

        editor.putStringSet("historial", inversiones)
        editor.apply()

        Toast.makeText(this, "Inversión guardada: $monto, $tasaInteres, $plazo, $entidad", Toast.LENGTH_SHORT).show()
    }

    private fun compararInversiones() {
        val sharedPreferences = getSharedPreferences("Inversiones", MODE_PRIVATE)
        val inversiones = sharedPreferences.getStringSet("historial", mutableSetOf()) ?: mutableSetOf()

        if (inversiones.size < 2) {
            Toast.makeText(this, "Necesitas al menos dos inversiones para comparar.", Toast.LENGTH_SHORT).show()
            return
        }

        val iterator = inversiones.iterator()
        val inversion1 = iterator.next().split(", ")
        val inversion2 = iterator.next().split(", ")

        Log.d("Inversion1", inversion1.toString())
        Log.d("Inversion2", inversion2.toString())

        val intent = Intent(this, Calculo::class.java).apply {
            putExtra("monto1", inversion1[0])
            putExtra("tasaInteres1", inversion1[1])
            putExtra("monto2", inversion2[0])
            putExtra("tasaInteres2", inversion2[1])
        }
        startActivity(intent)
    }
}







