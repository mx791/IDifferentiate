package function;

import java.util.HashSet;
import java.util.Set;

public class Variable implements IDifferentiable {

    private String name;

    public Variable(String variable) {
        this.name = variable;
    }

    public double compute(VariableBag parameters) {
        return parameters.getValue(this.name);
    }

    public IDifferentiable derivate(String variableName) {
        return this.name == variableName ? new Constant(1) : new Constant(0);
    }

    public String getExpression() {
        return this.name;
    }

    public IDifferentiable simplify() {
        return this;
    }

    public Set<String> getRequiredVariables() {
        HashSet<String> variables = new HashSet<String>();
        variables.add(this.name);
        return variables;
    }
}
