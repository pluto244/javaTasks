import java.awt.Point;

public class VertexCoordinatesStrategy implements AreaCalculationStrategy {
    private Point a, b, c;

    public VertexCoordinatesStrategy(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculateArea() {
        return Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2.0;
    }
}
