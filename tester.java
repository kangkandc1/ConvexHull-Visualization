package myhull2;

import java.awt.*;
import java.util.*;
import java.util.List;


public class tester {
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(16, 3),
                new Point(12, 17),
                new Point(0, 6),
                new Point(-4, -6),
                new Point(16, 6),

                new Point(16, -7),
                new Point(16, -3),
                new Point(17, -4),
                new Point(5, 19),
                new Point(19, -8),

                new Point(3, 16),
                new Point(12, 13),
                new Point(3, -4),
                new Point(17, 5),
                new Point(-3, 15),

                new Point(-3, -9),
                new Point(0, 11),
                new Point(-9, -3),
                new Point(-4, -2),
                new Point(12, 10));

        ConvexHull myhull=new ConvexHull();


        List<Point> hull = myhull.convexHull(points);
        Polygon p=myhull.wrapPolygon(hull);
        ArrayList<Integer[]> C=new ArrayList<Integer[]>();
        C.add(new Integer[]{1,2});
        C.add(new Integer[]{3,4});
        List<Point> clicks=myhull.wrapClicks(C);
        List<Point> newHull=myhull.convexHull(clicks);

        System.out.printf("Convex Hull: %s\n", hull);

    }
}
