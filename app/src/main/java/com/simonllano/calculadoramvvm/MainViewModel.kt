package com.simonllano.calculadoramvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    //Opcion 1 de como declarar un Live data
    val total : MutableLiveData<Double> by lazy { // Se crea instancia del live data
        MutableLiveData<Double> ()
    }

    //Opcion 2 para declarar el Live data, hace lo mismo que hacela opcion con la diferencia de que muestra un mensaje
    private val  _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg
    //Theme.CalculadoraMVVM
    //<style name="Theme.CalculadoraMVVM" parent="Base.Theme.CalculadoraMVVM" />
    fun validateNumbers(number1: String, number2: String, number3: String) {
        if(number1.isEmpty() || number2.isEmpty()) {
            Log.d("error", "Hay empty")
            _errorMsg.value = "Debe digitar los dos numeros"
        }

        else{
            when(number3){
                "0" -> total.value = number1.toDouble() + number2.toDouble()
                "1" -> total.value = number1.toDouble() - number2.toDouble()
                "2"-> total.value = number1.toDouble() * number2.toDouble()
                "3"-> total.value = number1.toDouble() / number2.toDouble()
            }





        }

    }

}