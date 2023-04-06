package geometry;

import java.util.*;

public class RadialGraph extends Shape {
    private final Point center;
    private final List<Point> neighbors;

    public RadialGraph(Point center, List<Point> neighbors) {
        this.center = center;
        this.neighbors = neighbors;

        if(neighbors.size()>0){ //account for only center case as the other constructor calls this constructor too
            double firstVal = center.distance(neighbors.get(0));
            for (int i = 1; i < neighbors.size(); i++) {
                if (center.distance(neighbors.get(i)) != firstVal) {
                    throw new IllegalArgumentException("All edges must have same length from center!");
                }
            }
        }
    }

    public RadialGraph(Point center) {
        this(center, new ArrayList<>());
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        List<Point> rotatedPoints = new ArrayList<>(neighbors.size());
        for (Point neighbor : neighbors) {
            Point rotatedPoint = neighbor.rotatePoint(center, degrees);
            rotatedPoints.add(rotatedPoint);
        }
        return new RadialGraph(center, rotatedPoints);
    }

    @Override
    public RadialGraph translateBy(double x, double y) {
        Point translateCenter = center.translatePoint(x,y);
        List<Point> translatedPoints = new ArrayList<>(neighbors.size());
        for(int i =0; i< neighbors.size(); i++){
            translatedPoints.add(i, neighbors.get(i).translatePoint(x,y));
        }
        return new RadialGraph(translateCenter, translatedPoints);
    }

    @Override
    public Point center() {
        return this.center;
    }

