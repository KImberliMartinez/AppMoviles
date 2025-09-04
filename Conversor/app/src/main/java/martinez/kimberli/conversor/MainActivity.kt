package martinez.kimberli.conversor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var  conversionSele: String = "longitud"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnLongitud: Button=findViewById(R.id.btnLongitud)
        val btnPeso:Button=findViewById(R.id.btnPeso)
        val btnTemperatura: Button=findViewById(R.id.btnTemperatura)
        val btnVolumen:Button=findViewById(R.id.btnVolumen)
        val txtNota: TextView=findViewById(R.id.NotaConversor)
        val txtAconvertir: EditText=findViewById(R.id.txtAconvertir)
        val txtResultado:TextView=findViewById(R.id.txtResultado)
        val btnConvertir:Button=findViewById(R.id.btnConvertir)


        btnLongitud.setOnClickListener {
            conversionSele="longitud"
            txtNota.setText("Convertir Km a millas")
        }
        btnPeso.setOnClickListener {
            conversionSele="peso"
            txtNota.setText("Convertir Kg a Libras")
        }

        btnTemperatura.setOnClickListener {
            conversionSele="temperatura"
            txtNota.setText("Convertir de Centigrados a Farenheit")
        }
        btnVolumen.setOnClickListener {
            conversionSele="volumen"
            txtNota.setText("Convertir litros a onzas")
        }

        btnConvertir.setOnClickListener {
            val valor = txtAconvertir.text.toString().trim().toDouble()

            val resultado = convertirValor(valor)

            txtResultado.text = "Resultado: $resultado"
        }
    }

    fun convertirValor(valor: Double): Double {
        var resultado = 0.0

        if (conversionSele == "longitud") {
            resultado = valor * 0.621371
        }
        else if (conversionSele == "peso") {
            resultado = valor * 2.20462
        }
        else if (conversionSele == "temperatura") {
            resultado = (valor * 9 / 5) + 32
        }
        else if (conversionSele == "volumen") {
            resultado = valor * 33.814
        }

        return resultado
    }
}