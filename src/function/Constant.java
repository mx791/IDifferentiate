package function;

import java.util.HashSet;
import java.util.Set;

public class Constant implements IDifferentiable {

    private double value;

    public Constant(double value) {
        this.value = value;
    }

    public double compute(VariableBag parameters) {
        return this.value;
    }

    public IDifferentiable derivate(String variableName) {
       return new Constant(0);
    }

    public String getExpression() {
        return this.value + "";
    }

    public IDifferentiable simplify() {
        return this;
    }

    public Set<String> getRequiredVariables() {
        return new HashSet<String>();
    }
}