   @Override
    public String toString() {
        // Translate the shape to have its center at (0,0)
        double centerX = center().getX();
        double centerY = center().getY();
        translateBy(-centerX, -centerY);

        // Sort the points by angle with respect to the positive x-axis
        List<Point> points = neighbors;
        Point center = center();
        Collections.sort(points, (p1, p2) -> {
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

        // Build the string representation of the shape
        StringBuilder sb = new StringBuilder("[");
        sb.append(center.toString());
        for (Point p : points) {
            sb.append("; ").append(p.toString());
        }
        sb.append("]");

        // Translate the shape back to its original position
        translateBy(centerX, centerY);

        return sb.toString();
    }


    public static void main(String... args) {

        Point center = new Point("center", 0, 0);
        Point east = new Point("east", 1, 0);
        Point west = new Point("west", -1, 0);
        Point north = new Point("north", 0, 1);
        Point south = new Point("south", 0, -1);
        Point toofarsouth = new Point("south", 0, -2);

        System.out.println("Single Point");
        RadialGraph lonely = new RadialGraph(center);
        System.out.println(lonely);
        //Single Point
        //[(center, 0.0, 0.0)]

        //Rotating Test
        System.out.println("\n" + "Rotate Test");
        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));
        System.out.println(g);
        g = g.rotateBy(90);
        System.out.println(g);
        g = g.rotateBy(180);
        System.out.println(g);
        g = g.rotateBy(270);
        System.out.println(g);
        g = g.rotateBy(180);
        System.out.println(g);
        System.out.println();
        //Rotate Test
        //[(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
        //[(center, 0.0, 0.0); (south, 1.0, 0.0); (east, 0.0, 1.0); (north, -1.0, 0.0); (west, 0.0, -1.0)]
        //[(center, 0.0, 0.0); (north, 1.0, 0.0); (west, 0.0, 1.0); (south, -1.0, 0.0); (east, 0.0, -1.0)]
        //[(center, 0.0, 0.0); (west, 1.0, 0.0); (south, 0.0, 1.0); (east, -1.0, 0.0); (north, 0.0, -1.0)]
        //[(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]

        //Translation Test
        System.out.println("\n" + "Translation Test");
        //Translate Tests
        Shape t = new RadialGraph(center, Arrays.asList(north, south, east, west));
        System.out.println(t);
        t = t.translateBy(100,100);
        System.out.println(t);
        t = t.translateBy(-10,-10);
        System.out.println(t);
        t = t.translateBy(-90,-90);
        System.out.println(t);
        System.out.println();
        //[(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
        //[(center, 100.0, 100.0); (east, 101.0, 100.0); (north, 100.0, 101.0); (west, 99.0, 100.0); (south, 100.0, 99.0)]
        //[(center, 90.0, 90.0); (east, 91.0, 90.0); (north, 90.0, 91.0); (west, 89.0, 90.0); (south, 90.0, 89.0)]
        //[(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]



        //Break Your Code Test
        System.out.println("Break Your Code Test");
        Point center1 = new Point("center", 10,14);
        Point east1 = new Point("east", 11,14);
        Point west1 = new Point("west", 9,14);
        Point north1 = new Point("north", 10,15);
        Point south1 = new Point("south", 10,13);
        Shape test1 = new RadialGraph(center1, Arrays.asList(north1, south1, east1, west1));
        System.out.println("Original:    " + test1);
        test1 = test1.translateBy(100,100);
        System.out.println("Translate:   " + test1);
        test1 = test1.translateBy(-10,-10);
        System.out.println("Translate:   " + test1);
        test1 = test1.rotateBy(90);
        System.out.println("Rotate   :   " + test1);
        test1 = test1.rotateBy(90);
        System.out.println("Rotate   :   " + test1);
        test1 = test1.rotateBy(90);
        System.out.println("Rotate   :   " + test1);
        test1 = test1.rotateBy(90);
        System.out.println("Rotate   :   " + test1);
        test1 = test1.rotateBy(-90);
        System.out.println("Rotate   :   " + test1);
        test1 = test1.rotateBy(-90);
        System.out.println("Rotate   :   " + test1);
        test1 = test1.rotateBy(-90);
        System.out.println("Rotate   :   " + test1);
        test1 = test1.rotateBy(-90);
        System.out.println("Rotate   :   " + test1);
        System.out.println();
        //Break Your Code Test
        //Original:    [(center, 10.0, 14.0); (east, 11.0, 14.0); (north, 10.0, 15.0); (west, 9.0, 14.0); (south, 10.0, 13.0)]
        //Translate:   [(center, 110.0, 114.0); (east, 111.0, 114.0); (north, 110.0, 115.0); (west, 109.0, 114.0); (south, 110.0, 113.0)]
        //Translate:   [(center, 100.0, 104.0); (east, 101.0, 104.0); (north, 100.0, 105.0); (west, 99.0, 104.0); (south, 100.0, 103.0)]
        //Rotate   :   [(center, 100.0, 104.0); (south, 101.0, 104.0); (east, 100.0, 105.0); (north, 99.0, 104.0); (west, 100.0, 103.0)]
        //Rotate   :   [(center, 100.0, 104.0); (west, 101.0, 104.0); (south, 100.0, 105.0); (east, 99.0, 104.0); (north, 100.0, 103.0)]
        //Rotate   :   [(center, 100.0, 104.0); (north, 101.0, 104.0); (west, 100.0, 105.0); (south, 99.0, 104.0); (east, 100.0, 103.0)]
        //Rotate   :   [(center, 100.0, 104.0); (east, 101.0, 104.0); (north, 100.0, 105.0); (west, 99.0, 104.0); (south, 100.0, 103.0)]
        //Rotate   :   [(center, 100.0, 104.0); (north, 101.0, 104.0); (west, 100.0, 105.0); (south, 99.0, 104.0); (east, 100.0, 103.0)]
        //Rotate   :   [(center, 100.0, 104.0); (west, 101.0, 104.0); (south, 100.0, 105.0); (east, 99.0, 104.0); (north, 100.0, 103.0)]
        //Rotate   :   [(center, 100.0, 104.0); (south, 101.0, 104.0); (east, 100.0, 105.0); (north, 99.0, 104.0); (west, 100.0, 103.0)]
        //Rotate   :   [(center, 100.0, 104.0); (east, 101.0, 104.0); (north, 100.0, 105.0); (west, 99.0, 104.0); (south, 100.0, 103.0)]

        System.out.println("\n" + "Illegal Argument Exceptions");
        // This line must throw IllegalArgumentException, since the edges will not be of the same length
        RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));
        System.out.println(nope);
        //Illegal Argument Exceptions
        //Exception in thread "main" java.lang.IllegalArgumentException: Whatever error you put!
        //	at geometry.RadialGraph.<init>(RadialGraph.java:17)
        //	at geometry.RadialGraph.main(RadialGraph.java:196)
    }
}
