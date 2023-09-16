package com.simonllano.calculadoramvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.simonllano.calculadoramvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java] //inicializando ViewModel
        super.onCreate(savedInstanceState)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.addButton.setOnClickListener {
            mainViewModel.validateNumbers(mainBinding.firstNumberEditText.text.toString(),mainBinding.secondNumberEditText.text.toString(),"0") //llamando metodo de ViewModel

        }

        mainBinding.substracButton.setOnClickListener {
            mainViewModel.validateNumbers(mainBinding.firstNumberEditText.text.toString(),mainBinding.secondNumberEditText.text.toString(),"1")
        }

        mainBinding.multiplyButton.setOnClickListener {
            mainViewModel.validateNumbers(mainBinding.firstNumberEditText.text.toString(),mainBinding.secondNumberEditText.text.toString(),"2")
        }

        mainBinding.divideButton.setOnClickListener {
            mainViewModel.validateNumbers(mainBinding.firstNumberEditText.text.toString(),mainBinding.secondNumberEditText.text.toString(),"3")
        }
        val resultadoObserver = Observer<Double> {total->  //Se crea el objeto observer
            mainBinding.resultTextView.setText(total.toString())
        }
        mainViewModel.total.observe(this, resultadoObserver) //Se conecta el view model con ...

        val errorMsgObserver = Observer<String> {errorMsg ->
            Snackbar.make(view, errorMsg, Snackbar.LENGTH_INDEFINITE)
                .setAction( "Aceptar") {}
                .show()
        }
    }
}