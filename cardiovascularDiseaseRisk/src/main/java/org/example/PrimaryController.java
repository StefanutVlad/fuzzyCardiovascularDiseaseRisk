package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField age_change;
    @FXML
    private ChoiceBox cb_age;
    @FXML
    private Slider slider_BMI;
    @FXML
    private Slider slider_Pressure;
    @FXML
    private Slider slider_PhysicalActivity;
    @FXML
    private Slider slider_Medicamentation;
    @FXML
    private Label label_BMI;
    @FXML
    private Label label_Pressure;
    @FXML
    private Label label_PhysicalActivity;
    @FXML
    private Label label_Medicamentation;
    @FXML
    private Label label_fuzzy_Age;
    @FXML
    private Label label_fuzzy_BMI;
    @FXML
    private Label label_fuzzy_BloodPressure;
    @FXML
    private Label label_fuzzy_Medicamentation;
    @FXML
    private Label label_fuzzy_PhysicalActivity;
    @FXML
    private Label label_fuzzy_Risk;
    @FXML
    private Label label_risk;

    private double fuzzyInputAge = 0;
    private double fuzzyInputBMI = 0;
    private double fuzzyInputBloodPressure = 0;
    //private double fuzzyInputPhysicalActivity = 0;
    //private double fuzzyInputMedicamentation = 0;
    private double fuzzyOutputRisk = 0;
    private FuzzyCdrController FuzzyCdrController = new FuzzyCdrController();

    public void calculateRisk() {
        if (fuzzyInputAge != 0 && fuzzyInputBMI != 0 && fuzzyInputBloodPressure != 0) {
            fuzzyOutputRisk = FuzzyCdrController.getOutput(fuzzyInputAge, fuzzyInputBMI, fuzzyInputBloodPressure);
            System.out.println("Risk: "+ fuzzyOutputRisk);
            String textOutput = fuzzyOutputRisk + "";
            label_fuzzy_Risk.setText("Output Risk: " + textOutput.substring(0, 4));
            String textRisk = textOutput.substring(0, 5);

            label_risk.setText(textRisk);
        }
    }

    public void onSliderBMI() {
        String text = slider_BMI.getValue() + "";
        label_BMI.setText(text.substring(0, 4));
        label_fuzzy_BMI.setText("Input BMI: " + text.substring(0, 4));
        fuzzyInputBMI = slider_BMI.getValue();
        calculateRisk();
    }
    public void onSliderPressure() {
        String text = slider_Pressure.getValue() + "";
        label_Pressure.setText(text.substring(0, 4));
        label_fuzzy_BloodPressure.setText("Input Blood Pressure: " + text.substring(0, 4));
        fuzzyInputBloodPressure = slider_Pressure.getValue();
        calculateRisk();
    }

//    public void onSliderPhysicalActivity() {
//        String text = slider_PhysicalActivity.getValue() + "";
//        label_PhysicalActivity.setText(text.substring(0, 4));
//        label_fuzzy_PhysicalActivity.setText("Input Physical Activity: " + text.substring(0, 4));
//        fuzzyInputPhysicalActivity = slider_PhysicalActivity.getValue();
//        calculateRisk();
//    }
//    public void onSliderMedicamentation() {
//        String text = slider_Medicamentation.getValue() + "";
//        label_Medicamentation.setText(text.substring(0, 4));
//        label_fuzzy_Medicamentation.setText("Input Medicamentation: " + text.substring(0, 4));
//        fuzzyInputMedicamentation = slider_Medicamentation.getValue();
//        calculateRisk();
//    }

    public void onAgeChange() {
        if (Double.parseDouble(age_change.getText()) > 100) {
            age_change.setText("100");
            label_fuzzy_Age.setText("Input Age: " + 100);
            fuzzyInputAge = 100;
            calculateRisk();
        } else if (Double.parseDouble(age_change.getText()) < 25) {
            age_change.setText("25");
            label_fuzzy_Age.setText("Input Age: " + 25);
            fuzzyInputAge = 25;
            calculateRisk();
        } else {
            label_fuzzy_Age.setText("Input Age: " + age_change.getText());
            fuzzyInputAge = Double.parseDouble(age_change.getText());
            calculateRisk();
        }
    }

    public void onChoiceMade() {
        System.out.println(cb_age.getValue().toString());
        switch (cb_age.getValue().toString()) {
            case "Young":
                age_change.setText("25");
                label_fuzzy_Age.setText("Input Age: " + age_change.getText());
                fuzzyInputAge = 25;
                calculateRisk();
                break;
            case "MidAge":
                age_change.setText("38");
                label_fuzzy_Age.setText("Input Age: " + age_change.getText());
                fuzzyInputAge = 38;
                calculateRisk();
                break;
            case "Old":
                age_change.setText("49");
                label_fuzzy_Age.setText("Input Age: " + age_change.getText());
                fuzzyInputAge = 49;
                calculateRisk();
                break;
            default:
                age_change.setText("-");
        }
    }
}
