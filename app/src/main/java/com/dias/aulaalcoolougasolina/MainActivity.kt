package com.dias.aulaalcoolougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    //1- Criar os atributos que serão usados na interface
    //com lateinit posso declarar a variavel e fazer a entrada de dados nessa variavel depois
    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //Remove a parte de cima do app, a cor da barra de bateria, horario, rede.
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Metodo para inicializarComponentesInterface
        inicializarComponentesInterface()
        btnCalcular.setOnClickListener {
            calcularMelhorPreco()
        }
    }

    //3. Metodo para calcular
    private fun calcularMelhorPreco() {
        val precoAlcool = editAlcool.text.toString()
        val precoGasolina = editGasolina.text.toString()

        //4. Verificar se está vazio
        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina) // Parâmetro

        //6. Fazer o calculo e mostrar o resultado
        if (resultadoValidacao) {
            //Calculo de melhor preço
            val precoAlcoolNumero = precoAlcool.toDouble()
            val precoGasolicaNumero = precoGasolina.toDouble()
            val resultado = precoAlcoolNumero / precoGasolicaNumero
            if (resultado >= 0.7) {
                textResultado.text = "Melhor utilizar Gasolina"
            } else {
                textResultado.text = "Melhor utilizar Alcool"
            }
        }
    }

    //5. Verificar se está vazio
    private fun validarCampos(pAlcool: String, pGasolina: String): Boolean {

        textInputAlcool.error = null
        textInputGasolina.error = null

        if (pAlcool.isEmpty()) {
            textInputAlcool.error = "Digite o preço do Alcool"
            return false
        } else if(pGasolina.isEmpty()) {
            textInputGasolina.error = "Digite o preço da Gasolina"
            return false
        }

        return true
    }

    //2 - Metodo para inicializarComponentesInterface
    private fun inicializarComponentesInterface() {
        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputGasolina = findViewById(R.id.text_input_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)
        textResultado = findViewById(R.id.text_resultado)
    }
}