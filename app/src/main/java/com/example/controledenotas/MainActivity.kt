package com.example.controledenotas

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private lateinit var nomeEditText: EditText
private lateinit var nota1EditText:EditText
private lateinit var nota2EditText:EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calcular = findViewById<Button>(R.id.calcular)  //val calcular = findViewById (R.id.calcular) as Button
        val sair = findViewById<Button>(R.id.sair)

        calcular.setOnClickListener {
            nota1EditText = findViewById<EditText>(R.id.nota1) //resgatando os valores inseridos pelo usuário
            nota2EditText = findViewById<EditText>(R.id.nota2)
            nomeEditText = findViewById<EditText>(R.id.nome)

            val resultado = findViewById<TextView>(R.id.resultado)
            val situacao = findViewById<TextView>(R.id.situacao)

            /*Se estiver tudo validado corretamente, essa função será executada aqui*/
            if (validarCampos()) {
                val nota1 = nota1EditText.text.toString().toInt()
                val nota2 = nota2EditText.text.toString().toInt()
                val nome = nomeEditText.text.toString()

                val media = calcularMedia(nota1, nota2)

                val intent = Intent(this, RelatorioActivity::class.java)

                intent.putExtra("nome", nomeEditText.text.toString())
                intent.putExtra("nota", "${nota1}")
                intent.putExtra("nota", "${nota2}")
                intent.putExtra("media", "${media}")
                intent.putExtra("situacao", situacaoAluno(media))


                startActivity(intent)
/*

                resultado.text = situacaoAluno(media)
*/

            }
        }

        sair.setOnClickListener {
            finish()
        }
    }

    private fun calcularMedia(nota1: Int, nota2: Int): Int {
        return (nota1 + nota2) / 2
    }

    private fun validarCampos(): Boolean {
        /*Validando o campo do nome*/
        var noError = true
        if (nomeEditText.text.isBlank()){
            nomeEditText.setError("Digite seu nome!")
            noError = false
        }
        if(nota1EditText.text.isBlank()){
            nota1EditText.setError("Digite a nota 1")
            noError = false
        }
        if (nota2EditText.text.isBlank()){
            nota2EditText.setError("Digite a nota 2")
            noError = false
        }
        return noError
    }
}