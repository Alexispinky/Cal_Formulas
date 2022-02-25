package com.example.cal_formulas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cal_formulas.databinding.ActivityResultadoBinding
import kotlin.math.sqrt

class Resultado : AppCompatActivity() {
    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val parametros = intent.extras

        when(parametros?.getInt("Formula",0)){
            1 -> calculaVolumen(parametros.getDouble(getString(R.string.radio)),parametros.getDouble(getString(R.string.altura),))
            2 -> calculaGeneral(parametros.getDouble(getString(R.string.A)),parametros.getDouble(getString(R.string.B)),parametros.getDouble(getString(R.string.C)))
            3 -> calculaPendiente(parametros.getDouble(getString(R.string.x1)),parametros.getDouble(getString(R.string.y1)),parametros.getDouble(getString(R.string.x2)),parametros.getDouble(getString(R.string.y2)))
        }
    }

    private fun calculaPendiente(X1: Double, Y1: Double, X2: Double, Y2: Double) {
        val res = (Y1 - Y2) / (X2 - X1)
        binding.textView.text = getString(R.string.PendienteRes,res)
    }

    private fun calculaGeneral(A: Double, B: Double, C: Double) {
        val x = (B*B) - (4*A*C)
        if(x > 0 ) {
            val res1 = (-(B) + sqrt(x)) / 2 * A
            val res2 = (-(B) - sqrt(x)) / 2 * A
            binding.textView.text = getString(R.string.RespuestaGeneral,res1,res2)
        }else
            binding.textView.text = getString(R.string.ErrorMenor0)
    }

    private fun calculaVolumen(Radio: Double, Altura: Double) {
        val res = (Math.PI * (Radio*Radio) * Altura) / 3
        binding.textView.text = getString(R.string.Volumen,res)
    }

    fun Regresar(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}