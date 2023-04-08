package geometry;

import java.util.StringJoiner;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values. Each point also has a unique name, which
 * is a <code>String</code> value.
 */
public class Point {

    public final double x, y;
    public final String name;

    public Point(String name, double x, double y) {
        this.name = name;
        this.x    = x;
        this.y    = y;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "(", ")").add(name).add(Double.toString(x)).add(Double.toString(y)).toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        if (Double.compare(point.y, y) != 0) return false;
        return name.equals(point.name);
    }

    //Student Added Functions
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public Point rotatePoint(Point center, double angle) {
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));
        double dx = x - center.x;
        double dy = y - center.y;
        double newX = center.x + cos * dx - sin * dy;
        double newY = center.y + sin * dx + cos * dy;
        return new Point(name, newX, newY);
    }
    public double distance(Point other){
        double y_distance = (other.y - this.y);
        double y_squared = Math.pow(y_distance, 2);

        double x_distance = (other.x - this.x);
        double x_squared = Math.pow(x_distance, 2);

//        System.out.println("X Distance:  " + x_distance);
//        System.out.println("X Squared:  " + x_squared);
//        System.out.println("Y Distance:  " + y_distance);
//        System.out.println("Y Squared:  " + y_squared);
//        System.out.println("Distance Added: " + (x_squared+y_squared));
//        System.out.println("Distance SQRT: " + Math.sqrt(x_squared+y_squared));
        double distance  = Math.sqrt((x_squared + y_squared));
        return distance;
    }
    public Point translatePoint(double x_translate, double y_translate) {
        double newX = x + x_translate;
        double newY = y + y_translate;
        return new Point(name, newX, newY);
    }
    public Point round(){
         double x1 = Math.round(x * 100.0) / 100.0;
         double y1 = Math.round(y * 100.0) / 100.0;
         return new Point(name, x1, y1);
    }
}

