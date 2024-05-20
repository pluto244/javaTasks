package shapes;

import java.awt.Graphics;

public class Triangle implements Shape {
    private int[] xPoints, yPoints;

    public Triangle(int x, int y, int base, int height) {
        this.xPoints = new int[]{x, x + base / 2, x - base / 2};
        this.yPoints = new int[]{y, y - height, y - height};
    }

    @Override
    public void draw(Graphics g) {
        g.drawPolygon(xPoints, yPoints, 3);
    }

    public int[] getXPoints() {
        return xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
    }
}
