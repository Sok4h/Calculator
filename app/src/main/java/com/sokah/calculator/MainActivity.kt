package com.sokah.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var lastNumeric = false
    private var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onDigit(view: View) {

        txtInput.append((view as Button).text)
        lastNumeric = true
        lastDot = false


    }

    fun clear(view: View){

        txtInput.text=""
        lastNumeric = false
        lastDot = false

    }
    fun onDecimalPoint(view: View){

        if(lastNumeric &&!lastDot) {

            txtInput.append(".")
            lastNumeric = false
            lastDot = true
        }

    }

    private fun isOperator (value :String) : Boolean {

        return if(value.startsWith("-")){

            false
        }
        else{ value.contains("/")||value.contains("x")||value.contains("+")||value.contains("-")}

    }

    fun onOperator (view: View){

        if(lastNumeric && !isOperator(txtInput.text.toString())){

            txtInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual (view: View){

        if(lastNumeric){

            var tvValue = txtInput.text.toString()
            var prefix = ""
            lateinit  var splitValue: String

            when {
                tvValue.contains("/") -> {

                    splitValue="/"
                }
                tvValue.contains("+") -> {

                    splitValue="+"
                }
                tvValue.contains("-") -> {

                    splitValue="-"
                }

                tvValue.contains("x") -> {

                    splitValue="x"
                }
            }

            try {


                if(tvValue.startsWith("-")){

                    prefix="-"

                    tvValue=tvValue.substring(1)
                }

                val splitedOperation = tvValue.split(splitValue)
                var operator1 = splitedOperation[0]
                val operator2= splitedOperation[1]

                // verifica que si el numero empieza con -
                if(prefix.isNotEmpty()){

                    operator1=prefix + operator1
                }

                //realiza la operacion
                when (splitValue) {
                    "/" -> {

                        txtInput.text = removeZero((operator1.toDouble() / operator2.toDouble()).toString())
                    }
                    "+" -> {

                        txtInput.text = removeZero((operator1.toDouble() + operator2.toDouble()).toString())
                    }
                    "-" -> {

                        txtInput.text =removeZero((operator1.toDouble() - operator2.toDouble()).toString())
                    }

                    "x" -> {

                        txtInput.text =removeZero((operator1.toDouble() * operator2.toDouble()).toString())
                    }
                }

            }
            catch(error:Error){


            }
        }
    }

    fun removeZero (result:String):String{

        var value = result

        if(result.contains(".0")) {

            value = result.substring(0,result.length-2)

        }
        return value
    }
}