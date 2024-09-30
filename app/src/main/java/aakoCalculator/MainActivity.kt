package aakoCalculator

import Expressions
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.AakoCalculator.R

class MainActivity : AppCompatActivity() {
    private val input = mutableListOf<String>()
    private var resultTextBox: TextView? = null
    private var infixExpression: Expressions? = null

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

    @SuppressLint("SetTextI18n")
    fun onClick(button: View) {
        when (val buttonText= (button as AppCompatButton).text.toString()) {
            "=" -> {
                infixExpression = Expressions(input)
                resultTextBox?.text = infixExpression!!.evaluation().toString()
                input.clear()
                input.add(resultTextBox?.text.toString())
                //Log.d("1","What the fuck is wrong? ${onClick(button)}")
            }

            "C" -> {
                input.clear()
                resultTextBox?.text = ""
            }

            "⌫" -> {
                resultTextBox?.text = "${resultTextBox?.text}".dropLast(1)
                if (resultTextBox?.text?.isNotEmpty()!! && resultTextBox?.text?.last() == ' ') {
                    resultTextBox?.text = "${resultTextBox?.text}".dropLast(1)
                }
                if (input.last().length == 1) {
                    input.removeAt(input.lastIndex)
                } else {
                    input[input.lastIndex] = input.last().dropLast(1)
                }
            }

            else -> {
                if (Character.isDigit(buttonText[0]) || buttonText[0] == '.') {
                    if (input.isNotEmpty() && Character.isDigit(input.last()[0])) {
                        resultTextBox?.text = "${resultTextBox?.text}${button.text}"
                    } else {
                        input.add(buttonText)
                        resultTextBox?.text = "${resultTextBox?.text} ${button.text}"
                    }
                } else {
                    input.add(buttonText)
                    resultTextBox?.text = "${resultTextBox?.text} ${button.text}"
                }
            }
        }
    }
}