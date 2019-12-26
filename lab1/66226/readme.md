Bartłomiej Urło I6E2S1 
nr albumu: 66226

Zadanie 9

Zadanie 9. Przedsiębiorstwo produkuje trzy wyroby. Do ich produkcji zużywa się m.in. dwa limitowane surowce. Zużycie tych surowców na jednostkę każdego z wyrobów, dopuszczalne limity zużycia oraz zyski jednostkowe ze sprzedaży podano w tabeli poniżej. Należy wyznaczyć takie ilości poszczególnych wyrobów, aby zysk był maksymalny.

Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.

Model matematyczny X1 – wielkość produkcji wyrobu w1 X2 – wielkość produkcji wyrobu w2 X3 – wielkość produkcji wyrobu w3

Wyznaczyć max z: 10x1 + 24x2 + 12x3
5x1 + 3x2 + 0x3 <=3600
X1 + 2x2 + 4x3 <=4800
X1, x2, x3 >= 0

Wykorzystane biblioteki

ojalgo guava

Kod programu:

import com.google.common.collect.ImmutableList; import org.ojalgo.optimisation.Expression; import org.ojalgo.optimisation.ExpressionsBasedModel; import org.ojalgo.optimisation.Optimisation; import org.ojalgo.optimisation.Variable;

import java.math.BigDecimal; import java.util.List;

public class Zadanie {

private static List makeVariables(){ ImmutableList.Builder result=ImmutableList.builder(); result.add(new Variable("X1").weight(10)); result.add(new Variable("X2").weight(24)); result.add(new Variable("X3").weight(12));

return result.build();
}

private static void printResult(Optimisation.Result result){ System.out.println("X1: "+result.get(0)); System.out.println("X2: "+result.get(1)); System.out.println("X3: "+result.get(2));

System.out.println("Revenue: " + result.getValue());
}

private static void solve(boolean IP){ List variables=makeVariables();

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
// These sets the variable bounds and constraints

for(Variable v:variables){
	Expression expression = model.addExpression("V_1_"+v.getName()).lower(0);
	expression.set(v, 1);
}


printResult(model.maximise());
}

public static void main(String[] args){ System.out.println("======= Solving the LP problem ======="); solve(false);

} }

Oczekiwany rezultat:

======= Solving the LP problem ======= 
x1 = 0.0
x2 = 1200.0 
x3 = 600.0 
Revenue = 36000.0
