package function;

import utils.ThreeFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Multiply extends ThreeFunction implements IDifferentiable {

    public Multiply(IDifferentiable a, IDifferentiable b) {
        super(a, b);
        this.simplifyProducts();
        this.simplifyConstants();
    }

    public Multiply(IDifferentiable[] terms) {
        super(terms);
        this.simplifyProducts();
        this.simplifyConstants();
    }

    public double compute(VariableBag parameters) {
        double product = 1;
        for (IDifferentiable fc : this.subTerms) {
            product *= fc.compute(parameters);
        }
        return product;
    }

    public IDifferentiable derivate(String variableName) {
        IDifferentiable result = this.subTerms[0];
        for (int i=1; i<this.subTerms.length; i++) {
            result = new Sum(
                    new Multiply(result.derivate(variableName), this.subTerms[i]),
                    new Multiply(result, this.subTerms[i].derivate(variableName))
                    );
        }
        return result;
    }

    public String getExpression() {
        return this.toString("*");
    }

    public IDifferentiable simplify() {
        this.simplifyProducts();
        this.simplifyConstants();
        if (this.subTerms.length == 0) {
            return new Constant(0);
        } else if (this.subTerms.length == 1) {
            return this.subTerms[0];
        }
        return this;
    }

    public Set<String> getRequiredVariables() {
        HashSet<String> variables = new HashSet<String>();
        for (IDifferentiable fc : this.subTerms) {
            variables.addAll(fc.getRequiredVariables());
        }
        return variables;
    }

    private void simplifyConstants() {
        double constantProduct = 1;
        ArrayList<IDifferentiable> finalList = new ArrayList<>();
        for (IDifferentiable function : this.subTerms) {
            function = function.simplify();
            if (function instanceof Constant) {
                constantProduct *= function.compute(null);
            } else if (function != null) {
                finalList.add(function);
            }
        }
        if (constantProduct != 1) {
            finalList.add(0, new Constant(constantProduct));
        }
        if (constantProduct != 0.0) {
            this.subTerms = new IDifferentiable[finalList.size()];
            this.subTerms = (IDifferentiable[]) finalList.toArray(this.subTerms);
        } else {
            this.subTerms = new IDifferentiable[0];
        }
    }

    private void simplifyProducts() {
        ArrayList<IDifferentiable> finalList = new ArrayList<>();
        for (IDifferentiable function : this.subTerms) {
            if (function instanceof Multiply) {
                for (IDifferentiable subFunction : ((Multiply) function).subTerms) {
                    finalList.add(subFunction);
                }
            } else {
                finalList.add(function);
            }
        }
        this.subTerms = new IDifferentiable[finalList.size()];
        this.subTerms = (IDifferentiable[]) finalList.toArray(this.subTerms);
    }


}
