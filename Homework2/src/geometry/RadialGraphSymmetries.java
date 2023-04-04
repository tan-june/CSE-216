package geometry;

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
        for(int i=0; i<=360; i++){
            RadialGraph a = radialGraph.rotateBy(i);
            symmetries.add(a);
        }
        return symmetries;
    }
}
