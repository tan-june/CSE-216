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
        List<Point> PointSort = new ArrayList<>(neighbors);
        PointSort.sort((p1, p2) -> {
            double angle1 = Math.atan2(-p1.getY(), -p1.getX());
            double angle2 = Math.atan2(-p2.getY(), -p2.getX());
            return Double.compare(angle1, angle2);
        });
        StringBuilder sb = new StringBuilder("[");
        sb.append(center.toString());

        for (Point point : PointSort) {
            sb.append("; ");
            sb.append("(");
            sb.append(point.name);
            sb.append(", ");
            sb.append(point.getX());
            sb.append(", ");
            sb.append(point.getY());
            sb.append(")");
        }
        sb.append("]");

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
        // A single node is a valid radial graph.
        RadialGraph lonely = new RadialGraph(center);
        // Must print: [(center, 0.0, 0.0)]
        System.out.println(lonely);

        System.out.println("\n" + "Rotate Test");
        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
        System.out.println(g);
        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1, 0), and similarly for all the
        g = g.rotateBy(90);
        System.out.println(g);
        g = g.rotateBy(90);
        System.out.println(g);
        g = g.rotateBy(90);
        System.out.println(g);
        g = g.rotateBy(90);
        System.out.println(g);

        System.out.println("\n" + "Translation Test");
        //Translate Tests
        Shape t = new RadialGraph(center, Arrays.asList(north, south, east, west));
        System.out.println(t);
        t = t.translateBy(10,10);
        System.out.println(t);
        t = t.translateBy(-10,-10);
        System.out.println(t);

        System.out.println("\n" + "Illegal Argument Exceptions");
        // This line must throw IllegalArgumentException, since the edges will not be of the same length
        RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));
        System.out.println(nope);
    }
}
