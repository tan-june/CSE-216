package geometry;

import java.util.*;

public class SquareSymmetries implements Symmetries<Square> {
    @Override
    public boolean areSymmetric(Square s1, Square s2) {
        double s1CenterX = s1.center.getX();
        double s1CenterY = s1.center.getY();
        double s2CenterX = s2.center.getX();
        double s2CenterY = s2.center.getY();
        double differenceX = s1CenterX-s2CenterX;
        double differenceY = s1CenterY-s2CenterY;

        return (differenceX == differenceY);
    }

    @Override
    public Collection<Square> symmetriesOf(Square square) {
        List<Square> symmetries = new ArrayList<>();
        Point storedCenter = square.center();
        double storedCenterX = storedCenter.getX();
        double storedCenterY= storedCenter.getY();

        Square rotate0 = square.rotateBy(0);
        //Rotate 90
        Square rotate90 = square.rotateBy(90);
        //Rotate 180
        Square rotate180 = square.rotateBy(180);
        //Rotate 270
        Square rotate270 = square.rotateBy(270);

        //Vertical Reflect
        Square verticalReflect = square;
        verticalReflect = (Square) verticalReflect.translateBy(-storedCenterX, -storedCenterY);
        verticalReflect = verticalReflect.rotateBy(-180);
        verticalReflect = (Square) verticalReflect.translateBy(storedCenterX, storedCenterY);

        //Horizontal Reflect
        Square horizontalRefelct = square;
        horizontalRefelct = (Square) horizontalRefelct.translateBy(-storedCenterX, -storedCenterY);
        horizontalRefelct = horizontalRefelct.rotateBy(-180);
        horizontalRefelct = (Square) horizontalRefelct.translateBy(storedCenterX, storedCenterY);

        //Diagonal Reflect
        Square diagonalReflect = square;
        diagonalReflect = (Square) diagonalReflect.translateBy(-storedCenterX, -storedCenterY);
        diagonalReflect = diagonalReflect.rotateBy(-90);
        diagonalReflect = (Square) diagonalReflect.translateBy(storedCenterX, storedCenterY);


        //do counter-diagonal reflect
        Square counterDiagonal = square;
        double x = counterDiagonal.center.getX();
        double y = counterDiagonal.center.getY();
        counterDiagonal.center = new Point(counterDiagonal.center.name, y, x);
        counterDiagonal = counterDiagonal.rotateBy(-90);

        symmetries.add(rotate0);
        symmetries.add(rotate90);
        symmetries.add(rotate180);
        symmetries.add(rotate270);

//        symmetries.add(verticalReflect);
//        symmetries.add(horizontalRefelct);
//        symmetries.add(diagonalReflect);
//        symmetries.add(counterDiagonal);

        return symmetries;
    }

    public static void main(String... args) {
        Point a = new Point("A", 10, 10);
        Point b = new Point("B", -10, 10);
        Point c = new Point("C", -10, -10);
        Point d = new Point("D", 10, -10);

        Point a1 = new Point("A", 10, 0);
        Point b1 = new Point("B", 0, 0);
        Point c1 = new Point("C", 0, -10);
        Point d1 = new Point("D", 10, -10);

        SquareSymmetries ar = new SquareSymmetries();
        Square s1 = new Square(a,b,c,d);
        Square s2 = new Square(a1,b1,c1,d1);

        System.out.println("Original: " + s1.toString());
        Collection<Square> symmetries = ar.symmetriesOf(s1);
        for (Square s : symmetries) System.out.println("          " + s);

        System.out.println();

        System.out.println("Original: " + s2.toString());
        Collection<Square> symmetries1 = ar.symmetriesOf(s2);
        for (Square s : symmetries1) System.out.println("          " + s);
        //doesn't print 180 in the right order

    }
}
