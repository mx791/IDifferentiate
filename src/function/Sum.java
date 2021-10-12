package function;

import utils.FormuleBuilder;
import utils.ThreeFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Sum extends ThreeFunction implements IDifferentiable  {

    public Sum(IDifferentiable a, IDifferentiable b) {
        super(a, b);
        this.simplifySums();
        this.simplifyConstants();
    }

    public Sum(IDifferentiable[] terms) {
        super(terms);
        this.simplifySums();
        this.simplifyConstants();
    }

    public double compute(VariableBag parameters) {
        double sum = 0;
        for (IDifferentiable subFunction : this.subTerms) {
            sum += subFunction.compute(parameters);
        }
        return sum;
    }

    public IDifferentiable derivate(String variableName) {
        FormuleBuilder fb = FormuleBuilder.create(this.subTerms[0].derivate(variableName));
        for (int ind=1; ind<this.subTerms.length; ind++) {
            fb.add(this.subTerms[ind].derivate(variableName));
        }
        return fb.getExpression();
    }

    public String getExpression() {
        String ret = "";
        for (IDifferentiable subFc : this.subTerms) {
            String prefix = (ret.length() != 0 ? " + " : "");
            String term = (subFc instanceof ThreeFunction) ? "(" + subFc.getExpression() + ")" : subFc.getExpression();
            ret += prefix + term;
        }
        return ret;
    }

    public IDifferentiable simplify() {
        this.simplifySums();
        this.simplifyConstants();
        if (this.subTerms.length == 1) {
            return this.subTerms[0];
        }
        return this.subTerms.length == 0 ? null : this;
    }

    private void simplifyConstants() {
        double constantSum = 0;
        ArrayList<IDifferentiable> finalList = new ArrayList<>();
        for (IDifferentiable function : this.subTerms) {
            function = function.simplify();
            if (function instanceof Constant) {
                constantSum += function.compute(null);
            } else {
                finalList.add(function);
            }
        }
        if (constantSum != 0) {
            finalList.add(0, new Constant(constantSum));
        }
        this.subTerms = new IDifferentiable[finalList.size()];
        this.subTerms = (IDifferentiable[]) finalList.toArray(this.subTerms);
    }

    private void simplifySums() {
        ArrayList<IDifferentiable> finalList = new ArrayList<>();
        for (IDifferentiable function : this.subTerms) {
            if (function instanceof Sum) {
                for (IDifferentiable subFunction : ((Sum) function).subTerms) {
                    finalList.add(subFunction);
                }
            } else {
                finalList.add(function);
            }
        }
        this.subTerms = new IDifferentiable[finalList.size()];
        this.subTerms = (IDifferentiable[]) finalList.toArray(this.subTerms);
    }

    public Set<String> getRequiredVariables() {
        HashSet<String> variables = new HashSet<String>();
        for (IDifferentiable fc : this.subTerms) {
            variables.addAll(fc.getRequiredVariables());
        }
        return variables;
    }
}
