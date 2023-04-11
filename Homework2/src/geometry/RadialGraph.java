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
                double threshold = 0.000001;
                if (Math.abs(center.distance(neighbors.get(i)) - firstVal) > threshold) {
                    //System.out.println(neighbors.get(i).toString());
                    //System.out.println("Value: " + center.distance(neighbors.get(i)));
                    throw new IllegalArgumentException("All edges must have same length from center!");
                }
            }
        }
    }
    public int getNeighborsSize() {return neighbors.size();}
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
        return new RadialGraph(center.rotatePoint(center, degrees), rotatedPoints);
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
        // sort points in case they are not sorted; but they are supposed too be
        List<Point> points = neighbors;
        Point center = center();
        Collections.sort(points, (object1, object2) -> {
            object1 = object1.round();
            object2 = object2.round();
            double angle1 = Math.toDegrees(Math.atan2(object1.getY() - centerY, object1.getX() - centerX));
            double angle2 = Math.toDegrees(Math.atan2(object2.getY() - centerY, object2.getX() - centerX));
            if (angle1 < 0) {
                angle1 += 360.0;
            }
            if (angle2 < 0) {
                angle2 += 360;
            }
            return Double.compare(angle1, angle2);
        });
        StringBuilder sb = new StringBuilder("[");
        sb.append(center.toString());
           for (int i = 0; i < points.size(); i++) {
           sb.append("; ");
           Point point = points.get(i);
           sb.append(String.format("(%s, %.2f, %.2f)", point.name, point.x, point.y));
       }
        sb.append("]");
        translateBy(centerX, centerY);
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == this){
        //System.out.println("failure 0");
            return true;
        }
        if(!(obj instanceof RadialGraph)){
        //System.out.println("failure 1");
            return false;
        }
        RadialGraph object = (RadialGraph) obj;
        if (this.center != object.center) {
            //System.out.println("failure 2");
            return false;
        }
        if (this.neighbors.size() != object.neighbors.size()) {
            //System.out.println("failure 3");
            return false;
        }
        for (int i = 0; i < this.neighbors.size(); i++) {
            double threshold = 0.000001;
            if ((Math.abs((this.neighbors.get(i).getX()) - (object.neighbors.get(i).getX())) > threshold)
            || (Math.abs((this.neighbors.get(i).getY()) - (object.neighbors.get(i).getY())) > threshold)) {
                //System.out.println(this.neighbors.get(i));
                //System.out.println(object.neighbors.get(i));
                return false;
            }
        }
        return true;
    }
    public static void main(String... args) {
        Point center = new Point("center", 0, 0);
        Point east = new Point("E", 1, 0);
        Point west = new Point("W", -1, 0);
        Point north = new Point("N", 0, 1);
        Point south = new Point("S", 0, -1);

        Point fifth = new Point("NE", ((Math.sqrt(2))/2),((Math.sqrt(2))/2));
        Point sixth = new Point("NW", -((Math.sqrt(2))/2),-((Math.sqrt(2))/2));
        Point seventh = new Point("SW", -((Math.sqrt(2))/2),((Math.sqrt(2))/2));
        Point eigth = new Point("SE", ((Math.sqrt(2))/2),-((Math.sqrt(2))/2));

        Point toofarsouth = new Point("south", 0, 0.2312);

        System.out.println("Single Point");
        RadialGraph lonely = new RadialGraph(center);
        System.out.println(lonely);
        //Single Point
        //[(center, 0.0, 0.0)]

        //Rotating Test
        System.out.println("\n" + "Rotate Test");
        Shape g = new RadialGraph(center, Arrays.asList(east, west, south, north, fifth, sixth, seventh, eigth));
        System.out.println(g);
        g = g.rotateBy(45);
        System.out.println(g);
        g = g.rotateBy(45);
        System.out.println(g);
        g = g.rotateBy(45);
        System.out.println(g);
        g = g.rotateBy(45);
        System.out.println(g);
        g = g.rotateBy(45);
        System.out.println(g);
        g = g.rotateBy(45);
        System.out.println(g);
        g = g.rotateBy(45);
        System.out.println(g);
        g = g.rotateBy(45);
        System.out.println(g);
        System.out.println();

        System.out.println("\n" + "Rotate Test 2");
        Shape g1 = new RadialGraph(center, Arrays.asList(east, west, south, north, fifth, sixth, seventh, eigth));
        g1 = g1.translateBy(10,10);
        System.out.println(g1);
        g = g.rotateBy(45);
        System.out.println(g1);
        g = g.rotateBy(45);
        System.out.println(g1);
        g = g.rotateBy(45);
        System.out.println(g1);
        g = g.rotateBy(45);
        System.out.println(g1);
        g = g.rotateBy(45);
        System.out.println(g1);
        g = g.rotateBy(45);
        System.out.println(g1);
        g = g.rotateBy(45);
        System.out.println(g1);
        g = g.rotateBy(45);
        System.out.println(g1);
        System.out.println();

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
        test1 = test1.translateBy(-90,-90);
        System.out.println("Translate:   " + test1);
        System.out.println();

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
