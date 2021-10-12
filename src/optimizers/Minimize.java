package optimizers;

import function.IDifferentiable;
import function.VariableBag;

import java.awt.image.renderable.ParameterBlock;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Minimize {

    private final int iterCount = 10;
    private final double learnRate = 0.03;

    public VariableBag minimize(IDifferentiable objectiveFunction) {
        Set<String> requiredVariables = objectiveFunction.getRequiredVariables();
        VariableBag valuesBag = new VariableBag(parametersValues(requiredVariables));
        Map<String, IDifferentiable> gradients = gradientMap(requiredVariables, objectiveFunction);

        for (int i=0; i<this.iterCount; i++) {
            double value = objectiveFunction.compute(valuesBag);
            System.out.println(value);
            Map<String, Double> gradient = this.gradientValues(gradients, valuesBag);
            this.updateValues(gradient, valuesBag);
        }

        return valuesBag;
    }

    public Map<String, IDifferentiable> gradientMap(Set<String> requiredVariables, IDifferentiable function) {
        HashMap<String, IDifferentiable> gradients = new HashMap<>();
        for (String key : requiredVariables) {
            gradients.put(key, function.derivate(key));
        }
        return gradients;
    }

    public Map<String, Double> gradientValues(Map<String, IDifferentiable> gradientFunctions, VariableBag currentValues) {
        HashMap<String, Double> gradients = new HashMap<>();
        for (String key : gradientFunctions.keySet()) {
            gradients.put(key, -gradientFunctions.get(key).compute(currentValues));
        }
        return gradients;
    }

    public void updateValues(Map<String, Double> gradients, VariableBag currentValues) {
        for (String key : gradients.keySet()) {
            currentValues.setValue(key, currentValues.getValue(key) + gradients.get(key) * this.learnRate);
        }
    }

    public Map<String, Double> parametersValues(Set<String> requiredVariables) {
        HashMap<String, Double> variables = new HashMap<>();
        for (String key : requiredVariables) {
            variables.put(key, getIniValue());
        }
        return variables;
    }

    public double getIniValue() {
        return 0.0;
    }
}
