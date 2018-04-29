package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class Controller {

    private boolean operatorExist;
    private boolean dotExist;
    private boolean resultExist;

    private Model model;
    @FXML
    private Label output;
    @FXML
    private Label additionalOutput;

    public Controller() {

        this.initField();

        this.model = new Model();
    }

    @FXML
    private void btnNumberClick(ActionEvent event) {

        if (this.resultExist)
            this.allClear();

        String buttonValue = this.getValue(event);
        String currentValue = this.output.getText();

        if (buttonValue.equals("00") && currentValue.equals("0"))
            return;

        String displayValue = currentValue.equals("0") ? buttonValue : currentValue + buttonValue;

        this.output.setText(displayValue);
    }

    @FXML
    private void btnOperatorClick(ActionEvent event) {

        if (this.resultExist) {
            this.allClear();
            return;
        }
        
        if (this.operatorExist) {
            this.btnEqualClick();
            return;
        }

        String buttonValue  = this.getValue(event);
        String currentValue = this.output.getText();
        String displayValue = currentValue + " " + buttonValue + " ";

        this.additionalOutput.setText(displayValue);

        this.output.setText("0");

        this.dotExist       = false;
        this.operatorExist  = true;
    }

    @FXML
    private void btnDotClick(ActionEvent event) {

        if (this.dotExist)
            return;

        if (this.resultExist)
            this.allClear();

        String buttonValue = this.getValue(event);
        String currentValue = this.output.getText();

        String displayValue = currentValue + buttonValue;

        this.output.setText(displayValue);

        this.dotExist = true;
    }

    @FXML
    private void btnClearClick() {

        allClear();
    }

    @FXML
    private void btnPlusMinusClick() {

        if (this.resultExist)
            this.allClear();

        String currentValue = this.output.getText();
        String displayValue;

        if (currentValue.charAt(0) == '-')
            displayValue = currentValue. substring(1);
        else
            displayValue = "-" + currentValue;

        this.output.setText(displayValue);
    }

    @FXML
    private void btnEqualClick() {

        if (this.resultExist)
            this.allClear();

        try {

            String result = this.additionalOutput.getText() + this.output.getText();

            this.model.calculation(result);
            this.additionalOutput.setText(result);
            this.output.setText(this.model.getResult());
        } catch (ArithmeticException e) {

            this.output.setText(e.getMessage());
        }

        this.resultExist = true;
    }

    private String getValue(ActionEvent event) {

        return ((Button) event.getSource()).getText();
    }

    private void allClear() {

        this.output.setText("0");
        this.additionalOutput.setText("");

        this.initField();
    }

    private void initField() {

        this.operatorExist  = false;
        this.dotExist       = false;
        this.resultExist    = false;
    }
}
