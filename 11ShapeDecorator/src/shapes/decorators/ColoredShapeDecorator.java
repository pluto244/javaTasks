package shapes.decorators;

import shapes.Shape;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Triangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class ColoredShapeDecorator extends ShapeDecorator {
    private Color borderColor;
    private Color fillColor;
    private boolean hasBorder;
    private int borderWidth;

    public ColoredShapeDecorator(Shape decoratedShape, Color borderColor, Color fillColor, boolean hasBorder, int borderWidth) {
        super(decoratedShape);
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.hasBorder = hasBorder;
        this.borderWidth = borderWidth;
    }

    @Override
    public void draw(Graphics g) {
        setFillColor(g);
        decoratedShape.draw(g);
        if (hasBorder) {
            setBorderColor(g);
        }
    }

    private void setBorderColor(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderWidth));
        decoratedShape.draw(g); // Перерисовываем фигуру, чтобы добавить границу
    }

    private void setFillColor(Graphics g) {
        g.setColor(fillColor);
        if (decoratedShape instanceof Circle) {
            Circle circle = (Circle) decoratedShape;
            g.fillOval(circle.getX(), circle.getY(), circle.getDiameter(), circle.getDiameter());
        } else if (decoratedShape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) decoratedShape;
            g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        } else if (decoratedShape instanceof Triangle) {
            Triangle triangle = (Triangle) decoratedShape;
            g.fillPolygon(triangle.getXPoints(), triangle.getYPoints(), 3);
        }
    }
}
