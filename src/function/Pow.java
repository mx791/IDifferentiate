package function;

import utils.FormuleBuilder;

import java.util.HashSet;
import java.util.Set;

public class Pow implements IDifferentiable {

    private IDifferentiable u;
    private IDifferentiable v;

    public Pow(IDifferentiable u, IDifferentiable v) {
        this.u = u;
        this.v = v;
    }

    public double compute(VariableBag parameters) {
        return Math.pow(this.u.compute(parameters), this.v.compute(parameters));
    }

    public IDifferentiable derivate(String variableName) {
        FormuleBuilder fb = FormuleBuilder.create(this.v).mul(this.u.derivate(variableName)).mul(
                new Pow(this.u,
                        FormuleBuilder.create(this.v).add(new Minus(new Constant(1.0))).getExpression()
                )
        );
        return fb.getExpression();
    }

    public String getExpression() {
        return "(" + this.u.getExpression() + ") ^ (" + this.v.getExpression() + ")";
    }

    public IDifferentiable simplify() {
        this.u = u.simplify();
        this.v = v.simplify();

        if (this.v instanceof Constant && this.v.compute(null) == 1) {
            return this.u;
        }

        if (this.v instanceof Constant && this.v.compute(null) == 0) {
            return new Constant(0);
        }

        return this;
    }

    public Set<String> getRequiredVariables() {
        HashSet<String> variables = new HashSet<String>();
        variables.addAll(this.u.getRequiredVariables());
        variables.addAll(this.v.getRequiredVariables());
        return variables;
    }

}
