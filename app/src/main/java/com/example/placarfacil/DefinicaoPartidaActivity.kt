package com.example.placarfacil

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DefinicaoPartidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definicao_partida)

        // Adiciona o botão de voltar na ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val editTextPontuacaoMaxima = findViewById<EditText>(R.id.editTextPontuacaoMaxima)
        val editTextQuantidadeSets = findViewById<EditText>(R.id.editTextQuantidadeSets)
        val editTextTempoSet = findViewById<EditText>(R.id.editTextTempoSet)
        val botaoConfirmar = findViewById<Button>(R.id.botaoConfirmar)

        botaoConfirmar.setOnClickListener {
            val pontuacaoMaxima = editTextPontuacaoMaxima.text.toString()
            val quantidadeSets = editTextQuantidadeSets.text.toString()
            val tempoSet = editTextTempoSet.text.toString()

            if (pontuacaoMaxima.isEmpty() || quantidadeSets.isEmpty() || tempoSet.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, JogoActivity::class.java)
                intent.putExtra("pontuacao_maxima", pontuacaoMaxima.toInt())
                intent.putExtra("quantidade_sets", quantidadeSets.toInt())
                intent.putExtra("tempo_set", tempoSet.toInt())
                startActivity(intent)
            }
        }
    }

    // Método que trata a ação do botão de voltar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
