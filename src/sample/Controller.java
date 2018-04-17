package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class Controller {

    private String operator;
    private boolean newIteration;
    private boolean operatorExist;
    private boolean secondArgument;
    private boolean firstNumberDot;
    private boolean secondNumberDot;

    private Model model;

    @FXML
    private Label output;

    public Controller() {

        this.initField();

        this.model = new Model();
    }

    @FXML
    private void btnNumberClick(ActionEvent event) {

        if (this.resetValue())
            return;

        String buttonValue = this.getValue(event);
        String currentValue = this.output.getText();

        if ((buttonValue.equals("0") || buttonValue.equals("00")) && currentValue.equals("0"))
            return;
        if (this.secondArgument && buttonValue.equals("00"))
            return;

        String displayValue = currentValue.equals("0") ? buttonValue : currentValue + buttonValue;

        this.output.setText(displayValue);
    }

    @FXML
    private void btnOperatorClick(ActionEvent event) {

        if (this.resetValue())
            return;

        if (this.operatorExist)
            return;

        String buttonValue  = this.getValue(event);
        String currentValue = this.output.getText();
        String displayValue = currentValue + " " + buttonValue + " ";

        this.operatorExist  = true;
        this.secondArgument = true;
        this.output.setText(displayValue);
    }

    @FXML
    private void btnDotClick(ActionEvent event) {

        if (this.resetValue())
            return;

        String buttonValue = this.getValue(event);
        String currentValue = this.output.getText();
        String displayValue = currentValue;

        if (!this.firstNumberDot && currentValue.indexOf(buttonValue) == -1) {
            displayValue = currentValue + buttonValue;
            this.firstNumberDot = true;
        } else if (!this.secondNumberDot && this.operatorExist) {
            displayValue = currentValue + buttonValue;
            this.secondNumberDot = true;
        }

        this.output.setText(displayValue);
    }

    @FXML
    private void btnClearClick(ActionEvent event) {

        allClear("0");
    }

    @FXML
    private void btnPlusMinusClick(ActionEvent event) {

        if (this.resetValue())
            return;

        String currentValue = this.output.getText();
        String displayValue;

        if (currentValue.charAt(0) == '-')
            displayValue = currentValue. substring(1);
        else
            displayValue = "-" + currentValue;

        this.output.setText(displayValue);
    }

    @FXML
    private void btnEqualClick(ActionEvent event) {

        if (this.resetValue())
            return;

        try {

            this.model.calculation(this.output.getText());
            this.output.setText(this.model.getResult());

        } catch (ArithmeticException e) {

            this.output.setText(e.getMessage());

        }

        this.newIteration = false;
    }

    private String getValue(ActionEvent event) {

        return ((Button) event.getSource()).getText();
    }

    private void allClear(String txt) {

        this.output.setText(txt);

        this.initField();
    }

    private void initField() {

        this.operator       = "";
        this.newIteration   = true;
        this.operatorExist  = false;
        this.secondArgument = false;
        this.firstNumberDot = false;
        this.secondNumberDot = false;
    }

    private boolean resetValue() {

        if (!this.newIteration) {
            this.allClear("0");
            return true;
        }

        return false;
    }
}
