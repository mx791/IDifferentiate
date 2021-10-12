package function;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

public class Div implements IDifferentiable {

    private IDifferentiable denominator;
    private IDifferentiable numerator;

    public Div(IDifferentiable numerator, IDifferentiable denominator) {
        this.denominator = denominator;
        this.numerator = numerator;
    }

    public double compute(VariableBag parameters) {
        return this.numerator.compute(parameters) / this.denominator.compute(parameters);
    }

    public IDifferentiable derivate(String variableName) {
        IDifferentiable num = new Sum(
                new Multiply(this.numerator.derivate(variableName), this.denominator),
                new Multiply(this.numerator, this.denominator.derivate(variableName))
                );
        return new Div(num, new Multiply(this.denominator, this.denominator));
    }

    public String getExpression() {
        return "(" + this.numerator.getExpression() + ") / (" + this.denominator.getExpression() + ")";
    }

    public IDifferentiable simplify() {
        this.denominator = this.denominator.simplify();
        this.numerator = this.numerator.simplify();
        return this;
    }

    public Set<String> getRequiredVariables() {
        HashSet<String> variables = new HashSet<String>();
        variables.addAll(this.denominator.getRequiredVariables());
        variables.addAll(this.numerator.getRequiredVariables());

        return variables;
    }
}
