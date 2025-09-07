package martinez.kimberli.calculadoraimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val peso: EditText = findViewById(R.id.etPeso)
    val altura: EditText = findViewById(R.id.etEstatura)
    val btnCalcular: Button = findViewById(R.id.btnCalcular)
    val imc: TextView = findViewById(R.id.txResultadoIMC)
    val estado: TextView = findViewById(R.id.txResultadoIMC)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnCalcular.setOnClickListener {
            val pesoValor= peso.text.toString().toDoubleOrNull()
            val estaturaValor=altura.toString().toDoubleOrNull()
            if (pesoValor != null && estaturaValor != null && estaturaValor > 0) {
                // Cálculo de IMC
                val result = pesoValor / (estaturaValor * estaturaValor)

                // Mostrar el IMC
                imc.text = "IMC: %.2f".format(result)

                // poonerlo cat
                val (category, colorRes) = when {
                    result < 18.5 -> "Bajo peso" to R.color.colorGreenish
                    result in 18.5..24.9 -> "Normal" to R.color.colorGreen
                    result in 25.0..29.9 -> "Sobrepeso" to R.color.colorYellow
                    result in 30.0..34.9 -> "Obesidad grado 1" to R.color.coloOrange
                    result in 35.0..39.9 -> "Obesidad grado 2" to R.color.colorRed
                    else -> "Obesidad grado 3" to R.color.colorBrown
                }

                // Mostrar la categoría y cambiar el color del fondo
                estado.text = category
                estado.setBackgroundResource(colorRes)

            } else {
                // Manejo de error si no ingresaron datos válidos
                imc.text = "Ingresa valores válidos"
                estado.text = ""
                estado.setBackgroundResource(android.R.color.transparent)
            }
        }
    }

    //otra funcion
}