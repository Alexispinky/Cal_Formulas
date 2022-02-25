package com.example.cal_formulas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.cal_formulas.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var posg : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.Formulas, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        //val spinner: Spinner = findViewById(R.id.opciones_spinner)

        spinner.onItemSelectedListener = this

    }

    //class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        when(pos)
        {
            0 -> setInvisibleAll()
            1 -> setCono()
            2 -> setFormulaGeneral()
            3 -> setPendiente()
        }
    }


    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }



    private fun setInvisibleAll()
    {
        posg = 0
        binding.ans1.isInvisible = true
        binding.ans2.isInvisible = true
        binding.ans3.isInvisible = true
        binding.ans4.isInvisible = true
        binding.imageView.isInvisible = true
        binding.val1.isInvisible = true
        binding.val2.isInvisible = true
        binding.val3.isInvisible = true
        binding.val4.isInvisible = true
        binding.button.isInvisible = true
        binding.ans1.text.clear()

    }

    private fun setCono() {
        setInvisibleAll()
        posg = 1
        binding.val2.text = getString(R.string.altura)
        binding.val1.text = getString(R.string.radio)
        binding.ans1.hint = getString(R.string.radio)
        binding.ans2.hint = getString(R.string.altura)
        binding.val1.isVisible = true
        binding.val2.isVisible = true
        binding.ans1.isVisible = true
        binding.ans2.isVisible = true
        binding.imageView.setImageResource(R.drawable.volumencono)
        binding.imageView.contentDescription = getString(R.string.des1)
        binding.imageView.isVisible = true
        binding.button.isVisible = true
    }

    private fun setFormulaGeneral(){
        setInvisibleAll()
        posg = 2
        binding.ans1.hint = getString(R.string.A)
        binding.ans2.hint = getString(R.string.B)
        binding.ans3.hint = getString(R.string.C)
        binding.val1.text = getText(R.string.A)
        binding.val2.text = getString(R.string.B)
        binding.val3.text = getString(R.string.C)
        binding.ans1.isVisible = true
        binding.ans2.isVisible = true
        binding.ans3.isVisible = true
        binding.val1.isVisible = true
        binding.val2.isVisible = true
        binding.val3.isVisible = true
        binding.imageView.setImageResource(R.drawable.formulageneral)
        binding.imageView.isVisible = true
        binding.button.isVisible = true
    }

    private fun setPendiente(){
        setInvisibleAll()
        posg = 3
        binding.ans1.hint = getString(R.string.x1)
        binding.ans2.hint = getString(R.string.y1)
        binding.ans3.hint = getString(R.string.x2)
        binding.ans4.hint = getString(R.string.y2)
        binding.val1.text = getText(R.string.x1)
        binding.val2.text = getString(R.string.y1)
        binding.val3.text = getString(R.string.x2)
        binding.val4.text = getString(R.string.y2)
        binding.ans1.isVisible = true
        binding.ans2.isVisible = true
        binding.ans3.isVisible = true
        binding.ans4.isVisible = true
        binding.val1.isVisible = true
        binding.val2.isVisible = true
        binding.val3.isVisible = true
        binding.val4.isVisible = true
        binding.imageView.setImageResource(R.drawable.pendienterecta)
        binding.imageView.isVisible = true
        binding.button.isVisible = true
    }

    fun Calcula(view: View) {
        when(posg){
            1 -> evaluaCono()
            2 -> evaluaFormula()
            3 -> evaluaPendiente()
        }

    }

    private fun evaluaPendiente() {
        when {
            binding.ans1.text.toString() == "" -> {
                binding.ans1.error = getString(R.string.ErrorVacio)
            }
            binding.ans2.text.toString() == "" -> {
                binding.ans2.error = getString(R.string.ErrorVacio)
            }
            binding.ans3.text.toString() == "" -> {
                binding.ans3.error = getString(R.string.ErrorVacio)
            }
            binding.ans4.text.toString() == "" -> {
                binding.ans4.error = getString(R.string.ErrorVacio)
            }
            else -> {
                val intent = Intent(this, Resultado::class.java)
                val parametros = Bundle()
                parametros.putInt("Formula",posg)
                parametros.putDouble(getString(R.string.x1),binding.ans1.toString().toDouble())
                parametros.putDouble(getString(R.string.y1),binding.ans2.toString().toDouble())
                parametros.putDouble(getString(R.string.x2),binding.ans3.toString().toDouble())
                parametros.putDouble(getString(R.string.y2),binding.ans4.toString().toDouble())
                intent.putExtras(parametros)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun evaluaFormula() {
        when {
            binding.ans1.text.toString() == "" -> {
                binding.ans1.error = getString(R.string.ErrorVacio)
            }
            binding.ans2.text.toString() == "" -> {
                binding.ans2.error = getString(R.string.ErrorVacio)
            }
            binding.ans3.text.toString() == "" -> {
                binding.ans3.error = getString(R.string.ErrorVacio)
            }
            binding.ans1.text.toString().toDouble() == 0.0 -> {
                binding.ans1.error = getString(R.string.ErrorACero)
            }
            else -> {
                val intent = Intent(this, Resultado::class.java)
                val parametros = Bundle()
                parametros.putInt("Formula",posg)
                parametros.putDouble(getString(R.string.A),binding.ans1.text.toString().toDouble())
                parametros.putDouble(getString(R.string.B),binding.ans2.text.toString().toDouble())
                parametros.putDouble(getString(R.string.C),binding.ans3.text.toString().toDouble())
                intent.putExtras(parametros)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun evaluaCono() {
        when {
            binding.ans1.text.toString() == "" -> {
                binding.ans1.error = getString(R.string.ErrorVacio)
            }
            binding.ans2.text.toString() == "" -> {
                binding.ans2.error = getString(R.string.ErrorVacio)
            }
            else -> {
                val intent = Intent(this, Resultado::class.java)
                val parametros = Bundle()
                parametros.putInt("Formula",posg)
                parametros.putDouble(getString(R.string.radio),binding.ans1.text.toString().toDouble())
                parametros.putDouble(getString(R.string.altura), binding.ans2.text.toString().toDouble())
                intent.putExtras(parametros)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun validaCampos(): Boolean{
        return when(posg){
            1 -> {
                if (binding.ans1.text.toString() == ""){
                    false
                }else binding.ans2.text.toString() != ""
            }
            else -> {
                false
            }
        }
    }
}
