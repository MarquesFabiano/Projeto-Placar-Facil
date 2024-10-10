package com.example.placarfacil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botaoDefinicaoPartida = findViewById<Button>(R.id.botaoDefinicaoPartida)

        botaoDefinicaoPartida.setOnClickListener {
            startActivity(Intent(this, DefinicaoPartidaActivity::class.java))
        }
    }
}
