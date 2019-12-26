import com.google.common.collect.ImmutableList;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

import java.math.BigDecimal;
import java.util.List;


public class Zadanie {


    private static List<Variable> makeVariables(){
        ImmutableList.Builder<Variable> result=ImmutableList.builder();
        result.add(new Variable("X1").weight(10));
        result.add(new Variable("X2").weight(24));
        result.add(new Variable("X3").weight(12));

        return result.build();
    }

    private static void printResult(Optimisation.Result result){
        System.out.println("X1: "+result.get(0));
        System.out.println("X2: "+result.get(1));
        System.out.println("X3: "+result.get(2));


        System.out.println("Revenue: " + result.getValue());
    }

    private static void solve(boolean IP){
        List<Variable> variables=makeVariables();

        final ExpressionsBasedModel model = new ExpressionsBasedModel();
        for(Variable v:variables){
            model.addVariable(v);
        }

        {
            Expression expression = model.addExpression("C1").upper(3600);
            expression.set(variables.get(0), 5);
            expression.set(variables.get(1), 3);
            expression.set(variables.get(2), 0);

        }

        {
            Expression expression = model.addExpression("C2").upper(4800);
            expression.set(variables.get(0), 1);
            expression.set(variables.get(1), 2);
            expression.set(variables.get(2), 4);

        }


//      These sets the variable bounds and constraints

		for(Variable v:variables){
			Expression expression = model.addExpression("V_1_"+v.getName()).lower(0);
			expression.set(v, 1);
		}


        printResult(model.maximise());
    }

    public static void main(String[] args){
        System.out.println("======= Solving the LP problem =======");
        solve(false);

    }
}
