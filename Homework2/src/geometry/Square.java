package geometry;

import java.util.ArrayList;
import java.util.List;

public class Square extends Shape {

    private  Point a;
    private  Point b;
    private  Point c;
    private  Point d;
    Point center;
    private List<Point> points;

    //Constructor based on 4 points in a Square
    public Square(Point a, Point b, Point c, Point d) {
        double distanceAtoB = a.distance(b);
        double distanceBtoC = b.distance(c);
        double distanceCtoD = c.distance(d);
        double distanceDtoA = d.distance(a);
        double distanceAtoC = a.distance(c);
        double distanceBtoD = b.distance(d);

        double threshold = 0.000001;
        if ((Math.abs(distanceAtoB - distanceBtoC) > threshold) ||
                (Math.abs(distanceAtoB - distanceCtoD) > threshold) ||
                (Math.abs(distanceAtoB - distanceDtoA) > threshold) ||
                (Math.abs(distanceAtoC - distanceBtoD) > threshold))
        {
            throw new NullPointerException("Provided input is not a square!");
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

        sortPoints();
        this.a=points.get(0);
        this.b=points.get(1);
        this.c=points.get(2);
        this.d=points.get(3);

    }

    public void sortPoints() {
        Point center = center();
        double centerX = center.getX();
        double centerY = center.getY();

        points.sort((p1, p2) -> {
            p1 = p1.round();
            p2 = p2.round();
            double angle1 = Math.toDegrees(Math.atan2(p1.getY() - centerY, p1.getX() - centerX));
            double angle2 = Math.toDegrees(Math.atan2(p2.getY() - centerY, p2.getX() - centerX));
            if (angle1 < 0) {
                angle1 += 360.0;
            }
            if (angle2 < 0) {
                angle2 += 360;
            }
            return Double.compare(angle1, angle2);
        });
//        System.out.println(points);
    }

    public Point getA(){
        return a;
    }
    public Point getB(){
        return b;
    }
    public Point getC(){
        return c;
    }
    public Point getD(){
        return d;
    }

    //Finding the Center of the Square
    @Override
    public Point center() {
        double side1 = (a.x + b.x + c.x + d.x) / 4.0;
        double side2 = (a.y + b.y + c.y + d.y) / 4.0;
        return new Point("Center of Square", side1, side2);
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

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Square)) {
            return false;
        }
        Square other = (Square) obj;

        // Compare X and Y coordinates of each point using Math.abs and a threshold
        double threshold = 0.000001;
        boolean answer  = Math.abs(a.getX() - other.a.getX()) < threshold
                       && Math.abs(a.getY() - other.a.getY()) < threshold
                       && Math.abs(b.getX() - other.b.getX()) < threshold
                       && Math.abs(b.getY() - other.b.getY()) < threshold
                       && Math.abs(c.getX() - other.c.getX()) < threshold
                       && Math.abs(c.getY() - other.c.getY()) < threshold
                       && Math.abs(d.getX() - other.d.getX()) < threshold
                       && Math.abs(d.getY() - other.d.getY()) < threshold;
        return answer;
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

        points.sort((p1, p2) -> {
            p1 = p1.round();
            p2 = p2.round();
            double angle1 = Math.toDegrees(Math.atan2(p1.getY() - centerY, p1.getX() - centerX));
            double angle2 = Math.toDegrees(Math.atan2(p2.getY() - centerY, p2.getX() - centerX));
            if (angle1 < 0) {
                angle1 += 360.0;
            }
            if (angle2 < 0) {
                angle2 += 360;
            }
            return Double.compare(angle1, angle2);
        });
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < PointSort.size(); i++) {
            Point point = PointSort.get(i);
            sb.append(String.format("(%s, %.2f, %.2f)", point.name, point.x, point.y));
            if (i < PointSort.size() - 1) {
                sb.append("; ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Square verticalReflect() {
        Point center = center();

        Point tempA = new Point(a.name, a.x, 2*center.y- a.y);
        Point tempB = new Point(b.name, b.x, 2*center.y-b.y);
        Point tempC = new Point(c.name, c.x, 2*center.y-c.y);
        Point tempD = new Point(d.name, d.x, 2*center.y-d.y);

        return new Square(tempA, tempB, tempC, tempD);
    }

    public Square horizontalReflect() {
        Point center = center();

        Point tempA = new Point(a.name, 2 * center.x - a.x, a.y);
        Point tempB = new Point(b.name, 2 * center.x - b.x, b.y);
        Point tempC = new Point(c.name, 2 * center.x - c.x, c.y);
        Point tempD = new Point(d.name, 2 * center.x - d.x, d.y);

        return new Square(tempA, tempB, tempC, tempD);
    }

    public Square diagonalReflect() {
        Point center = center();
        Point tempA = new Point(a.name, a.y - center.y, a.x - center.x);
        Point tempB = new Point(b.name, b.y - center.y, b.x - center.x);
        Point tempC = new Point(c.name, c.y - center.y, c.x - center.x);
        Point tempD = new Point(d.name, d.y - center.y, d.x - center.x);

        Square reflected = new Square(tempA, tempB, tempC, tempD);

        Point finalA = new Point(a.name, center.y + tempA.x, center.x + tempA.y);
        Point finalB = new Point(b.name, center.y + tempB.x, center.x + tempB.y);
        Point finalC = new Point(c.name, center.y + tempC.x, center.x + tempC.y);
        Point finalD = new Point(d.name, center.y + tempD.x, center.x + tempD.y);

        return new Square(finalA, finalB, finalC, finalD);
    }

    public Square counterDiagonal(){
        Point center = center();
        Point tempA = new Point(a.name, -a.y - center.y, -a.x - center.x);
        Point tempB = new Point(b.name, -b.y - center.y, -b.x - center.x);
        Point tempC = new Point(c.name, -c.y - center.y, -c.x - center.x);
        Point tempD = new Point(d.name, -d.y - center.y, -d.x - center.x);

        Square reflected = new Square(tempA, tempB, tempC, tempD);

        Point finalA = new Point(a.name, center.y + tempA.x, center.x + tempA.y);
        Point finalB = new Point(b.name, center.y + tempB.x, center.x + tempB.y);
        Point finalC = new Point(c.name, center.y + tempC.x, center.x + tempC.y);
        Point finalD = new Point(d.name, center.y + tempD.x, center.x + tempD.y);

        return new Square(finalA, finalB, finalC, finalD);
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

        lonely = lonely.translateBy(100,100);
        System.out.println("Print Square Not at Origin");
        System.out.println(lonely);
        System.out.println(lonely.center());
        System.out.println();
        lonely = lonely.translateBy(-100,-100);


        //To String Testing
        System.out.println("toString Testing -> Should always be ABCD");
        Shape lonely1 = lonely.translateBy(90,90);
        System.out.println(lonely1);
        Shape lonely2 = lonely1.translateBy(-15,15);
        System.out.println(lonely2);
        Shape lonely3 = lonely2.translateBy(100,-115);
        System.out.println(lonely3);
        System.out.println();

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


        System.out.println("\n" + "Illegal Argument Exceptions");
        Shape nope = new Square(north, toofarsouth, east, west);
    }
    }
