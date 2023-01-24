package myhull2;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
//the convex hull algorithm is based on code available at https://rosettacode.org/wiki/Convex_hull#Java
public class ConvexHull {
    public List<Point> convexHull(List<Point> p){
        if (p.isEmpty()) return emptyList();
        p.sort(Point::compareTo);
        List<Point> h = new ArrayList<>();

        // lower hull
        for (Point pt : p) {
            while (h.size() >= 2 && !ccw(h.get(h.size() - 2), h.get(h.size() - 1), pt)) {
                h.remove(h.size() - 1);
            }
            h.add(pt);
        }

        // upper hull
        int t = h.size() + 1;
        for (int i = p.size() - 1; i >= 0; i--) {
            Point pt = p.get(i);
            while (h.size() >= t && !ccw(h.get(h.size() - 2), h.get(h.size() - 1), pt)) {
                h.remove(h.size() - 1);
            }
            h.add(pt);
        }

        h.remove(h.size() - 1);
        return h;
    }

    private static boolean ccw(Point a, Point b, Point c) {
        return ((b.x - a.x) * (c.y - a.y)) > ((b.y - a.y) * (c.x - a.x));
    }

    public Polygon wrapPolygon(List<Point> H){
        int[] HX=new int[H.size()];
        int [] HY=new int[H.size()];
        for(int i=0;i<H.size();i++){
            HX[i]=H.get(i).getX();
            HY[i]=H.get(i).getY();
        }
        Polygon p= new Polygon(HX,HY,H.size());
        return p;

    }

    public List<Point> wrapClicks(ArrayList<Integer[]> C){

        ArrayList<Point> H=new ArrayList<>();
        for (int i=0;i<C.size();i++){
            Integer [] click=C.get(i);
            Point p= new Point(click[0],click[1]);
            H.add(p);
        }
        return H;
    }



}



