package function;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Minus implements IDifferentiable {

    private IDifferentiable u;
    private IDifferentiable v;

    public Minus(IDifferentiable fc) {
        this.u = new Constant(0);
        this.v = fc;
    }

    public Minus(IDifferentiable u, IDifferentiable v) {
        this.u = u;
        this.v = v;
    }

    public double compute(VariableBag parameters) {
        return this.u.compute(parameters) - this.v.compute(parameters);
    }

    public IDifferentiable derivate(String variableName) {
        return new Minus(this.u.derivate(variableName), this.v.derivate(variableName));
    }

    public String getExpression() {
        return this.u.getExpression() + " - " + this.v.getExpression();
    }

    public IDifferentiable simplify() {
        this.u = u.simplify();
        this.v = this.v.simplify();
        return this;
    }

    public Set<String> getRequiredVariables() {
        HashSet<String> variables = new HashSet<String>();
        variables.addAll(this.u.getRequiredVariables());
        variables.addAll(this.v.getRequiredVariables());
        return variables;
    }
}
