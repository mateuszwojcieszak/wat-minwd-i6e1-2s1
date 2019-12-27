import com.joptimizer.exception.JOptimizerException;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;

public class Main {
    public static void main(String[] args) {
        LinearMultivariateRealFunction objectiveFunction = new LinearMultivariateRealFunction(new double[]{0.3,0.2},0);

        ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[4];
        // x1 >= 0
        inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1.0,0.00}, 0.0);  // focus: -x1 <= 0
        // x2 >= 0
        inequalities[1] = new LinearMultivariateRealFunction(new double[]{0.0, -1.00}, 0.0);  // focus: -x2+0 <= 0
        // x1*7+x2*3>=2100
        inequalities[2] = new LinearMultivariateRealFunction(new double[]{-7.00,-3.00}, 2100.0); // focus: -7x1-3x2+2100 <= 0
        // x2>=1200
        inequalities[3] = new LinearMultivariateRealFunction(new double[]{0.0,-2.00}, 1200.0);// focus: -2x2+1200 <= 0

        //optimization problem
        OptimizationRequest optimizationRequest = new OptimizationRequest();
        optimizationRequest.setF0(objectiveFunction);
        optimizationRequest.setFi(inequalities);
        optimizationRequest.setToleranceFeas(1.e-9);
        optimizationRequest.setTolerance(1.e-9);

        JOptimizer optimizer = new JOptimizer();
        optimizer.setOptimizationRequest(optimizationRequest);
        try {
            optimizer.optimize();
        } catch (JOptimizerException e) {
            e.printStackTrace();
        }

        double[] solution = optimizer.getOptimizationResponse().getSolution();
        for (int i=0; i<solution.length/2; i++){
            System.out.println( "X1: " + Math.round(solution[i]) + "\tX2: " + Math.round(solution[i+1]) );
        }

        System.out.println("Y =" + (0.3d*Math.round(solution[0])+0.2d*Math.round(solution[1])));

    }
}