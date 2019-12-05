

import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;
import org.apache.log4j.BasicConfigurator;

/**
 * @author K.P.L.Kanchana
 */

public class Lab1 {

    public static void main(String[] args) throws Exception {

        // Objective function (plane)
        LinearMultivariateRealFunction objectiveFunction = new LinearMultivariateRealFunction(new double[] {1.2, 1.8}, 0); //minimize 3x+4y

        //inequalities (polyhedral feasible set G.X<H )
        ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[4];
        // 6x=3y>=120
        inequalities[0] = new LinearMultivariateRealFunction(new double[]{-6.0, -3.0}, 120.0);  // focus: -6x-3y+120 <= 0
        // x+3y>=60
        inequalities[1] = new LinearMultivariateRealFunction(new double[]{-1.0, -3.00}, 60.0);  // focus: -x-3y+60
        // 9x+y >= 36
        inequalities[2] = new LinearMultivariateRealFunction(new double[]{-9.0, -1.00}, 36.0); // focus: -9x-y+36 <= 0
        // 6x+6y >= 180
        inequalities[3] = new LinearMultivariateRealFunction(new double[]{-6.0, -6.00}, 180.0);// focus: -6x-6y+180 <= 0

        //optimization problem
        OptimizationRequest or = new OptimizationRequest();
        or.setF0(objectiveFunction);
        or.setFi(inequalities);
        //or.setInitialPoint(new double[] {0.0, 0.0});//initial feasible point, not mandatory
        or.setToleranceFeas(1.E-9);
        or.setTolerance(1.E-9);

        //optimization
        JOptimizer opt = new JOptimizer();
        opt.setOptimizationRequest(or);
        opt.optimize();

        double[] sol = opt.getOptimizationResponse().getSolution();

        System.out.println("Length: " + sol.length);
        for (int i=0; i<sol.length/2; i++){
            System.out.println( "X" + (i+1) + ": " + Math.round(sol[i]) + "\ty" + (i+1) + ": " + Math.round(sol[i+1]) );
        }
    }

}
