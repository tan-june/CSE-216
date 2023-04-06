package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            throw new NullPointerException("Provided Input is not a Square!");
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
        double centerX = center().getX();
        double centerY = center().getY();
        translateBy(-centerX, -centerY);
        List<Point> PointSort = points;
        Point center = center();

        Collections.sort(PointSort, (p1, p2) -> {
            p1 = p1.round();
            p2 = p2.round();
            double angle1 = Math.toDegrees(Math.atan2(p1.getY() - centerY, p1.getX() - centerX));
            double angle2 = Math.toDegrees(Math.atan2(p2.getY() - centerY, p2.getX() - centerX));
            if (angle1 < 0) {
                angle1 += 360;
            }
            if (angle2 < 0) {
                angle2 += 360;
            }
            return Double.compare(angle1, angle2);
        });
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < PointSort.size(); i++) {
            Point point = PointSort.get(i);
            sb.append("(");
            sb.append(point.name);
            sb.append(", ");
            sb.append(point.getX());
            sb.append(", ");
            sb.append(point.getY());
            sb.append(")");
            if (i < PointSort.size() - 1) {
                sb.append("; ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String... args) {
        Point east = new Point("A", 10, 10);
        Point west = new Point("B", -10, 10);
        Point north = new Point("C", -10, -10);
        Point south = new Point("D", 10, -10);
        Point toofarsouth = new Point("south", 0, -2);

        System.out.println("Square Testing");
        //Print Square @ Origin -> ABCD
        Shape lonely = new Square(east, west, north, south);
        System.out.println(lonely);
        System.out.println(lonely.center());
        System.out.println();
        //Square Testing
        //[(A, 10.0, 10.0); (B, -10.0, 10.0); (C, -10.0, -10.0); (D, 10.0, -10.0)]
        //(Center of Square, 0.0, 0.0)

        lonely = lonely.translateBy(100,100);
        System.out.println("Print Square Not at Origin");
        System.out.println(lonely);
        System.out.println(lonely.center());
        System.out.println();
        lonely = lonely.translateBy(-100,-100);
        //Print Square Not at Origin
        //[(A, 110.0, 110.0); (B, 90.0, 110.0); (C, 90.0, 90.0); (D, 110.0, 90.0)]
        //(Center of Square, 100.0, 100.0)


        //To String Testing
        System.out.println("toString Testing -> Should always be ABCD");
        Shape lonely1 = lonely.translateBy(90,90);
        System.out.println(lonely1);
        Shape lonely2 = lonely1.translateBy(-15,15);
        System.out.println(lonely2);
        Shape lonely3 = lonely2.translateBy(100,-115);
        System.out.println(lonely3);
        System.out.println();
        //toString Testing -> Should always be ABCD
        //[(A, 100.0, 100.0); (B, 80.0, 100.0); (C, 80.0, 80.0); (D, 100.0, 80.0)]
        //[(A, 85.0, 115.0); (B, 65.0, 115.0); (C, 65.0, 95.0); (D, 85.0, 95.0)]
        //[(A, 185.0, 0.0); (B, 165.0, 0.0); (C, 165.0, -20.0); (D, 185.0, -20.0)]

        //Translation Testing
        Point a1 = new Point("A", 20, 20);
        Point b1 = new Point("B", -20, 20);
        Point c1 = new Point("C", -20, -20);
        Point d1 = new Point("D", 20, -20);
        Shape translator = new Square(a1,b1,c1,d1);
        translator = translator.translateBy(-100,100);
        System.out.println("\n" + "Translation Tests -> Should always be ABCD");
        System.out.println("Original: " + translator);
        translator = translator.translateBy(1000,-1000);
        System.out.println("          " + translator);
        translator = translator.translateBy(-1000,1000);
        System.out.println("          " + translator);
        System.out.println();
        //Translation Tests -> Should always be ABCD
        //Original: [(A, -80.0, 120.0); (B, -120.0, 120.0); (C, -120.0, 80.0); (D, -80.0, 80.0)]
        //          [(A, 920.0, -880.0); (B, 880.0, -880.0); (C, 880.0, -920.0); (D, 920.0, -920.0)]
        //          [(A, -80.0, 120.0); (B, -120.0, 120.0); (C, -120.0, 80.0); (D, -80.0, 80.0)]

        //Rotation Test
        Shape rotator = new Square(a1,b1,c1,d1);
        System.out.println("\n" + "Rotate Tests");
        System.out.println("Original: " + rotator);
        rotator = rotator.rotateBy(90);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(90);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(90);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(90);
        System.out.println("          " + rotator);
        System.out.println();
        //Rotate Tests
        //Original: [(A, 20.0, 20.0); (B, -20.0, 20.0); (C, -20.0, -20.0); (D, 20.0, -20.0)]
        //          [(D, 20.0, 20.0); (A, -20.0, 20.0); (B, -20.0, -20.0); (C, 20.0, -20.0)]
        //          [(C, 20.0, 20.0); (D, -20.0, 20.0); (A, -20.0, -20.0); (B, 20.0, -20.0)]
        //          [(B, 20.0, 20.0); (C, -20.0, 20.0); (D, -20.0, -20.0); (A, 20.0, -20.0)]
        //          [(A, 20.0, 20.0); (B, -20.0, 20.0); (C, -20.0, -20.0); (D, 20.0, -20.0)]

        //Rotation Test 2
        System.out.println("\n" + "Rotate Tests 2");
        rotator = rotator.translateBy(50,-50);
        System.out.println("Original: " + rotator);
        rotator = rotator.rotateBy(90);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(270);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(-180);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(-180);
        System.out.println("          " + rotator);
        System.out.println();
        //Rotate Tests 2
        //Original: [(A, 70.0, -30.0); (B, 30.0, -30.0); (C, 30.0, -70.0); (D, 70.0, -70.0)]
        //          [(D, 70.0, -30.0); (A, 30.0, -30.0); (B, 30.0, -70.0); (C, 70.0, -70.0)]
        //          [(A, 70.0, -30.0); (B, 30.0, -30.0); (C, 30.0, -70.0); (D, 70.0, -70.0)]
        //          [(C, 70.0, -30.0); (D, 30.0, -30.0); (A, 30.0, -70.0); (B, 70.0, -70.0)]
        //          [(A, 70.0, -30.0); (B, 30.0, -30.0); (C, 30.0, -70.0); (D, 70.0, -70.0)]

        //Rotation Test 3
        System.out.println("\n" + "Rotate Tests 3");
        rotator = rotator.translateBy(10000,10000);
        System.out.println("Original: " + rotator);
        rotator = rotator.rotateBy(-90);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(-90);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(-90);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(-90);
        System.out.println("          " + rotator);
        rotator = rotator.rotateBy(360);
        System.out.println("          " + rotator);
        //Rotate Tests 3
        //Original: [(A, 10070.0, 9970.0); (B, 10030.0, 9970.0); (C, 10030.0, 9930.0); (D, 10070.0, 9930.0)]
        //          [(B, 10070.0, 9970.0); (C, 10030.0, 9970.0); (D, 10030.0, 9930.0); (A, 10070.0, 9930.0)]
        //          [(C, 10070.0, 9970.0); (D, 10030.0, 9970.0); (A, 10030.0, 9930.0); (B, 10070.0, 9930.0)]
        //          [(D, 10070.0, 9970.0); (A, 10030.0, 9970.0); (B, 10030.0, 9930.0); (C, 10070.0, 9930.0)]
        //          [(A, 10070.0, 9970.0); (B, 10030.0, 9970.0); (C, 10030.0, 9930.0); (D, 10070.0, 9930.0)]
        //          [(A, 10070.0, 9970.0); (B, 10030.0, 9970.0); (C, 10030.0, 9930.0); (D, 10070.0, 9930.0)]

        System.out.println("\n" + "Illegal Argument Exceptions");
        Shape nope = new Square(north, toofarsouth, east, west);
    }
    }
