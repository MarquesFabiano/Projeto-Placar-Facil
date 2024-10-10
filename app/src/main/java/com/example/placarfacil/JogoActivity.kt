package com.example.placarfacil

import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JogoActivity : AppCompatActivity() {

    private var pontuacaoTimeA = 0
    private var pontuacaoTimeB = 0
    private var setAtual = 1
    private var tempoRestante = 0
    private var setsTotais = 0
    private var pontuacaoMaxima = 0

    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        // Adiciona o botão de voltar na ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Recebe os dados da atividade anterior
        pontuacaoMaxima = intent.getIntExtra("pontuacao_maxima", 0)
        setsTotais = intent.getIntExtra("quantidade_sets", 0)
        tempoRestante = intent.getIntExtra("tempo_set", 60)

        val textViewPlacar = findViewById<TextView>(R.id.textViewPlacar)
        val textViewSetAtual = findViewById<TextView>(R.id.textViewSetAtual)
        val textViewTempoRestante = findViewById<TextView>(R.id.textViewTempoRestante)
        val botaoTimeAAdd = findViewById<Button>(R.id.botaoTimeAAdd)
        val botaoTimeARemove = findViewById<Button>(R.id.botaoTimeARemove)
        val botaoTimeBAdd = findViewById<Button>(R.id.botaoTimeBAdd)
        val botaoTimeBRemove = findViewById<Button>(R.id.botaoTimeBRemove)

        // Exibe o set atual e o placar inicial
        textViewSetAtual.text = "Set Atual: $setAtual"
        textViewPlacar.text = "Placar: $pontuacaoTimeA x $pontuacaoTimeB"

        // Inicia o temporizador
        startTimer(textViewTempoRestante, textViewSetAtual)

        // Ações dos botões para adicionar/remover pontos para o Time A e B
        botaoTimeAAdd.setOnClickListener {
            pontuacaoTimeA++
            textViewPlacar.text = "Placar: $pontuacaoTimeA x $pontuacaoTimeB"
            checkWinner()
        }

        botaoTimeARemove.setOnClickListener {
            if (pontuacaoTimeA > 0) pontuacaoTimeA--
            textViewPlacar.text = "Placar: $pontuacaoTimeA x $pontuacaoTimeB"
        }

        botaoTimeBAdd.setOnClickListener {
            pontuacaoTimeB++
            textViewPlacar.text = "Placar: $pontuacaoTimeA x $pontuacaoTimeB"
            checkWinner()
        }

        botaoTimeBRemove.setOnClickListener {
            if (pontuacaoTimeB > 0) pontuacaoTimeB--
            textViewPlacar.text = "Placar: $pontuacaoTimeA x $pontuacaoTimeB"
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

    // Método que inicia o temporizador
    private fun startTimer(textViewTempoRestante: TextView, textViewSetAtual: TextView) {
        countDownTimer = object : CountDownTimer((tempoRestante * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                textViewTempoRestante.text = "Tempo Restante: ${secondsLeft}s"
            }

            override fun onFinish() {
                // Quando o tempo de um set terminar, verifica se é o último set
                if (setAtual < setsTotais) {
                    setAtual++
                    textViewSetAtual.text = "Set Atual: $setAtual"
                    startTimer(textViewTempoRestante, textViewSetAtual)
                } else {
                    Toast.makeText(this@JogoActivity, "Tempo de jogo finalizado", Toast.LENGTH_LONG).show()
                    endGame()
                }
            }
        }
        countDownTimer.start()
    }

    // Método que verifica se algum time atingiu a pontuação máxima
    private fun checkWinner() {
        if (pontuacaoTimeA >= pontuacaoMaxima) {
            Toast.makeText(this, "Time A venceu!", Toast.LENGTH_LONG).show()
            endGame()
        } else if (pontuacaoTimeB >= pontuacaoMaxima) {
            Toast.makeText(this, "Time B venceu!", Toast.LENGTH_LONG).show()
            endGame()
        }
    }

    // Método que encerra o jogo
    private fun endGame() {
        countDownTimer.cancel()
        // Lógica adicional para encerrar a partida ou reiniciar o jogo pode ser colocada aqui
    }
}
