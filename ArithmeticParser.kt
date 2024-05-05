import java.lang.Exception

class ArithmeticParser {
    private var index = 0
    private lateinit var input: String

    fun parse(expression: String): Int {
        index = 0
        input = expression.replace(" ", "")
        return parseExpression()
    }

    private fun parseExpression(): Int {
        return when {
            input[index] == '(' -> {
                index++
                val leftOperand = parseExpression()
                val operation = parseOperation()
                val rightOperand = parseExpression()
                if (input[index++] != ')') throw Exception("Missing closing parenthesis")
                calculate(leftOperand, operation, rightOperand)
            }
            input[index] == '-' -> {
                index++
                if (!input[index].isDigit()) throw Exception("Invalid constant expression")
                val value = parseNumber()
                -value
            }
            input[index].isDigit() -> parseNumber()
            else -> throw Exception("Invalid expression")
        }
    }

    private fun parseOperation(): Char {
        val operation = input[index]
        return when (operation) {
            '+', '-', '*' -> {
                index++
                operation
            }
            else -> throw Exception("Invalid operation")
        }
    }

    private fun parseNumber(): Int {
        var number = 0
        while (index < input.length && input[index].isDigit()) {
            number = number * 10 + (input[index] - '0')
            index++
        }
        return number
    }

    private fun calculate(leftOperand: Int, operation: Char, rightOperand: Int): Int {
        return when (operation) {
            '+' -> leftOperand + rightOperand
            '-' -> leftOperand - rightOperand
            '*' -> leftOperand * rightOperand
            else -> throw Exception("Invalid operation")
        }
    }

}
