package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class Controller {

    private double firstNumber;
    private double secondNumber;
    private String operator;
    private boolean start;

    @FXML
    private Label output;

    public Controller() {

        this.firstNumber    = 0.0;
        this.secondNumber   = 0.0;
        this.start          = false;
    }

    @FXML
    private void btnNumberClick(ActionEvent event) {

        String buttonValue = this.getValue(event);
        String currentValue = this.output.getText();

        if ((buttonValue.equals("0") || buttonValue.equals("00")) && currentValue.equals("0"))
            return;

        String displayValue = currentValue.equals("0") ? buttonValue : currentValue + buttonValue;

        this.output.setText(displayValue);
    }

    @FXML
    private void btnOperatorClick(ActionEvent event) {

        String buttonValue = this.getValue(event);

        if (buttonValue.equals("=") && this.start) {
            try {

                double result = Model.calculation(this.firstNumber, this.secondNumber, this.operator.charAt(0));

            } catch (ArithmeticException e) {
                this.allClear("Деление на ноль!");
            }
        } else {

            this.output.setText(buttonValue);

        }
    }

    @FXML
    private void btnDotClick(ActionEvent event) {

    }

    @FXML
    private void btnClearClick(ActionEvent event) {

        allClear("0");
    }

    private String getValue(ActionEvent event) {

        return ((Button) event.getSource()).getText();
    }

    private void allClear(String txt) {

        this.output.setText(txt);

        this.firstNumber    = 0.0;
        this.secondNumber   = 0.0;
        this.operator       = "";
        this.start          = true;
    }
}
