import java.util.*
import kotlin.math.pow

class Expressions(var infix: MutableList<String>) {


    private fun infixToPostfix() {
        var postfix = ""
        var stack = Stack<String>()

        for (e in infix) {
            if (e.all { it.isDigit() || it == '.' }) {
                postfix += "$e "
            } else if (e == "(") {
                stack.push(e)
            } else if (e == ")") {
                while (stack.isNotEmpty() && stack.peek() != "(") {
                    postfix += "${stack.pop()} "
                }
                stack.push(e)
                if (stack.isNotEmpty()) stack.pop()
            } else {
                while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(e)) {
                    postfix += "${stack.pop()} "
                }
            }
        }

        while (stack.isNotEmpty()) {
            postfix += "${stack.pop()} "
        }
    }

    fun evaluation(postfix: String): Number {
        var stack = Stack<Double>()
        var i = 0

        while (i <= postfix.length) {
            if (postfix[i] == ' ') {
                i++
                continue
            } else if (Character.isDigit(postfix[i]) || postfix[i] == '.') {
                var number = ""
                while (Character.isDigit(postfix[i]) || postfix[i] == '.') {
                    number += postfix[i]
                    i++
                }
                stack.push(number.toDouble())
            } else {
                val a = stack.pop()
                val b = stack.pop()
                when (postfix[i]) {
                    '+' -> stack.push(b + a)
                    '-' -> stack.push(b - a)
                    '*' -> stack.push(b * a)
                    '/' -> stack.push(b / a)
                    '%' -> stack.push(b % a)
                    '^' -> stack.push(b.pow(a))
                }
                i++
            }
        }
        return if(stack.peek() / stack.peek().toInt() == 1.0) stack.pop().toInt() else stack.pop()
    }

    private fun precedence(operator: String): Byte {
        return when (operator) {
            "+", "-" -> 1
            "*", "/", "%" -> 2
            "^" -> 3
            else -> 0
        }
    }

}