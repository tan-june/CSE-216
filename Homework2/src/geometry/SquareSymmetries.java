package geometry;
import java.util.*;
public class
SquareSymmetries implements Symmetries<Square> {
    @Override
    public boolean areSymmetric(Square s1, Square s2) {
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s1.getA());
//        System.out.println(s2.getA());
//        System.out.println(s1.getB());
//        System.out.println(s2.getB());
//        System.out.println(s1.getC());
//        System.out.println(s2.getC());
//        System.out.println(s1.getD());
//        System.out.println(s2.getD());
        if(s1.equals(s2)){
            return true;
        }
        if(s2.equals(s1)){
            return true;
        }
        if(s2.equals(s1.rotateBy(-360))){
            return true;
        }
        if(s2.equals(s1.rotateBy(360))){
            return true;
        }
        Collection<Square> symmetries = symmetriesOf(s1);
        for (Square symmetry : symmetries) {
            if (symmetry.equals(s2)) {
                return true;
            }
        }
        Collection<Square> symmetries1 = symmetriesOf(s2);
        for (Square symmetry : symmetries1) {
            if (symmetry.equals(s1)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Collection<Square> symmetriesOf(Square square) {
        List<Square> symmetries = new ArrayList<>();
        //Rotate 0
        Square rotate0 = square.rotateBy(0);
        symmetries.add(rotate0);
        //Rotate 90
        Square rotate90 = square.rotateBy(-90);
        symmetries.add(rotate90);
        //Rotate 180
        Square rotate180 = square.rotateBy(-180);
        symmetries.add(rotate180);
        //Rotate 270
        Square rotate270 = square.rotateBy(-270);
        symmetries.add(rotate270);
        //Vertical Reflect
        Square verticalReflect = square.verticalReflect();
        symmetries.add(verticalReflect);
//        //Horizontal Reflect
        Square horizontalReflect = square.horizontalReflect();
        symmetries.add(horizontalReflect);
        //Diagonal Reflect
        Square diagonalReflect = square.diagonalReflect();
        symmetries.add(diagonalReflect);
        //Counter-Diagonal Reflect

        Square counterDiagonal = square.counterDiagonal();
        symmetries.add(counterDiagonal);
        return symmetries;
    }
    public static void main(String... args) {
        Point a = new Point("A", 10, 10);
        Point b = new Point("B", -10, 10);
        Point c = new Point("C", -10, -10);
        Point d = new Point("D", 10, -10);

        Point a1 = new Point("A", 100, 100);
        Point b2 = new Point("B", -100, 100);
        Point c3 = new Point("C", -100, -100);
        Point d4 = new Point("D", 100, -100);

        SquareSymmetries ar = new SquareSymmetries();

        Square s1 = new Square(a,b,c,d);
        Square s2 = new Square(a,b,c,d);
        Square s3 = new Square(a1,b2,c3,d4);

        Square h1 = s2.rotateBy(-90);
        System.out.println(ar.areSymmetric(s1,h1));

        Square h2 = s2.rotateBy(-180);
        System.out.println(ar.areSymmetric(s1,h2));

        Square h3 = s2.rotateBy(-270);
        System.out.println(ar.areSymmetric(s1,h3));

        Square h4 = s2.rotateBy(-360);
        System.out.println(ar.areSymmetric(s1,h4));

        Square h5 = s2.rotateBy(90);
        System.out.println(ar.areSymmetric(s1,h5));

        Square h6 = s2.rotateBy(180);
        System.out.println(ar.areSymmetric(s1,h6));

        Square h7 = s2.rotateBy(270);
        System.out.println(ar.areSymmetric(s1,h7));

        Square h8 = s2.rotateBy(360);
        System.out.println(ar.areSymmetric(s1,h8));
        System.out.println(ar.areSymmetric(h8,h8));
        System.out.println(ar.areSymmetric(h8,s1));
        System.out.println(ar.areSymmetric(h8,s3));

        Collection<Square> symmetries = ar.symmetriesOf(s1);
        for (Square s : symmetries) System.out.println(s);
        System.out.println();
        Collection<Square> symmetries1 = ar.symmetriesOf(s2);
        for (Square s : symmetries1) System.out.println(s);
    }
}
