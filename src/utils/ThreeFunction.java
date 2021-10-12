package utils;

import function.Constant;
import function.IDifferentiable;
import function.Sum;

import java.util.ArrayList;

public class ThreeFunction {

    public IDifferentiable[] subTerms;

    public ThreeFunction(IDifferentiable a, IDifferentiable b) {
        this.subTerms = new IDifferentiable[]{ a, b };
    }

    public ThreeFunction(IDifferentiable[] terms) {
        this.subTerms = terms;
    }

    protected String toString(String separator) {
        String ret = "";
        for (IDifferentiable subFc : this.subTerms) {
            String prefix = (ret.length() != 0 ? " " + separator + " " : "");
            String term = (subFc instanceof ThreeFunction) ? "(" + subFc.getExpression() + ")" : subFc.getExpression();
            ret += prefix + term;
        }
        return ret;
    }

}
