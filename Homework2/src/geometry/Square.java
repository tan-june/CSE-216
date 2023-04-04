package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Square extends Shape {

    private Point a;
    private Point b;
    private Point c;
    private Point d;
    Point center;
    private List<Point> points;

    //Constructor based on 4 points in a Square
    public Square(Point a, Point b, Point c, Point d) {
        double DistanceAtoB = a.distance(b);
        double DistanceBtoC = b.distance(c);
        double DistanceCtoD = c.distance(d);
        double DistanceDtoA = d.distance(a);
        double DistanceAtoC = a.distance(c);
        double DistanceBtoD = b.distance(d);
        if (DistanceAtoB != DistanceBtoC || DistanceAtoB != DistanceCtoD || DistanceAtoB != DistanceDtoA || DistanceAtoC != DistanceBtoD) {
            throw new NullPointerException("Input Given is not A Square!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        points = new ArrayList<>();
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        center = center();
    }

    public Point getA(){
        return a;
    }

    //Finding the Center of the Square
    @Override
    public Point center() {
        double x = (a.getX() + c.getX()) / 2;
        double y = (a.getY()+ c.getY()) / 2;
        return new Point("Center of Square", x, y);
    }

    //Rotate by Degrees -> Work Done in the Point.Java Class and Called and stored and returns new square
    @Override
    public Square rotateBy(int degrees) {
        Point centerofSquare = center();

        Point rotateA = a.rotatePoint(centerofSquare, degrees);
        Point rotateB = b.rotatePoint(centerofSquare, degrees);
        Point rotateC = c.rotatePoint(centerofSquare, degrees);
        Point rotateD = d.rotatePoint(centerofSquare, degrees);

        return new Square(rotateA, rotateB, rotateC, rotateD);
    }

    @Override
    public Shape translateBy(double x, double y) {

        //translate Point A, B, C, D
        Point translatedA = a.translatePoint(x, y);
        Point translatedB = b.translatePoint(x, y);
        Point translatedC = c.translatePoint(x, y);
        Point translatedD = d.translatePoint(x, y);

        return new Square(translatedA, translatedB, translatedC, translatedD);
    }

    @Override
    public String toString() {
        List<Point> PointSort = new ArrayList<>(points);
        PointSort.sort((p1, p2) -> {
            double angle1 = Math.atan2(-p1.getY(), -p1.getX());
            double angle2 = Math.atan2(-p2.getY(), -p2.getX());
            return Double.compare(angle1, angle2);
        });
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < PointSort.size(); i++) {
            sb.append("(").append(PointSort.get(i).name).append(", ").append(PointSort.get(i).getX()).append(", ").append(PointSort.get(i).getY()).append(")");

            if (i < PointSort.size()-1) {
                sb.append("; ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String... args) {
        Point east = new Point("East", 1, 1);
        Point west = new Point("West", -1, 1);
        Point north = new Point("North", -1, -1);
        Point south = new Point("South", 1, -1);
        Point toofarsouth = new Point("south", 0, -2);
        //east -> north -> west -> south

        System.out.println("Square Testing");

        //Print Square
        Shape lonely = new Square(north, south, east, west);
        System.out.println(lonely);
        System.out.println(lonely.center());

        System.out.println("\n" + "Translation Tests");
        System.out.println(lonely);
        lonely = lonely.translateBy(50,50);
        System.out.println(lonely);
        lonely = lonely.translateBy(-50,-50);
        System.out.println(lonely);

        //Rotation Test
        System.out.println("\n" + "Rotate Tests");
        System.out.println("Original: " + lonely);
        lonely = lonely.rotateBy(90);
        System.out.println(lonely);
        lonely = lonely.rotateBy(90);
        System.out.println(lonely);
        lonely = lonely.rotateBy(90);
        System.out.println(lonely);
        lonely = lonely.rotateBy(90);
        System.out.println(lonely);
        System.out.println("New Center:" + lonely.center());

        System.out.println("\n" + "Illegal Argument Exceptions");
        Shape nope = new Square(north, toofarsouth, east, west);
    }
    }
