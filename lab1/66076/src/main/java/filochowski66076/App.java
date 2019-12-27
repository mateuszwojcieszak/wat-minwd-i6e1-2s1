package filochowski66076;

import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class App
{
    public static void main( String[] args ) {
        System.out.println("\n\n\n\n\n Part a");
        partA();
        System.out.println("\n\n\n\n\n Part b");
        partB();
    }


    public static void partA(){
        final ExpressionsBasedModel model = new ExpressionsBasedModel();
        final Variable a = Variable.make("x1").lower(0).upper(Double.POSITIVE_INFINITY).weight(1.2);
        final Variable b = Variable.make("x2").lower(0).upper(Double.POSITIVE_INFINITY).weight(1.8);
        model.addVariable(a);
        model.addVariable(b);
        final Expression limitationOne = model.addExpression("Ograniczenie 1").lower(120).upper(Double.POSITIVE_INFINITY);
        limitationOne.set(a, 6).set(b, 3);
        final Expression limitationTwo = model.addExpression("Ograniczenie 2").lower(60).upper(Double.POSITIVE_INFINITY);
        limitationTwo.set(a, 1).set(b, 3);
        final Expression limitationThree = model.addExpression("Ograniczenie 3").lower(36).upper(Double.POSITIVE_INFINITY);
        limitationThree.set(a, 9).set(b, 1);
        final Expression limitationFour = model.addExpression("Ograniczenie 4").lower(180).upper(Double.POSITIVE_INFINITY);
        limitationFour.set(a, 6).set(b, 6);
        a.integer(true);
        b.integer(true);
        Optimisation.Result result = model.minimise();
        System.out.println(result.toString());
        System.out.println(model.toString());
    }

    public static void partB() {
        final ExpressionsBasedModel model = new ExpressionsBasedModel();
        final Variable a = Variable.make("x1").lower(0).upper(Double.POSITIVE_INFINITY).weight(1.2);
        final Variable b = Variable.make("x2").lower(0).upper(Double.POSITIVE_INFINITY).weight(1.8);
        model.addVariable(a);
        model.addVariable(b);
        final Expression limitationOne = model.addExpression("Ograniczenie 1").lower(120).upper(240);
        limitationOne.set(a, 6).set(b, 3);
        final Expression limitationTwo = model.addExpression("Ograniczenie 2").lower(60).upper(Double.POSITIVE_INFINITY);
        limitationTwo.set(a, 1).set(b, 3);
        final Expression limitationThree = model.addExpression("Ograniczenie 3").lower(36).upper(Double.POSITIVE_INFINITY);
        limitationThree.set(a, 9).set(b, 1);
        final Expression limitationFour = model.addExpression("Ograniczenie 4").lower(180).upper(Double.POSITIVE_INFINITY);
        limitationFour.set(a, 6).set(b, 6);
        a.integer(true);
        b.integer(true);
        Optimisation.Result result = model.minimise();
        System.out.println(result.toString());
        System.out.println(model.toString());
    }
}
