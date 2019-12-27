import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class Optimization {
    public static void main( String[] args ) {
        final ExpressionsBasedModel model = new ExpressionsBasedModel();
        final Variable a = Variable.make("p1").lower(0).upper(Double.POSITIVE_INFINITY).weight(1.2);
        final Variable b = Variable.make("p2").lower(0).upper(Double.POSITIVE_INFINITY).weight(1.8);
        final Variable c = Variable.make("p3").lower(0).upper(Double.POSITIVE_INFINITY).weight(2.0);
        final Variable d = Variable.make("p4").lower(0).upper(Double.POSITIVE_INFINITY).weight(0.9);
        model.addVariable(a);
        model.addVariable(b);
        model.addVariable(c);
        model.addVariable(d);
        final Expression limitationOne = model.addExpression("Pierwsze ograniczenie: ").lower(120).upper(Double.POSITIVE_INFINITY);
        limitationOne.set(a, 6).set(b, 3).set(c, 4).set(d, 4);
        final Expression limitationTwo = model.addExpression("Drugie ograniczenie").lower(60).upper(Double.POSITIVE_INFINITY);
        limitationTwo.set(a, 1).set(b, 3).set(c, 2).set(d, 4);
        a.integer(true);
        b.integer(true);
        c.integer(true);
        d.integer(true);
        Optimisation.Result result = model.minimise();
        System.out.println("Rozwiązanie: " + result.toString());
        System.out.println();
        System.out.println(model.toString());
    }
}
