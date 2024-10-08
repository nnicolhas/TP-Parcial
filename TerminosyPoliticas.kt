package com.example.parcialtp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TerminosActivity : AppCompatActivity() {

    private lateinit var textViewTerminos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminos)

        textViewTerminos = findViewById(R.id.textViewTerminos)
        textViewTerminos.text = getString(R.string.politicas_y_terminos)
    }
}
