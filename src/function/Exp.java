package function;

import java.util.HashSet;
import java.util.Set;

public class Exp implements IDifferentiable {

    private IDifferentiable subFunction;

    public Exp(IDifferentiable subFunction) {
        this.subFunction = subFunction;
    }

    public double compute(VariableBag parameters) {
        return Math.exp(this.subFunction.compute(parameters));
    }

    public IDifferentiable derivate(String variableName) {
        return new Multiply(
                this.subFunction.derivate(variableName),
                new Exp(this.subFunction));
    }


    public String getExpression() {
        return "exp(" + this.subFunction.getExpression() + ")";
    }

    public IDifferentiable simplify() {
        return this;
    }

    public Set<String> getRequiredVariables() {
        return this.subFunction.getRequiredVariables();
    }
}
