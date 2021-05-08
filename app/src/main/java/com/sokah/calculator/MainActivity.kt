package com.sokah.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric = false;
    var lastDot = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun Ondigit(view: View) {

        txtInput.append((view as Button).text)
        lastNumeric = true


    }

    fun Clear(view: View){

        txtInput.text=""
        lastNumeric = false
        lastDot = false

    }
    fun OnDecimalPoint(view: View){

        if(lastNumeric &&!lastDot) {

            txtInput.append(".")
            lastNumeric = false
            lastDot = true
        }

    }
}