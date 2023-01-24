package myhull2;

public class Point implements  Comparable<Point> {
     int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return  y;
    }

    public int compareTo(Point o) {
        return Integer.compare(x, o.x);
    }


    public String toString() {
        return String.format("(%d, %d)", x, y);
    }


}
