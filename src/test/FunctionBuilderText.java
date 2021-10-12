package test;

import function.*;
import junit.framework.TestCase;
import org.junit.Test;
import utils.FormuleBuilder;

public class FunctionBuilderText extends TestCase {

    @Test
    public void testSum() {
        FormuleBuilder fb = FormuleBuilder.create(new Constant(2)).add(new Variable("x"));
        String res = fb.getExpression().getExpression();
        System.out.println("f=" + res);
        assertEquals("2.0 + x", res);
        res = fb.getExpression().derivate("x").getExpression();
        assertEquals("1.0", res);

        fb = FormuleBuilder.create(new Variable("y")).add(new Variable("x"));
        res = fb.getExpression().getExpression();
        System.out.println(res);
        assertEquals("y + x", res);
        res = fb.getExpression().derivate("y").getExpression();
        assertEquals("1.0", res);

        fb = FormuleBuilder.create(new Variable("y")).add(new Variable("x"));
        res = fb.getExpression().getExpression();
        System.out.println(res);
        assertEquals("y + x", res);

        fb = FormuleBuilder.create(new Constant(2)).add(new Variable("x")).add(new Constant(3)).add(new Constant(1));
        res = fb.getExpression().getExpression();
        System.out.println(res);
        assertEquals("6.0 + x", res);
    }

    @Test
    public void testMul() {
        FormuleBuilder fb = FormuleBuilder.create(new Constant(2)).mul(new Variable("x")).mul(new Constant(2));
        String res = fb.getExpression().getExpression();
        System.out.println(res);
        assertEquals("4.0 * x", res);
        String der = fb.getExpression().derivate("x").getExpression();
        assertEquals("4.0", der);
    }

    @Test
    public void testExp() {
        IDifferentiable fc = new Exp(new Variable("x"));
        assertEquals("exp(x)", fc.derivate("x").getExpression());

        fc = new Exp(new Multiply(new Constant(2), new Variable("x")));
        assertEquals("2.0 * exp(2.0 * x)", fc.derivate("x").getExpression());
    }

    @Test
    public void testPow() {
        IDifferentiable fc = FormuleBuilder.create(new Variable("x")).pow(2).getExpression();
        assertEquals("2.0 * x", fc.derivate("x").getExpression());

        fc = FormuleBuilder.create(new Variable("x")).pow(3).getExpression();
        assertEquals("3.0 * (x) ^ (2.0)", fc.derivate("x").getExpression());
    }

}
