package geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareSymmetries implements Symmetries<Square> {
    @Override
    public boolean areSymmetric(Square s1, Square s2) {
        return false;
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
        verticalReflect = verticalReflect.rotateBy(180);
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
        symmetries.add(verticalReflect);
        symmetries.add(horizontalRefelct);
        symmetries.add(diagonalReflect);
        symmetries.add(counterDiagonal);

        return symmetries;
    }
}
