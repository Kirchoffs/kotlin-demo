import javafx.fxml.FXML
import javafx.scene.layout.VBox
import javafx.scene.control.Label
import javafx.scene.control.Button
import javafx.scene.input.KeyEvent
import Operator.Add
import Operator.Sub
import Operator.Mul
import Operator.Div
import tornadofx.View

class Calculator: View() {
    override val root: VBox by fxml("/calculator.fxml")

    @FXML
    lateinit var display: Label

    private var state: Operator = Add(0)

    private val displayValue: Long
        get() = when(display.text) {
            "" -> 0
            else -> display.text.toLong()
        }

    init {
        title = "Calculator"

        root.lookupAll(".button").forEach {
            button -> button.setOnMouseClicked {
                operator((button as Button).text)
            }
        }

        root.addEventFilter(KeyEvent.KEY_TYPED) {
            operator(it.character.uppercase().replace("\r", "="))
        }
    }

    private fun onAction(fn: Operator) {
        state = fn
        display.text = ""
    }

    private fun operator(x: String) {
        if (Regex("[0-9]").matches(x)) {
            display.text += x
        } else {
            when(x) {
                "+" -> onAction(Add(displayValue))
                "-" -> onAction(Sub(displayValue))
                "/" -> onAction(Div(displayValue))
                "%" -> { onAction(Add(displayValue / 100)); operator("=") }
                "x" -> onAction(Mul(displayValue))
                "C" -> onAction(Add(0))
                "+/-" -> { onAction(Add(-1 * displayValue)); operator("=") }
                "=" -> display.text = state.calculate(displayValue).toString()
            }
        }
    }
}