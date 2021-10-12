package function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VariableBag {

    private Map<String, Double> variablesValues;

    public VariableBag() {
        this.variablesValues = new HashMap<>();
    }

    public VariableBag(Map<String, Double> iniValues) {
        this.variablesValues = iniValues;
    }

    public VariableBag setValue(String variableName, double value) {
        this.variablesValues.put(variableName, value);
        return this;
    }

    public double getValue(String variableName) {
        return this.variablesValues.get(variableName);
    }

    public Set<String> getKeys() {
        return this.variablesValues.keySet();
    }

}
