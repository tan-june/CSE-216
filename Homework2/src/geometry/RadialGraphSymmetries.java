package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RadialGraphSymmetries implements Symmetries<RadialGraph>{
    @Override
    public boolean areSymmetric(RadialGraph s1, RadialGraph s2) {
        if(s1 == null || s2 == null) {
//            System.out.println("null trigger");
            return false;
        }
        if(s1.center() != s2.center()){
//            System.out.println("center trigger");
            return false;
        }
        if(s1.getNeighborsSize() != s2.getNeighborsSize()){
//            System.out.println("neighbor trigger");
            return false;
        }

        int degrees = 360/s1.getNeighborsSize();

        for (int i = 0; i < s1.getNeighborsSize(); i++) {
            if (s1.equals(s2.rotateBy(i*degrees))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<RadialGraph> symmetriesOf(RadialGraph radialGraph) {
        List<RadialGraph> symmetries = new ArrayList<>();
        symmetries.add(radialGraph);
        int numofVertices = radialGraph.getNeighborsSize();
        int degrees = 360/numofVertices;
        for(int i=1; i<numofVertices; i++){ //figure out how many times this needs to be done
            RadialGraph a = radialGraph.rotateBy(degrees*i);
            symmetries.add(a);
        }
        return symmetries;
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


        RadialGraphSymmetries a = new RadialGraphSymmetries();

        System.out.println("----------------are Symmetric-------------------------");
        System.out.println();

        RadialGraph sm1 = new RadialGraph(center, Arrays.asList(north, south, east, west));
        RadialGraph sm2 = new RadialGraph(center, Arrays.asList(north, south, east, west));
        RadialGraph sm3 = new RadialGraph(center, Arrays.asList(seventh, south, east, west));
        RadialGraph sm4 = new RadialGraph(center, Arrays.asList(eigth, south, east, west));
        RadialGraph sm5 = new RadialGraph(center, Arrays.asList(east, west, south, north, fifth, sixth, seventh, eigth));
        RadialGraph sm6 = new RadialGraph(center, Arrays.asList(east, west, south, north, fifth, sixth, seventh));
        System.out.println("Test 1: "+ a.areSymmetric(sm1, sm1));
        System.out.println("Test 2: "+ a.areSymmetric(sm2, sm1));
        System.out.println("Test 3: "+ a.areSymmetric(sm1, sm2));
        System.out.println("Test 4: "+ a.areSymmetric(sm1, sm3));
        System.out.println("Test 5: "+ a.areSymmetric(sm2, sm3));
        System.out.println("Test 6: "+ a.areSymmetric(sm1, sm4));
        System.out.println("Test 7: "+ a.areSymmetric(sm2, sm4));
        System.out.println("Test 8: "+ a.areSymmetric(sm3, sm4));
        System.out.println("Test 9: "+ a.areSymmetric(sm3, sm5));
        System.out.println("Test10: "+ a.areSymmetric(sm5, sm6));


        System.out.println("----------------Symmetries Test-------------------------");
        System.out.println();

        System.out.println("1 spokes = 1 symmetries");
        RadialGraph hello = new RadialGraph(center, Arrays.asList(sixth));
        Collection<RadialGraph> symmetri3es1 = a.symmetriesOf(hello);
        for (RadialGraph s : symmetri3es1) System.out.println(s);

        System.out.println();
        System.out.println("3 spokes = 3 symmetries");
        RadialGraph he2llo = new RadialGraph(center, Arrays.asList(sixth, seventh, eigth));
        Collection<RadialGraph> sy2mmetri3es1 = a.symmetriesOf(he2llo);
        for (RadialGraph s : sy2mmetri3es1) System.out.println(s);

        System.out.println();
        System.out.println("4 spokes = 4 symmetries");
        RadialGraph s1 = new RadialGraph(center, Arrays.asList(north, south, east, west));
        Collection<RadialGraph> symmetries = a.symmetriesOf(s1);
        for (RadialGraph s : symmetries) System.out.println(s);

        System.out.println();
        System.out.println("5 spokes = 5 symmetries");
        RadialGraph hell2o = new RadialGraph(center, Arrays.asList(east, west, sixth, seventh, eigth));
        Collection<RadialGraph> symmetri33es1 = a.symmetriesOf(hell2o);
        for (RadialGraph s : symmetri33es1) System.out.println(s);

        System.out.println();
        System.out.println("6 spokes = 6 symmetries");
        RadialGraph six = new RadialGraph(center, Arrays.asList(east, west, south, north, fifth, eigth));
        Collection<RadialGraph> symmetries2 = a.symmetriesOf(six);
        for (RadialGraph s : symmetries2) System.out.println(s);

        System.out.println();
        System.out.println("8 spokes = 8 symmetries");
        RadialGraph g = new RadialGraph(center, Arrays.asList(east, west, south, north, fifth, sixth, seventh, eigth));
        Collection<RadialGraph> symmetries1 = a.symmetriesOf(g);
        for (RadialGraph s : symmetries1) System.out.println(s);

    }
}
