# IDifferentiate

Différentiation automatique en Java, et de descente de gradient.

```
fc = new Exp(new Multiply(new Constant(2), new Variable("x")));
fc.getExpression() // exp(2.0 * x)
fc.derivate("x").getExpression()  // 2.0 * exp(2.0 * x)
```

## Optimisation via descente de gradient
```
Minimize m = new Minimize();
FormuleBuilder fb = FormuleBuilder
        .create(new Minus(new Variable("x"), new Constant(2.5)))
        .pow(2.0)
        .add(FormuleBuilder.create(new Minus(new Variable("y"), new Constant(-2.5))).pow(2.0).getExpression());
VariableBag optimalValues = m.minimize(fb.getExpression())
```
