package test;

import function.Constant;
import function.Minus;
import function.Variable;
import junit.framework.TestCase;
import optimizers.Minimize;
import utils.FormuleBuilder;

public class MinimizeTest extends TestCase {

    public void testMinimize() {
        Minimize m = new Minimize();
        FormuleBuilder fb = FormuleBuilder
                .create(new Minus(new Variable("x"), new Constant(2.5)))
                .pow(2.0)
                .add(FormuleBuilder.create(new Minus(new Variable("y"), new Constant(-2.5))).pow(2.0).getExpression());

        System.out.println(fb.getExpression().getExpression());
        m.minimize(fb.getExpression());

    }

    public void testMinimize2() {
        Minimize m = new Minimize();
        FormuleBuilder fb = FormuleBuilder
                .create(new Minus(new Variable("x"), new Constant(2.5)))
                .pow(2.0)
                .mul(FormuleBuilder.create(new Minus(new Variable("y"), new Constant(-2.5))).pow(2.0).getExpression());

        System.out.println(fb.getExpression().getExpression());
        m.minimize(fb.getExpression());

    }

}
