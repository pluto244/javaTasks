import java.awt.Point;

public class TriangleAreaCalculator {
    private AreaCalculationStrategy strategy;

    public void setStrategy(AreaCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateArea() {
        if (strategy == null) {
            throw new IllegalStateException("Calculation strategy has not been set");
        }
        return strategy.calculateArea();
    }
}
