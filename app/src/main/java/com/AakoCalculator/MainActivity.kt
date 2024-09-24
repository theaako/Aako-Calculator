package com.AakoCalculator

import Expression
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val input = mutableListOf<String>()
    private var resultTextBox: TextView? = null
    private var infixExpression: Expression? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resultTextBox = findViewById(R.id.resultTextView)
    }

    fun onClick(button: View) {
        val buttonText  = (button as AppCompatButton).text.toString()
        when (buttonText) {
            "=" -> {
                infixExpression = Expression(input)
                resultTextBox?.text = infixExpression!!.evaluateExpressions().toString()
            }
            else -> {input.add(buttonText)
                resultTextBox?.text = "${resultTextBox?.text} ${button.text}"
            3646}
        }
    }
}