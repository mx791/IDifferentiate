package utils;

import function.*;

public class FormuleBuilder {

    private IDifferentiable expression;

    public FormuleBuilder(IDifferentiable expression) {
        this.expression = expression;
    }

    public static FormuleBuilder create(IDifferentiable expression) {
        return new FormuleBuilder(expression);
    }

    public FormuleBuilder add(IDifferentiable expression) {
        this.expression = new Sum(this.expression, expression);
        return this;
    }
    public FormuleBuilder add(double value) {
        this.expression = new Sum(this.expression, new Constant(value));
        return this;
    }
    public FormuleBuilder add(String var) {
        this.expression = new Sum(this.expression, new Variable(var));
        return this;
    }

    public FormuleBuilder mul(IDifferentiable expression) {
        this.expression = new Multiply(this.expression, expression);
        return this;
    }

    public FormuleBuilder pow(IDifferentiable expression) {
        this.expression = new Pow(this.expression, expression);
        return this;
    }

    public FormuleBuilder pow(double value) {
        this.expression = new Pow(this.expression, new Constant(value));
        return this;
    }

    public IDifferentiable getExpression() {
        return this.expression;
    }
}
