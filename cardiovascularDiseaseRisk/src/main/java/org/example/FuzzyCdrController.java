package org.example;

import com.fuzzylite.Engine;
import com.fuzzylite.activation.General;
import com.fuzzylite.defuzzifier.Centroid;
import com.fuzzylite.norm.s.Maximum;
import com.fuzzylite.norm.t.AlgebraicProduct;
import com.fuzzylite.norm.t.Minimum;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;
import com.fuzzylite.term.Triangle;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;

public class FuzzyCdrController {
    private Engine engine = new Engine();
    private InputVariable age = new InputVariable();
    private InputVariable BMI = new InputVariable();
    private InputVariable bloodPressure = new InputVariable();
    //private InputVariable physicalActivity = new InputVariable();
    //private InputVariable medicamentation = new InputVariable();
    private OutputVariable CDR = new OutputVariable();
    private RuleBlock mamdani = new RuleBlock();

    public FuzzyCdrController() {

        engine.setName("Cardiovascular Disease Risk");
        engine.setDescription("");

        age.setName("Age");
        age.setDescription("");
        age.setEnabled(true);
        age.setRange(20.000, 100.000);
        age.setLockValueInRange(false);
        age.addTerm(new Triangle("Young", 20, 20, 34));
        age.addTerm(new Triangle("MidAge",  31, 40, 50));
        age.addTerm(new Triangle("Old", 45, 100, 100));
        engine.addInputVariable(age);

        BMI.setName("BMI");
        BMI.setDescription("");
        BMI.setEnabled(true);
        BMI.setRange(10.000, 50.000);
        BMI.setLockValueInRange(false);
        BMI.addTerm(new Triangle("Underweight", 10, 10, 19));
        BMI.addTerm(new Triangle("Normal", 17, 22, 26));
        BMI.addTerm(new Triangle("Overweight", 24, 50, 50));
        engine.addInputVariable(BMI);


        bloodPressure.setName("bloodPressure");
        bloodPressure.setDescription("");
        bloodPressure.setEnabled(true);
        bloodPressure.setRange(80.000, 200.000);
        bloodPressure.setLockValueInRange(false);
        bloodPressure.addTerm(new Triangle("Low", 80, 80, 130));
        bloodPressure.addTerm(new Triangle("Medium",  110, 142, 165));
        bloodPressure.addTerm(new Triangle("High", 135, 200, 200));
        engine.addInputVariable(bloodPressure);

//        physicalActivity.setName("physicalActivity");
//        physicalActivity.setDescription("");
//        physicalActivity.setEnabled(true);
//        physicalActivity.setRange(0.000, 1.000);
//        physicalActivity.setLockValueInRange(false);
//        physicalActivity.addTerm(new GaussianProduct("No", 1, 0, 0.05, 0.44));
//        physicalActivity.addTerm(new GaussianProduct("Yes",  0.05, 0.55, 0.01, 1));
//        engine.addInputVariable(physicalActivity);
//
//        medicamentation.setName("medicamentation");
//        medicamentation.setDescription("");
//        medicamentation.setEnabled(true);
//        medicamentation.setRange(0.000, 1.000);
//        medicamentation.setLockValueInRange(false);
//        medicamentation.addTerm(new GaussianProduct("No", 1, 0, 0.05, 0.44));
//        medicamentation.addTerm(new GaussianProduct("Yes",  0.05, 0.55, 0.01, 1));
//        engine.addInputVariable(medicamentation);

        CDR.setName("CDR"); //CardiovascularDiseaseRisk
        CDR.setDescription("");
        CDR.setEnabled(true);
        CDR.setRange(0.000, 100.000);
        CDR.setLockValueInRange(false);
        CDR.setAggregation(new Maximum());
        CDR.setDefuzzifier(new Centroid(100));
        CDR.setDefaultValue(Double.NaN);
        CDR.setLockPreviousValue(false);

        CDR.addTerm(new Triangle("Healthy", 0, 0, 33));
        CDR.addTerm(new Triangle("Mild", 24.83, 34.75, 49.38));
        CDR.addTerm(new Triangle("Moderate", 34.7, 49.8, 64.9));
        CDR.addTerm(new Triangle("Severe", 52.87, 69, 85.13));
        CDR.addTerm(new Triangle("VerySevere", 68, 100, 100));
        engine.addOutputVariable(CDR);


        mamdani.setName("mamdani");
        mamdani.setDescription("");
        mamdani.setEnabled(true);
        mamdani.setConjunction(new AlgebraicProduct());
        mamdani.setDisjunction(null);
        mamdani.setImplication(new Minimum());
        mamdani.setActivation(new General());

        mamdani.addRule(Rule.parse("if Age is Young and BMI is Underweight and bloodPressure is Low then CDR is Healthy", engine));
        mamdani.addRule(Rule.parse("if Age is Young and BMI is Underweight and bloodPressure is Medium then CDR is Healthy", engine));
        mamdani.addRule(Rule.parse("if Age is Young and BMI is Underweight and bloodPressure is High then CDR is Mild", engine));

        mamdani.addRule(Rule.parse("if Age is Young and BMI is Normal and bloodPressure is Low then CDR is Healthy", engine));
        mamdani.addRule(Rule.parse("if Age is Young and BMI is Normal and bloodPressure is Medium then CDR is Mild", engine));
        mamdani.addRule(Rule.parse("if Age is Young and BMI is Normal and bloodPressure is High then CDR is Mild", engine));

        mamdani.addRule(Rule.parse("if Age is Young and BMI is Overweight and bloodPressure is Low then CDR is Mild", engine));
        mamdani.addRule(Rule.parse("if Age is Young and BMI is Overweight and bloodPressure is Medium then CDR is Moderate", engine));
        mamdani.addRule(Rule.parse("if Age is Young and BMI is Overweight and bloodPressure is High then CDR is Moderate", engine));


        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Underweight and bloodPressure is Low then CDR is Healthy", engine));
        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Underweight and bloodPressure is Medium then CDR is Mild", engine));
        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Underweight and bloodPressure is High then CDR is Mild", engine));

        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Normal and bloodPressure is Low then CDR is Healthy", engine));
        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Normal and bloodPressure is Medium then CDR is Mild", engine));
        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Normal and bloodPressure is High then CDR is Moderate", engine));

        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Overweight and bloodPressure is Low then CDR is Moderate", engine));
        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Overweight and bloodPressure is Medium then CDR is Severe", engine));
        mamdani.addRule(Rule.parse("if Age is MidAge and BMI is Overweight and bloodPressure is High then CDR is VerySevere", engine));


        mamdani.addRule(Rule.parse("if Age is Old and BMI is Underweight and bloodPressure is Low then CDR is Mild", engine));
        mamdani.addRule(Rule.parse("if Age is Old and BMI is Underweight and bloodPressure is Medium then CDR is Mild", engine));
        mamdani.addRule(Rule.parse("if Age is Old and BMI is Underweight and bloodPressure is High then CDR is Moderate", engine));

        mamdani.addRule(Rule.parse("if Age is Old and BMI is Normal and bloodPressure is Low then CDR is Moderate", engine));
        mamdani.addRule(Rule.parse("if Age is Old and BMI is Normal and bloodPressure is Medium then CDR is Moderate", engine));
        mamdani.addRule(Rule.parse("if Age is Old and BMI is Normal and bloodPressure is High then CDR is Severe", engine));

        mamdani.addRule(Rule.parse("if Age is Old and BMI is Overweight and bloodPressure is Low then CDR is Severe", engine));
        mamdani.addRule(Rule.parse("if Age is Old and BMI is Overweight and bloodPressure is Medium then CDR is VerySevere", engine));
        mamdani.addRule(Rule.parse("if Age is Old and BMI is Overweight and bloodPressure is High then CDR is VerySevere", engine));

        engine.addRuleBlock(mamdani);
    }

    public double getOutput(double ageInput, double bmiInput, double bloodPressureInput) {

        StringBuilder status = new StringBuilder();

        if (!engine.isReady(status))
            throw new RuntimeException("[Engine error]:n" + status);

        age = engine.getInputVariable("Age");
        BMI = engine.getInputVariable("BMI");
        bloodPressure = engine.getInputVariable("bloodPressure");
        //physicalActivity = engine.getInputVariable("physicalActivity");
        //medicamentation = engine.getInputVariable("medicamentation");
        CDR = engine.getOutputVariable("CDR");

        age.setValue(ageInput);
        BMI.setValue(bmiInput);
        bloodPressure.setValue(bloodPressureInput);
        //physicalActivity.setValue(physicalActivityInput);
        //medicamentation.setValue(medicamentationInput);

        engine.process();

        System.out.println("Age: "+age.getValue() + "BMI: "+BMI.getValue() + "BP: "+ bloodPressure.getValue() + "CDR: "+CDR.getValue());

        return CDR.getValue();
    }
}
