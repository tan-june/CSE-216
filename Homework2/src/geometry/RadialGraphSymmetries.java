package geometry;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RadialGraphSymmetries implements Symmetries<RadialGraph>{
    @Override
    public boolean areSymmetric(RadialGraph s1, RadialGraph s2) {
        return false;
    }

    @Override
    public Collection<RadialGraph> symmetriesOf(RadialGraph radialGraph) {
        Set<RadialGraph> symmetries = new HashSet<>();
        symmetries.add(radialGraph);
        for(int i=0; i<360/4; i++){ //figure out how many times this needs to be done
            RadialGraph a = radialGraph.rotateBy(i);
            symmetries.add(a);
            radialGraph.rotateBy(-i);
        }
        return symmetries;
    }

    public static void main(String... args) {
        Point east = new Point("A", 10, 10);
        Point west = new Point("B", -10, 10);
        Point north = new Point("C", -10, -10);
        Point south = new Point("D", 10, -10);
        Point center = new Point("Center", 0 ,0);

        RadialGraphSymmetries a = new RadialGraphSymmetries();

        RadialGraph s1 = new RadialGraph(center, Arrays.asList(north, south, east, west));


        Collection<RadialGraph> symmetries = a.symmetriesOf(s1);

        for (RadialGraph s : symmetries) System.out.println(s);
    }
}
