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
            mainViewModel.validateNumbers(mainBinding.firstNumberEditText.text.toString(),mainBinding.secondNumberEditText.text.toString()) //llamando metodo de ViewModel

        }

        mainBinding.substracButton.setOnClickListener {
            mainViewModel.validateNumbers()
        }

        val sumaObserver = Observer<Double> {suma->  //Se crea el objeto observer
            mainBinding.resultTextView.setText(suma.toString())
        }
        mainViewModel.suma.observe(this, sumaObserver) //Se conecta el view model con ...

        val errorMsgObserver = Observer<String> {errorMsg ->
            Snackbar.make(view, errorMsg, Snackbar.LENGTH_INDEFINITE)
                .setAction( "Aceptar") {}
                .show()
        }
    }
}