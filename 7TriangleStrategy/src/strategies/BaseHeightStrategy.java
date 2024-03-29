public class BaseHeightStrategy implements AreaCalculationStrategy {
    private double base, height;

    public BaseHeightStrategy(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return (base * height) / 2;
    }
}
