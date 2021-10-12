package function;

import java.util.Set;

public interface IDifferentiable {
    public double compute(VariableBag parameters);
    public IDifferentiable derivate(String variableName);
    public String getExpression();
    public IDifferentiable simplify();
    public Set<String> getRequiredVariables();
}