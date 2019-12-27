import com.joptimizer.exception.JOptimizerException;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;

public class Main {
    public static void main(String[] args) {
        LinearMultivariateRealFunction objectiveFunction = new LinearMultivariateRealFunction(new double[]{-6,-3,-5,-2},0);

        ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[6];
        // x1 >= 0
        inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.00, 0.00, 0.00}, 0.0);  // focus: -x1+0 <= 0
        // x2 >= 0
        inequalities[1] = new LinearMultivariateRealFunction(new double[]{0.0, -1.00, 0.00,0.00}, 0.0);  // focus: -x2+0 <= 0
        // x3 >= 0
        inequalities[2] = new LinearMultivariateRealFunction(new double[]{0.0, 0.00, -1.00, 0.00}, 0.0); // focus: -x3+0 <= 0
        // x4 >= 0
        inequalities[3] = new LinearMultivariateRealFunction(new double[]{0.0, 0.00, 0.00, -1.00}, 0.0);// focus: -x4 +0 <= 0
        // 15x1 + 10x2 + 20x3 +19x4 <= 26 000
        inequalities[4] = new LinearMultivariateRealFunction(new double[]{15.00,10.00,20.00,19.00},-26000.00);// focus: 15x1+10x2+20x3+19x4-26000 <= 0
        // 9x1 + 3x2 + 5x3 +10x4 <= 100 000
        inequalities[5] = new LinearMultivariateRealFunction(new double[]{9.00,3.00,5.00,10.00},-100000.00);// focus: 9x1+3x2+5x3+10x4-100000 <= 0

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
        System.out.println("Length: " + solution.length);
        for (int i=0; i<solution.length/4; i++){
            System.out.println( "X1: " + Math.round(solution[i]) + "\tX2: " + Math.round(solution[i+1])+"\tX3: " + Math.round(solution[i+2]) + "\tX4: " + Math.round(solution[i+3]) );
        }

        System.out.println("Z =" + (6d*Math.round(solution[0])+3d*Math.round(solution[1])+5d*Math.round(solution[2])+2d*Math.round(solution[3])));

    }
}